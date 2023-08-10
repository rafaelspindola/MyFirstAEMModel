package com.webjump.trilha03.core.schedulers;

import com.webjump.trilha03.core.utils.ResolverUtils;
import org.apache.sling.api.resource.*;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Designate(ocd=MyFirstScheduler.SchedulerConfig.class)
@Component(service = Runnable.class)
public class MyFirstScheduler implements Runnable {
    @ObjectClassDefinition(name = "Webjump task")
    public static @interface SchedulerConfig {

        @AttributeDefinition(name = "Cron-job expression")
        String scheduler_expression() default "*/30 * * * * ?";

        @AttributeDefinition(name = "Concurrent task",
                description = "Whether or not to schedule this task concurrently")
        boolean scheduler_concurrent() default false;

        @AttributeDefinition(name = "A parameter",
                description = "Can be configured in /system/console/configMgr")
        String myParameter() default "";
    }

    private final Logger logger = LoggerFactory.getLogger(getClass ());

    private String myParam;
    @Reference
    private Scheduler scheduler;
    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public void run() {
        logger.debug ("Webjump trilha 03 task is now running");
        ResourceResolver resolver = null;
        try {
            resolver = ResolverUtils.newResolver (resourceResolverFactory);
            Resource modelResource = resolver.getResource ("/content/trilha-03/us/en/jcr:content/root/container/container/my_first_model");

            if (modelResource != null) {
                ModifiableValueMap valueMap = modelResource.adaptTo (ModifiableValueMap.class);
                if (valueMap != null) {
                    valueMap.put ("clientName", "Huawei");
                    valueMap.put ("codeID", "00456");
                    valueMap.put ("isNewClient", true);
                    resolver.commit ();
                }
            }
        } catch (LoginException e) {
            logger.error ("Login error {}", e.getMessage ());
        } catch (PersistenceException e) {
            logger.error ("Error in persisting data {}", e.getMessage ());
        }
    }

    @Activate
    protected void activate(final SchedulerConfig config) {
        myParam = config.myParameter ();
        ScheduleOptions scheduleOptions = scheduler.EXPR (config.scheduler_expression ());
        scheduler.schedule (this, scheduleOptions);
    }
}
