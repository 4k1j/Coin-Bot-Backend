package com.coinbot.application.order;

import lombok.Getter;

@Getter
public enum OrderPosition {

    BUY("bid"),
    SELL("ask");

    private final String value;

    OrderPosition(String value) {
        this.value = value;
    }
}
