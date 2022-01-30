package com.coinbot.infra.exchange.api.client;

import com.coinbot.infra.exchange.request.Request;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientHelper {

    public static String toQueryString(Request request) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> queryElements = new ArrayList<>();

        Map<String, String> params = objectMapper.convertValue(request, Map.class);
        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }
        return String.join("&", queryElements.toArray(new String[0]));
    }
}
