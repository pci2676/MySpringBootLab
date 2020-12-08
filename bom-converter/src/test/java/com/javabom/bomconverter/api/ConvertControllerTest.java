package com.javabom.bomconverter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ConvertController.class)
class ConvertControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void convertStringToLocalDateTime() throws Exception {
        //given
        String time = "2020-12-08 12:22:33";

        //when
        MvcResult result = mockMvc.perform(get("/api/{time}", time))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        LocalDateTime localDateTime = objectMapper.readValue(contentAsString, LocalDateTime.class);

        //then
        assertAll(
                "년 월 일",
                () -> Assertions.assertThat(localDateTime.getYear()).isEqualTo(2020),
                () -> Assertions.assertThat(localDateTime.getMonthValue()).isEqualTo(12),
                () -> Assertions.assertThat(localDateTime.getDayOfMonth()).isEqualTo(8)
        );

        assertAll(
                "시 분 초",
                () -> Assertions.assertThat(localDateTime.getHour()).isEqualTo(12),
                () -> Assertions.assertThat(localDateTime.getMinute()).isEqualTo(22),
                () -> Assertions.assertThat(localDateTime.getSecond()).isEqualTo(33)
        );
    }
}