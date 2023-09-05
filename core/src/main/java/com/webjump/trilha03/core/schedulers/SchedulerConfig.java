package com.webjump.trilha03.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
// This interface provides configurations for the scheduler
@ObjectClassDefinition(name = "Webjump task")
public @interface SchedulerConfig {

    // This method executes the task every 30 seconds
    @AttributeDefinition(name = "Cron-job expression")
    String scheduler_expression() default "*/30 * * * * ?";

    @AttributeDefinition(name = "Concurrent task",
            description = "Whether or not to schedule this task concurrently")
    boolean scheduler_concurrent() default false;

    @AttributeDefinition(name = "A parameter",
            description = "Can be configured in /system/console/configMgr")
    String myParameter() default "";
}