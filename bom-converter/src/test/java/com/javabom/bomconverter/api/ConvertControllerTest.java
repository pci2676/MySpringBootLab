package com.javabom.bomconverter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.javabom.bomconverter.dto.RequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("StringToLocalDateTimeConverter2를 이용해서 PathVariable을 변환한다.")
    @Test
    void convertStringToLocalDateTime() throws Exception {
        //given
        String time = "2020-12-08T12:22:33";

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
                () -> assertThat(localDateTime.getYear()).isEqualTo(2020),
                () -> assertThat(localDateTime.getMonthValue()).isEqualTo(12),
                () -> assertThat(localDateTime.getDayOfMonth()).isEqualTo(8)
        );

        assertAll(
                "시 분 초",
                () -> assertThat(localDateTime.getHour()).isEqualTo(12),
                () -> assertThat(localDateTime.getMinute()).isEqualTo(22),
                () -> assertThat(localDateTime.getSecond()).isEqualTo(33)
        );
    }

    @DisplayName("StringToLocalDateTimeConverter2를 이용해서 Dto의 LocalDateTime 으로 변환한다.")
    @Test
    void convertStringToLocalDateTime2() throws Exception {
        //given
        String time = "2020-12-08T12:22:33";

        //when
        MvcResult result = mockMvc.perform(get("/api/convert?time={time}&id={id}", time, 1L))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        RequestDto requestDto = objectMapper.readValue(contentAsString, RequestDto.class);

        Long id = requestDto.getId();
        LocalDateTime localDateTime = requestDto.getTime();

        assertThat(id).isEqualTo(1L);
        //then
        assertAll(
                "년 월 일",
                () -> assertThat(localDateTime.getYear()).isEqualTo(2020),
                () -> assertThat(localDateTime.getMonthValue()).isEqualTo(12),
                () -> assertThat(localDateTime.getDayOfMonth()).isEqualTo(8)
        );

        assertAll(
                "시 분 초",
                () -> assertThat(localDateTime.getHour()).isEqualTo(12),
                () -> assertThat(localDateTime.getMinute()).isEqualTo(22),
                () -> assertThat(localDateTime.getSecond()).isEqualTo(33)
        );
    }
}