package com.coinbot.infra.exchange.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrdersRequest implements Request {

    private String market;

    private String side;    // bid: 매수, ask: 매도

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String volume;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String price;

    @JsonProperty("ord_type")
    private String orderType;
}
