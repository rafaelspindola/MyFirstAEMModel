package com.webjump.trilha03.core.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.webjump.trilha03.core.models.MyFirstModel;
import com.webjump.trilha03.core.models.MyFirstModelImpl;
import com.webjump.trilha03.core.services.MyFirstServiceImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static junitx.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith (MockitoExtension.class)

public class MyFirstServletTest {

    private static final ObjectMapper objMapper = new ObjectMapper ();
    @Mock
    private MockSlingHttpServletRequest req;
    @Mock
    private MockSlingHttpServletResponse resp;
    @Mock
    private MyFirstModel myFirstModel;
    @InjectMocks
    private MyFirstServiceImpl myFirstService;


    @Test
    protected void testDoPost() throws ServletException, IOException {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);

        // Prepara o JSON de teste
        String jsonPayload = "{\"clientName\": \"Huawei\", \"codeID\": \"123456\", \"isNewClient\": true}";
        when(req.getReader()).thenReturn(new BufferedReader (new StringReader (jsonPayload)));


        MyFirstModelImpl payloadData = objMapper.readValue(jsonPayload, MyFirstModelImpl.class);
        myFirstService.saveClient (payloadData);

        // Executa o servlet
        MyFirstServlet servlet = new MyFirstServlet();
        servlet.doPost(req, resp);

        verify(resp).setStatus(HttpServletResponse.SC_OK);
    }

}
