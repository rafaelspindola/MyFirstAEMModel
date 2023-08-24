package com.webjump.trilha03.core.services;

import com.webjump.trilha03.core.models.MyFirstModelImpl;
import org.apache.sling.api.resource.Resource;

import java.io.IOException;

public interface MyFirstService {

    void saveClient(MyFirstModelImpl payloadData) throws IOException;
}
