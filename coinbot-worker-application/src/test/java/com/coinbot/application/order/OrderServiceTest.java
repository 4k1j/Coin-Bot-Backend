package com.coinbot.application.order;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import com.coinbot.infra.auth.JwtTokenProvider;
import com.coinbot.infra.exchange.api.client.OrdersClient;
import com.coinbot.infra.exchange.request.OrdersRequest;
import com.coinbot.infra.exchange.response.OrdersResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.security.NoSuchAlgorithmException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

@AutoConfigureWebClient(registerRestTemplate = true)
@RestClientTest(OrdersClient.class)
public class OrderServiceTest {

  @MockBean
  private JwtTokenProvider jwtTokenProvider;
  @Autowired
  private OrdersClient ordersClient;
  @Autowired
  private MockRestServiceServer mockServer;
  @Autowired
  private ObjectMapper objectMapper;

  OrdersResponse ordersResponse = OrdersResponse.builder().side("bid").price("500.0")
      .state("wait").market("KRW-ONT").volume("10.0").locked("5002.5").tradeCount(0)
      .orderType("limit")
      .avgPrice(null).createAt(null).remainingVolume("2.5").reservedFee("2.5")
      .remainingFee("2.5").
      paidFee("0.0").executedVolume(null).build();

  @DisplayName("지정가로 주문한다.")
  @Test
  public void limitOrder() throws JsonProcessingException, NoSuchAlgorithmException {
    // given: api response를 정의한다.
    String expectedResult = objectMapper.writeValueAsString(ordersResponse);

    // when: Api를 호출한다.
    mockServer.expect(requestTo(OrdersClient.ORDERS_URL))
        .andRespond(withSuccess(expectedResult, MediaType.APPLICATION_JSON));
    OrdersRequest request = OrdersRequest.builder().side("bid").market("KRW-ONT").volume("10")
        .price("500")
        .orderType("limit").build();
    OrdersResponse actual = ordersClient.order(request);

    // then: 결과를 확인한다.
    Assertions.assertThat(actual.getSide()).isEqualTo(ordersResponse.getSide());
  }
}

