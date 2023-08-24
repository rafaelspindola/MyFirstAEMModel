package com.webjump.trilha03.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webjump.trilha03.core.models.MyFirstModelImpl;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.tika.io.IOUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(service = MyFirstServiceImpl.class, immediate = true)
public class MyFirstServiceImpl implements MyFirstService {
    @Reference
    private Resource resource;
    private static final Logger logger = LoggerFactory.getLogger (MyFirstServiceImpl.class);
    @Override
    public void saveClient(MyFirstModelImpl payloadData) throws IOException {
        try {
            resource.getResourceType ();
            ResourceResolver resourceResolver = resource.getResourceResolver ();

            ModifiableValueMap properties = resource.adaptTo (ModifiableValueMap.class);
            properties.put ("clientName", payloadData.getClientName ());
            properties.put ("codeID", payloadData.getCodeID ());
            properties.put ("isNewClient", payloadData.getIsNewClient ());

            resourceResolver.commit ();
        } catch (PersistenceException e) {
            logger.error ("Error in request {}.", e.getMessage ());
        }
    }

}
