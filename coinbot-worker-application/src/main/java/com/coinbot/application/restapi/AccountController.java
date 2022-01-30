package com.coinbot.application.restapi;

import com.coinbot.infra.exchange.api.client.AccountClient;
import com.coinbot.infra.exchange.response.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AccountController {
    private final AccountClient accountClient;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountResponse>> showAllAccounts() {
        return ResponseEntity.ok(accountClient.getAccounts());
    }
}

