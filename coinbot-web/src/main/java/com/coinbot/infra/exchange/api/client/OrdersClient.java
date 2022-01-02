package com.coinbot.infra.exchange.api.client;

import com.coinbot.infra.auth.JwtTokenProvider;
import com.coinbot.infra.exchange.api.UpbitApiPaths;
import com.coinbot.infra.exchange.response.OrdersResponse;
import com.coinbot.infra.exchange.request.OrdersRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class OrdersClient {

    public static final String ORDERS_URL = UpbitApiPaths.BASE_SEVER_URL + "/orders";

    private final JwtTokenProvider jwtTokenProvider;
    private final RestTemplate restTemplate;

    public OrdersResponse order(OrdersRequest request) throws NoSuchAlgorithmException {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtTokenProvider.createTokenWithParam(toQueryString(request)));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OrdersRequest> entity = new HttpEntity<>(request, headers);
        return restTemplate.exchange(
                ORDERS_URL,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<OrdersResponse>() {
                }).getBody();
    }

    private String toQueryString(OrdersRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> queryElements = new ArrayList<>();

        Map<String, String> params = objectMapper.convertValue(request, Map.class);
        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }
        return String.join("&", queryElements.toArray(new String[0]));
    }
}
