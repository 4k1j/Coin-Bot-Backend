package com.coinbot.application.restapi;


import com.coinbot.application.chance.ChanceService;
import com.coinbot.infra.exchange.response.ChanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChanceController {

    private final ChanceService chanceService;

    @GetMapping("/chance/{market}")
    public ResponseEntity<ChanceResponse> chance(@PathVariable String market) {
        try {
            return ResponseEntity.ok(chanceService.getOrderInfo(market));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
