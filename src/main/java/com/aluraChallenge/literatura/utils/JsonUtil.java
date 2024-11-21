package com.aluraChallenge.literatura.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T fromJson(String json, TypeReference<T> typeReference) throws JsonProcessingException{
        return objectMapper.readValue(json, typeReference);
    }

    public static String toJson(Object object) throws JsonProcessingException{
        return objectMapper.writeValueAsString(object);
    }
}