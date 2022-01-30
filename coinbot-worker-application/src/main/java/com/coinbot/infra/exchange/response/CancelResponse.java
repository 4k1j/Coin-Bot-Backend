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
public class CancelResponse {

    private String uuid;
    private String side;

    @JsonProperty("ord_type")
    private String orderType;
    private String price;
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
    @JsonProperty("executed_volume")
    private String executedVolume;
    @JsonProperty("trade_count")
    private String tradeCount;
}
