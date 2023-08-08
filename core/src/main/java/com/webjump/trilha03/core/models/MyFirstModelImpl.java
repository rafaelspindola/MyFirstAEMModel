package com.webjump.trilha03.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class MyFirstModelImpl {

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    protected String clientName;
    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    protected int numberID;
    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    protected Boolean isNewClient;

    public String getClientName() {
        return clientName;
    }

    public int getNumberID() {
        return numberID;
    }

    public Boolean getIsNewClient() {
        return isNewClient;
    }
}
