package com.webjump.trilha03.core.services;

import com.adobe.xfa.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webjump.trilha03.core.models.MyFirstModelImpl;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.tika.io.IOUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(service = {MyFirstServiceImpl.class}, immediate = true)
public class MyFirstServiceImpl implements MyFirstService {
    @Reference
    private Resource resource;
    private static final Logger logger = LoggerFactory.getLogger (MyFirstServiceImpl.class);
    @Override
    public void saveClient(MyFirstModelImpl payloadData) throws IOException {
        try {
            // Get resource resolver to interact with content repository
            ResourceResolver resourceResolver = resource.getResourceResolver ();

            // Adapt resource to ModifiableValueMap to modify it's properties and then change data
            ModifiableValueMap properties = resource.adaptTo (ModifiableValueMap.class);
            properties.put ("clientName", payloadData.getClientName ());
            properties.put ("codeID", payloadData.getCodeID ());
            properties.put ("isNewClient", payloadData.getIsNewClient ());

            // Save changes to content repository
            resourceResolver.commit ();

            logger.info ("Client data saved successfully for clientName: {}, codeID: {}",
                    payloadData.getClientName (), payloadData.getCodeID ());
        } catch (IOException e) {
            // If there's a problem with data persistence log an error message with details
            logger.error ("Error in request {}.", e.getMessage ());
        }
    }

}
