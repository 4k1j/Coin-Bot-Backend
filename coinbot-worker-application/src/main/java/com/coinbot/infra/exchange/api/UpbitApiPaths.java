package com.coinbot.infra.exchange.api;

public class UpbitApiPaths {

    public static final String UPBIT_GET_ACCOUNTS_URL = "https://api.upbit.com/v1/accounts";
    public static final String UPBIT_POST_ORDERS_URL = "https://api.upbit.com/v1/orders";

    private static final String SEVER_URL = "https://api.upbit.com";

    private static final String API_VERSION = "/v1";

    public static final String BASE_SEVER_URL = SEVER_URL + API_VERSION;
}
