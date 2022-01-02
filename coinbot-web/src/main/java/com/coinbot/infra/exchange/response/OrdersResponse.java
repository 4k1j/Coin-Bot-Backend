package com.coinbot.infra.exchange.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponse {

    private String uuid;

    private String side;

    @JsonProperty("ord_type")
    private String orderType;
    private String price;

    @JsonProperty("avg_price")
    private String avgPrice;

    private String state;

    private String market;

    @JsonProperty("create_at")
    private String createAt;

    private String volume;

    @JsonProperty("remaining_volume")
    private String remainingVolume;

    @JsonProperty("reserved_fee")
    private String reservedFee;

    @JsonProperty("remaining_fee")
    private String remainingFee;

    @JsonProperty("paid_fee")
    private String paidFee;

    private String locked;

    @JsonProperty("execute_volume")
    private String executedVolume;

    private int tradeCount;
}
