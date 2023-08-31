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
    protected String codeID;
    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    protected Boolean isNewClient;

    public MyFirstModelImpl(String clientName, String codeID, Boolean isNewClient) {
        this.clientName = clientName;
        this.codeID = codeID;
        this.isNewClient = isNewClient;
    }

    public String getClientName() {
        return clientName;
    }

    public String getCodeID() {return codeID;}

    public Boolean getIsNewClient() {
        return isNewClient;
    }
    
}
