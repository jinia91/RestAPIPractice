package com.example.houseutils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HealthCheckControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("헬스체크 테스트")
    public void healthCheckTest() throws Exception {
        // given
        // when
        ResultActions perform = mockMvc.perform(get("/api/ping"));
        // then
        perform.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("OK"));
    }
}