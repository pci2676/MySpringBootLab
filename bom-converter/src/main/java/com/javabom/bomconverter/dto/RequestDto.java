package com.javabom.bomconverter.dto;

import java.time.LocalDateTime;

public class RequestDto {
    private Long id;
    private LocalDateTime time;

    private RequestDto() {
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
