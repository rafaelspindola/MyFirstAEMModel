package com.webjump.trilha03.core.utils;

import com.adobe.xfa.Obj;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import java.util.HashMap;
import java.util.Map;
// This class provides utility methods to handle resource resolver issues regarding user access
public final class ResolverUtils {

    private ResolverUtils() {}

    public static final String WEBJUMP_USER = "webjump";

    public static ResourceResolver newResolver(ResourceResolverFactory resourceResolverFactory)
            throws LoginException {
        final Map<String, Object> paramMap = new HashMap<String, Object> ();
        paramMap.put (ResourceResolverFactory.SUBSERVICE, WEBJUMP_USER);
        ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver (paramMap);
        return resolver;
    }

}
