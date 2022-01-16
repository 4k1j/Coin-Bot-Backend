package com.coinbot.infra.exchange.api.client;

import com.coinbot.infra.auth.JwtTokenProvider;
import com.coinbot.infra.exchange.api.UpbitApiPaths;
import com.coinbot.infra.exchange.request.CancelRequest;
import com.coinbot.infra.exchange.request.Request;
import com.coinbot.infra.exchange.response.CancelResponse;
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
    public static final String ORDER_URL = UpbitApiPaths.BASE_SEVER_URL + "/order";

    private final JwtTokenProvider jwtTokenProvider;
    private final RestTemplate restTemplate;

    public OrdersResponse order(OrdersRequest request) throws NoSuchAlgorithmException {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtTokenProvider.createTokenWithParam(ClientHelper.toQueryString(request)));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OrdersRequest> entity = new HttpEntity<>(request, headers);
        return restTemplate.exchange(
                ORDERS_URL,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<OrdersResponse>() {
                }).getBody();
    }

    public CancelResponse cancel(CancelRequest request) throws NoSuchAlgorithmException {
        HttpHeaders headers = new HttpHeaders();
        String queryString = ClientHelper.toQueryString(request);
        headers.setBearerAuth(jwtTokenProvider.createTokenWithParam(queryString));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                ORDER_URL + "?" + queryString,
                HttpMethod.DELETE,
                entity,
                new ParameterizedTypeReference<CancelResponse>() {
                }).getBody();
    }
}

