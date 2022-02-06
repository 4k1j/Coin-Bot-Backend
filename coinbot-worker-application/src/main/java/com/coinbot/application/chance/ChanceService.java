package com.coinbot.application.chance;

import com.coinbot.infra.exchange.response.ChanceResponse;
import java.security.NoSuchAlgorithmException;

public interface ChanceService {

  public ChanceResponse getOrderInfo(String market) throws NoSuchAlgorithmException;

}
