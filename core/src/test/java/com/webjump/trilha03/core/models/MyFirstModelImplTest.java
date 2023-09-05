package com.webjump.trilha03.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    // Sets up AEM mocks and context
    private final AemContext ctx = new AemContext ();

    private MyFirstModel myFirstModel;

    @BeforeEach
    public void setUp() throws Exception {
        // Adds models for the classes being tested
        ctx.addModelsForClasses (MyFirstModelImpl.class);
        // Loads JSON data representing content into the testing context
        ctx.load ().json ("/models/impl/MyFirstModelImpl.json", "/content");
        ctx.currentResource ("/content/my-first-model");
        myFirstModel = ctx.request().adaptTo(MyFirstModel.class);
    }

    @Test
    @DisplayName ("Should match expected and actual clientName values.")
    public void getClientName() {
        final String expected = "Intel";
        String actual = myFirstModel.getClientName ();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName ("Should match expected and actual codeID values.")
    public void getCodeID() {
        final String expected = "123456";
        String actual = myFirstModel.getCodeID ();

        assertEquals (expected, actual);
    }

    @Test
    @DisplayName ("Should match expected and actual isNewClient values.")
    public void getIsNewClient() {
        final boolean expected = false;
        boolean actual = myFirstModel.getIsNewClient ();

        assertEquals (expected, actual);
    }
}
