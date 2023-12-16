package com.ismailcet.ordermanagment.inventoryservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StringToJsonConverter {
    public static JsonNode stringConverter(String request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode parser = mapper.readTree(request);
        return parser;
    }
}
