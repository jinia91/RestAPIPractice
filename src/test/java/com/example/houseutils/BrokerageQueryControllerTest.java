package com.example.houseutils;

import com.example.houseutils.policy.ActionType;
import com.example.houseutils.policy.OtherActionTypeFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BrokerageQueryControllerTest {

    @Autowired
    MockMvc mockMvc;

    static final String CALC_BROKERAGE_AMOUNT_URL = "http://localhost/v2/api/calc/brokerage";

    @Test
    public void 중개수수료매매조회성공테스트_V1() throws Exception {
        // given
        ActionType actionType = ActionType.PURCHASE;
        Long price = 1_000_000_000L;
        // when
        ResultActions perform = mockMvc.perform(
                get(String.format("/v1/api/calc/brokerage?actionType=%s&price=%d", actionType, price)));
        // then
        perform.andExpect(status().isOk())
                .andExpect(content().string("5000000"));
    }

    @Test
    public void 중개수수료매매조회성공테스트_V2() throws Exception {
        // given
        ActionType actionType = ActionType.PURCHASE;
        Long price = 1_000_000_000L;
        // when
        ResultActions perform = mockMvc.perform(
                get(String.format("/v2/api/calc/brokerage?actionType=%s&price=%d", actionType, price)));
        // then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("amount")
                        .value("5000000"));
        verifyLinksInJason(actionType, price, perform);
    }

    private void verifyLinksInJason(ActionType actionType, Long price, ResultActions perform) throws Exception {
        List<ActionType> otherActionTypes = OtherActionTypeFactory.getOtherActionTypesFrom(actionType);
        for (ActionType otherActionType : otherActionTypes) {
            perform.andExpect(jsonPath("_links.%s-policy-calculate.href", otherActionType)
                    .value(String.format("%s?actionType=%s&price=%d", CALC_BROKERAGE_AMOUNT_URL, otherActionType, price)));
        }
    }

    // 클라이언트 에러 4xx
    @Test
    public void 중개수수료매매조회실패테스트_V2_1() throws Exception {
        // given
        ActionType actionType = ActionType.PURCHASE;
        Long price = null;
        // when
        ResultActions perform = mockMvc.perform(
                get(String.format("/v2/api/calc/brokerage?actionType=%s&price=%d", actionType, price)));
        // then
        perform.andExpect(status().is4xxClientError());
    }

    // 클라이언트 에러 4xx
    @Test
    public void 중개수수료매매조회실패테스트_V2_2() throws Exception {
        // given
        ActionType actionType = null;
        Long price = 1_000_000_000L;
        // when
        ResultActions perform = mockMvc.perform(
                get(String.format("/v2/api/calc/brokerage?actionType=%s&price=%d", actionType, price)));
        // then
        perform.andExpect(status().is4xxClientError());
    }

    @Test
    public void 중개수수료임대조회테스트() throws Exception {
        // given
        ActionType actionType = ActionType.RENT;
        Long price = 1_000_000_000L;
        // when
        ResultActions perform = mockMvc.perform(
                get(String.format("/v1/api/calc/brokerage?actionType=%s&price=%d", actionType, price)));
        // then
        perform.andExpect(status().isOk())
                .andExpect(content().string("4000000"));
    }
}