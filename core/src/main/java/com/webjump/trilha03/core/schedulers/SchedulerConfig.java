package com.webjump.trilha03.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Webjump task")
public @interface SchedulerConfig {

    @AttributeDefinition(name = "Cron-job expression")
    String scheduler_expression() default "*/30 * * * * ?";

    @AttributeDefinition(name = "Concurrent task",
            description = "Whether or not to schedule this task concurrently")
    boolean scheduler_concurrent() default false;

    @AttributeDefinition(name = "A parameter",
            description = "Can be configured in /system/console/configMgr")
    String myParameter() default "";
}