package com.webjump.trilha03.core.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webjump.trilha03.core.models.MyFirstModel;
import com.webjump.trilha03.core.models.MyFirstModelImpl;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.apache.tika.io.IOUtils;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Component(service = {Servlet.class})
@SlingServletResourceTypes(
        resourceTypes ="trilha-03/components/my-first-model",
        methods ={HttpConstants.METHOD_GET, HttpConstants.METHOD_POST},
        extensions = {"txt", "json"}
)
public class MyFirstModelServlet extends SlingAllMethodsServlet {
    private static final Logger logger = LoggerFactory.getLogger(MyFirstModelServlet.class);
    private static final long serialVersionUID = 2L;
    @Override
    protected void doGet(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) throws ServletException, IOException {

    }
    @Override
    public void doPost(final SlingHttpServletRequest req,
                          SlingHttpServletResponse resp) throws ServletException, IOException {

        try {
            Resource currentResource = req.getResource ();
            ResourceResolver resourceResolver = req.getResourceResolver ();

            String body = IOUtils.toString (req.getReader ());
            ObjectMapper objectMapper = new ObjectMapper ();
            MyFirstModelImpl payloadData = objectMapper.readValue (body, MyFirstModelImpl.class);

            ModifiableValueMap properties = currentResource.adaptTo(ModifiableValueMap.class);
            properties.put("clientName", payloadData.getClientName());
            properties.put("codeID", payloadData.getCodeID());
            properties.put("isNewClient", payloadData.getIsNewClient());
            resp.setStatus (HttpServletResponse.SC_OK);

            resourceResolver.commit ();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Failed to save data.");
        }
    }
}
