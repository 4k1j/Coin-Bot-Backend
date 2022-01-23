package com.coinbot.infra.exchange.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("bid_fee")
    private String bidFee;
    @JsonProperty("ask_fee")
    private String askFee;
    private Object market;
    @JsonProperty("bid_account")
    private Object bidAccount;
    @JsonProperty("ask_account")
    private Object askAccount;

}
