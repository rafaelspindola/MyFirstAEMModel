package com.webjump.trilha03.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.osgi.framework.Constants;

import static junitx.framework.Assert.assertEquals;

@ExtendWith ({AemContextExtension.class, MockitoExtension.class})
public class MyFirstModelImplTest {

    private final AemContext ctx = new AemContext ();

    @BeforeEach
    public void setUp() throws Exception {
        ctx.addModelsForClasses (MyFirstModelImpl.class);
        ctx.load ().json ("/models/impl/MyFirstModelImpl.json", "/content");
    }

    @Test
    public void getClientName() {
        ctx.currentResource ("/content/my-first-model");
        MyFirstModel myFirstModel = ctx.request().adaptTo(MyFirstModel.class);

        final String expected = "Intel";
        String actual = myFirstModel.getClientName ();

        assertEquals(expected, actual);
    }

    @Test
    public void getCodeID() {
        ctx.currentResource ("/content/my-first-model");
        MyFirstModel myFirstModel = ctx.request ().adaptTo (MyFirstModel.class);

        final String expected = "123456";
        String actual = myFirstModel.getCodeID ();

        assertEquals (expected, actual);
    }

    @Test
    public void getIsNewClient() {
        ctx.currentResource ("/content/my-first-model");
        MyFirstModel myFirstModel = ctx.request ().adaptTo (MyFirstModel.class);

        final boolean expected = false;
        boolean actual = myFirstModel.getIsNewClient ();

        assertEquals (expected, actual);
    }
}
