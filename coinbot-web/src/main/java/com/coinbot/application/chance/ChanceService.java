package com.coinbot.application.chance;

import com.coinbot.infra.exchange.api.client.ChanceClient;
import com.coinbot.infra.exchange.request.ChanceRequest;
import com.coinbot.infra.exchange.response.ChanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChanceService {

    private final ChanceClient chanceClient;

    public ChanceResponse getOrderInfo(String market) throws NoSuchAlgorithmException {
        return chanceClient.getChance(new ChanceRequest(market));
    }

}
