package com.webjump.trilha03.core.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.webjump.trilha03.core.models.MyFirstModel;
import com.webjump.trilha03.core.models.MyFirstModelImpl;
import com.webjump.trilha03.core.services.MyFirstService;
import com.webjump.trilha03.core.services.MyFirstServiceImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith ({MockitoExtension.class, AemContextExtension.class})
public class MyFirstServletTest {

    private static final ObjectMapper objMapper = new ObjectMapper ();
    @Mock
    private MockSlingHttpServletRequest req;
    @Mock
    private MockSlingHttpServletResponse resp;

    @Mock
    private MyFirstService myFirstService;
    @InjectMocks
    private MyFirstServlet myFirstServlet;


    @Test
    @DisplayName ("Should test a successful post request")
    protected void testDoPost() throws ServletException, IOException {
        // Initializes mocks
        MockitoAnnotations.openMocks(this);

        // Prepares the test json
        String jsonPayload = "{\"clientName\": \"Huawei\", \"codeID\": \"123456\", \"isNewClient\": true}";
        when(req.getReader()).thenReturn(new BufferedReader (new StringReader (jsonPayload)));

        // Executes the method under test
        myFirstServlet.doPost(req, resp);

        // Checks response status
        verify(resp).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @DisplayName ("Should throw an IOException")
    protected void testDoPostIOException() throws ServletException, IOException {
        MyFirstServlet myFirstServletMock = mock (MyFirstServlet.class);
        doThrow (new IOException ("Test IOException")).when (myFirstServletMock).doPost (req, resp);

        assertThrows (IOException.class, () -> myFirstServletMock.doPost (req, resp));
    }

}
