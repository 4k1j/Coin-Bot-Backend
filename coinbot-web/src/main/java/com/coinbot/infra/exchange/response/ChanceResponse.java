package com.coinbot.infra.exchange.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChanceResponse {

    private String bid_fee;
    private String market;
    private String marketId;
    private String marketName;
    private List<String> marketOrderTypes;
    private List<String> marketOrderSides;
    private Object marketBid ;
    private String marketBidCurrency;

}
