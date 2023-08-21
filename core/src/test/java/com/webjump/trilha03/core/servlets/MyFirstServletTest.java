package com.webjump.trilha03.core.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.webjump.trilha03.core.models.MyFirstModel;
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
    @Mock
    private Resource currentResource;

    @Mock
    private ResourceResolver resourceResolver;

    @Mock
    private ModifiableValueMap valueMap;




//    @BeforeEach
//    void setup(AemContext context) {
//        servlet = new MyFirstModelServlet ();
//        MockitoAnnotations.openMocks (this);
//
//        req = context.request();
//        resp = context.response();
//    }


    @Test
    public void testDoPost() throws ServletException, IOException {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);

        // Configuração dos mocks
        when(req.getResource()).thenReturn(currentResource);
        when(req.getResourceResolver()).thenReturn(resourceResolver);
        when(currentResource.adaptTo(ModifiableValueMap.class)).thenReturn(valueMap);

        // Prepara o JSON de teste
        String jsonPayload = "{\"clientName\": \"Huawei\", \"codeID\": \"123456\", \"isNewClient\": true}";
        when(req.getReader()).thenReturn(new BufferedReader (new StringReader (jsonPayload)));

        // Executa o servlet
        MyFirstModelServlet servlet = new MyFirstModelServlet();
        servlet.doPost(req, resp);

        // Verificações
        verify(valueMap).put(eq("clientName"), eq("Huawei"));
        verify(valueMap).put(eq("codeID"), eq("123456"));
        verify(valueMap).put(eq("isNewClient"), eq(true));
        verify(resourceResolver).commit();
        verify(resp).setStatus(HttpServletResponse.SC_OK);
    }

}
