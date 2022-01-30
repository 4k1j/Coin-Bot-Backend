package com.coinbot.infra.exchange.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CancelRequest implements Request{

    private String uuid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String identifier;

    public CancelRequest(String uuid) {
        this.uuid = uuid;
    }
}
