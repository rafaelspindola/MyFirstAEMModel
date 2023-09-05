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

@Designate(ocd= SchedulerConfig.class)
@Component(service = Runnable.class)
public class MyFirstScheduler implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(getClass ());

    private String myParam;
    @Reference
    private Scheduler scheduler;
    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public void run() {
        logger.debug ("Webjump trilha 03 task is now running.");
        ResourceResolver resolver = null;
        try {
            // Creates a new resource resolver with necessary credentials and points to the resource repository
            resolver = ResolverUtils.newResolver (resourceResolverFactory);
            Resource modelResource = resolver.getResource ("/content/trilha-03/us/en/jcr:content/root/container/container/my_first_model");

            if (modelResource != null) {
                logger.debug ("Before updating modelResource.");
                // Adapts resource to ModifiableValueMap to make changes and then modifies data
                ModifiableValueMap valueMap = modelResource.adaptTo (ModifiableValueMap.class);
                if (valueMap != null) {
                    valueMap.put ("clientName", "Huawei");
                    valueMap.put ("codeID", "00456");
                    valueMap.put ("isNewClient", true);

                    // Saves changes
                    resolver.commit ();
                }
                logger.debug ("After updating modelResource.");
            }
        } catch (LoginException e) {
            logger.error ("Login error {}", e.getMessage ());
        } catch (PersistenceException e) {
            logger.error ("Error in persisting data {}", e.getMessage ());
        } finally {
            logger.debug ("Webjump trilha 03 task has been completed.");
        }
    }

    @Activate
    protected void activate(final SchedulerConfig config) {
        logger.debug ("Activating configurations.");

        // Retrieves configuration parameters
        myParam = config.myParameter ();

        // Configures scheduler to activate every 30 seconds
        ScheduleOptions scheduleOptions = scheduler.EXPR (config.scheduler_expression ());

        // Schedules task
        scheduler.schedule (this, scheduleOptions);
    }
}
