package com.webjump.trilha03.core.services;

import com.webjump.trilha03.core.models.MyFirstModelImpl;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith ({MockitoExtension.class, AemContextExtension.class})
public class MyFirstServiceTest {

    @Mock
    private Resource resource;

    @Mock
    private ResourceResolver resourceResolver;

    @Mock
    private ModifiableValueMap valueMap;
    @InjectMocks
    private MyFirstServiceImpl myFirstService;
    @Test
    protected void testSaveClient() throws Exception {
        // Initializes mocks
        MockitoAnnotations.openMocks (this);

        // Mocks resource, resourceResolver and ModifiableValueMap
        when(resource.getResourceResolver()).thenReturn(resourceResolver);
        when(resource.adaptTo(ModifiableValueMap.class)).thenReturn (valueMap);

        // Payload
        MyFirstModelImpl payloadData = new MyFirstModelImpl ("Huawei", "123456", true);

        // Call the method under test
        myFirstService.saveClient(payloadData);

        // Verify business logic
        verify (valueMap).put (eq ("clientName"), eq ("Huawei"));
        verify (valueMap).put (eq ("codeID"), eq ("123456"));
        verify (valueMap).put (eq ("isNewClient"), eq (true));
        verify (resourceResolver).commit ();
    }
}
