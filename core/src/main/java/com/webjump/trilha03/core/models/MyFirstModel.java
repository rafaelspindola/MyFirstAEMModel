package com.webjump.trilha03.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


public interface MyFirstModel {
    String getClientName();
    String getCodeID();
    Boolean getIsNewClient();
}
