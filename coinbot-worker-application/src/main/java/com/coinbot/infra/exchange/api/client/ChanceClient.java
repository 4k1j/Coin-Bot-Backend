package com.coinbot.infra.exchange.api.client;

import com.coinbot.infra.auth.JwtTokenProvider;
import com.coinbot.infra.exchange.api.UpbitApiPaths;
import com.coinbot.infra.exchange.request.ChanceRequest;
import com.coinbot.infra.exchange.response.ChanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.security.NoSuchAlgorithmException;
import java.util.List;


@RequiredArgsConstructor
@Component
public class ChanceClient {

    public static final String CHANCE_URL = UpbitApiPaths.BASE_SEVER_URL + "/orders/chance";

    private final JwtTokenProvider jwtTokenProvider;
    private final RestTemplate restTemplate;

    public ChanceResponse getChance(ChanceRequest request) throws NoSuchAlgorithmException {
        HttpHeaders headers = new HttpHeaders();
        String queryString = ClientHelper.toQueryString(request);
        headers.setBearerAuth(jwtTokenProvider.createTokenWithParam(queryString));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                CHANCE_URL + "?" + queryString,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<ChanceResponse>() {
                }).getBody();
    }
}
