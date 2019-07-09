package com.indraparkapi.resources;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractResourceTest {

    public String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
