package com.coinbot.application.order;

import com.coinbot.infra.exchange.api.client.OrdersClient;
import com.coinbot.infra.exchange.request.CancelRequest;
import com.coinbot.infra.exchange.request.OrdersRequest;
import com.coinbot.infra.exchange.response.CancelResponse;
import com.coinbot.infra.exchange.response.OrdersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrdersClient ordersClient;

    /**
     * 지정가 주문
     * @param position: 매수, 매도 구분
     * @param market: 마켓 명
     * @param volume: 주문량
     * @param price: 가격
     */
    public OrdersResponse orders(OrderPosition position, String market, String volume, String price) throws NoSuchAlgorithmException {
        OrdersRequest request = OrdersRequest.builder()
                .side(position.getValue())
                .market(market)
                .volume(volume)
                .price(price)
                .orderType("limit")
                .build();
        return ordersClient.order(request);
    }

    /**
     * 시장가 주문
     * @param market: 마켓 명
     * @param volumeOrPrice: 매수일 경우 -> price(주문 가격), 매도일 경우 -> volume(주문량)
     */
    public OrdersResponse orders(OrderPosition position, String market, String volumeOrPrice) throws NoSuchAlgorithmException {
        String orderType = null;
        String price = null;
        String volume = null;

        if (position == OrderPosition.BUY) {
            orderType = "price";
            price = volumeOrPrice;
        }
        else if (position == OrderPosition.SELL) {
            orderType = "market";
            volume = volumeOrPrice;
        }
        OrdersRequest request = OrdersRequest.builder()
                .side(position.getValue())
                .market(market)
                .price(price)
                .volume(volume)
                .orderType(orderType)
                .build();
        return ordersClient.order(request);
    }

    public CancelResponse cancel(String uuid) throws NoSuchAlgorithmException {
        return ordersClient.cancel(new CancelRequest(uuid));
    }
}
