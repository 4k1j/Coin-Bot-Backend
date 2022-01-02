package com.coinbot.infra.exchange.api.client;

import com.coinbot.infra.auth.JwtTokenProvider;
import com.coinbot.infra.exchange.api.UpbitApiPaths;
import com.coinbot.infra.exchange.response.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RequiredArgsConstructor
@Component
public class ChanceClient {

    public static final String CHANCE_URL = UpbitApiPaths.BASE_SEVER_URL + "/orders/chance";

    private final JwtTokenProvider jwtTokenProvider;
    private final RestTemplate restTemplate;

    public List<AccountResponse> getChance() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtTokenProvider.createToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                CHANCE_URL,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<AccountResponse>>() {
                }).getBody();
    }
}
