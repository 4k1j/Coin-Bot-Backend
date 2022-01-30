package com.coinbot.application.restapi;

import com.coinbot.application.order.OrderPosition;
import com.coinbot.application.order.OrderService;
import com.coinbot.infra.exchange.api.client.OrdersClient;
import com.coinbot.infra.exchange.request.OrdersRequest;
import com.coinbot.infra.exchange.response.CancelResponse;
import com.coinbot.infra.exchange.response.OrdersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
public class OrdersController {

    private final OrdersClient ordersClient;
    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<OrdersResponse> order() {
        try {
            return ResponseEntity.ok(orderService.orders(OrderPosition.BUY, "KRW-STEEM", "10", "500")); // 지정가 매수
//            return ResponseEntity.ok(orderService.orders(OrderPosition.BUY, "KRW-BTC", "5000")); // 시장가 매수
//            return ResponseEntity.ok(orderService.orders(OrderPosition.SELL, "KRW-BTC", "0.00017316", "60000000")); // 지정가 매도
//            return ResponseEntity.ok(orderService.orders(OrderPosition.SELL, "KRW-BTC", "0.00017316")); // 지정가 매도
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    @GetMapping("/cancel/{uuid}")
    public ResponseEntity<CancelResponse> order(@PathVariable String uuid) {
        try {
            return ResponseEntity.ok(orderService.cancel(uuid));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
