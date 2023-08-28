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
            String body = IOUtils.toString (req.getReader ());
            ObjectMapper objectMapper = new ObjectMapper ();
            MyFirstModelImpl payloadData = objectMapper.readValue (body, MyFirstModelImpl.class);

            myFirstService.saveClient (payloadData);

            resp.setStatus (HttpServletResponse.SC_OK);

        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error in input/output stream.");
        }
    }
}
