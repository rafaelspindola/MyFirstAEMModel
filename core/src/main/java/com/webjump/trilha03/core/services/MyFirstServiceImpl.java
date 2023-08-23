package com.webjump.trilha03.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webjump.trilha03.core.models.MyFirstModelImpl;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.tika.io.IOUtils;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(service = {MyFirstService.class}, immediate = true)
public class MyFirstServiceImpl implements MyFirstService {
    @Override
    public void saveClient(Resource resource, MyFirstModelImpl payloadData) throws Exception {
        try {
            ResourceResolver resourceResolver = resource.getResourceResolver ();

            ModifiableValueMap properties = resource.adaptTo (ModifiableValueMap.class);
            properties.put ("clientName", payloadData.getClientName ());
            properties.put ("codeID", payloadData.getCodeID ());
            properties.put ("isNewClient", payloadData.getIsNewClient ());

            resourceResolver.commit ();
        } catch (Exception e) {
            throw new Exception ("Failed to save data.");
        }
    }

}
