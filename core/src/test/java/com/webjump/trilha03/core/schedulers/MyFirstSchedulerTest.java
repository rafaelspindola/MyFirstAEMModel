package com.webjump.trilha03.core.schedulers;

import org.apache.sling.api.resource.*;
import org.apache.sling.commons.scheduler.Scheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;

import java.util.List;

import static junitx.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith (MockitoExtension.class)
public class MyFirstSchedulerTest {
    @Mock
    private TestLogger logger = TestLoggerFactory.getTestLogger (getClass ());
    @Mock
    private Resource resource;
    @Mock
    private ModifiableValueMap valueMap;
    @Mock
    private Scheduler scheduler;
    @Mock
    private SchedulerConfig config;
    @Mock
    private ResourceResolver resourceResolver;
    @Mock
    private ResourceResolverFactory resourceResolverFactory;
    @InjectMocks
    private MyFirstScheduler myFirstScheduler;

    @Test
    void testRun() throws PersistenceException, LoginException {

        when(resourceResolverFactory.getServiceResourceResolver(any())).thenReturn(resourceResolver);
        when(resourceResolver.getResource(anyString())).thenReturn(resource);
        when(resource.adaptTo(ModifiableValueMap.class)).thenReturn(valueMap);

        myFirstScheduler.run();

        verify(valueMap).put("clientName", "Huawei");
        verify(valueMap).put("codeID", "00456");
        verify(valueMap).put("isNewClient", true);
        verify(resourceResolver).commit();
    }
}
