package com.webjump.trilha03.core.services;

import com.webjump.trilha03.core.models.MyFirstModelImpl;
import org.apache.sling.api.resource.Resource;

public interface MyFirstService {

    void saveClient(Resource resource, MyFirstModelImpl payloadData) throws Exception;
}
