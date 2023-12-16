package com.ismailcet.ordermanagment.cargoservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringToJsonConverter {
    public static JsonNode stringToJsonConverter(String request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode parser = mapper.readTree(request);
        return parser;
    }
}
