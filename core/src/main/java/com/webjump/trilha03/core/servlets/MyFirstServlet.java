package com.webjump.trilha03.core.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webjump.trilha03.core.models.MyFirstModelImpl;
import com.webjump.trilha03.core.services.MyFirstService;
import com.webjump.trilha03.core.services.MyFirstServiceImpl;
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
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(service = {Servlet.class})
@SlingServletResourceTypes(
        resourceTypes ="trilha-03/components/my-first-model",
        methods ={HttpConstants.METHOD_GET, HttpConstants.METHOD_POST},
        extensions = {"txt", "json"}
)
public class MyFirstServlet extends SlingAllMethodsServlet {
    private static final Logger logger = LoggerFactory.getLogger(MyFirstServlet.class);
    private static final long serialVersionUID = 2L;
    @Reference
    private MyFirstService myFirstService;
    @Override
    protected void doGet(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) throws ServletException, IOException {

    }
    @Override
    public void doPost(final SlingHttpServletRequest req,
                          SlingHttpServletResponse resp) throws ServletException, IOException {

        try {
            // Reads request body and parses json input into a Java object
            String body = IOUtils.toString (req.getReader ());
            ObjectMapper objectMapper = new ObjectMapper ();
            MyFirstModelImpl payloadData = objectMapper.readValue (body, MyFirstModelImpl.class);
            logger.info ("Received JSON payload: {}", body);

            // Saves the payload/input data
            myFirstService.saveClient (payloadData);

            // Sets the response to 200 OK
            resp.setStatus (HttpServletResponse.SC_OK);
            logger.info ("Request processed successfully. Response status: 200 OK");
        } catch (IOException e) {
            // If there's an exception set the response status to 500 and write an error message
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error in input/output stream.");
        }
    }
}
