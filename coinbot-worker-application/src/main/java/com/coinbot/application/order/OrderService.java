package com.coinbot.application.order;

import com.coinbot.infra.exchange.response.CancelResponse;
import com.coinbot.infra.exchange.response.OrdersResponse;
import java.security.NoSuchAlgorithmException;

public interface OrderService {

  public OrdersResponse orders(OrderPosition position, String market, String volume, String price) throws NoSuchAlgorithmException;

  public OrdersResponse orders(OrderPosition position, String market, String volumeOrPrice) throws NoSuchAlgorithmException;
  public CancelResponse cancel(String uuid) throws NoSuchAlgorithmException;

}
