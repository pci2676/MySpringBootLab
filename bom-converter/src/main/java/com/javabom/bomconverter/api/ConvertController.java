package com.javabom.bomconverter.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ConvertController {

    @GetMapping("/api/{time}")
    public ResponseEntity<LocalDateTime> convertStringToLocalDateTime(@PathVariable(name = "time") LocalDateTime localDateTime) {
        return ResponseEntity.ok(localDateTime);
    }
}
