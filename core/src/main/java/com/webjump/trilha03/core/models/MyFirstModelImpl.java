package com.webjump.trilha03.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.components.ComponentContext;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.*;


@Model(adaptables = {SlingHttpServletRequest.class},
        adapters = MyFirstModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = MyFirstModelImpl.RESOURCE_TYPE
)
public class MyFirstModelImpl implements MyFirstModel {

    protected static final String RESOURCE_TYPE = "trilha-03/components/my-first-model";

    @Self
    private SlingHttpServletRequest request;

    @ValueMapValue
    protected String clientName;
    @ValueMapValue
    protected String codeID;
    @ValueMapValue
    protected Boolean isNewClient;

    public MyFirstModelImpl() {

    }

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
