package com.example.cloudeventsdemo.controller;

import com.example.cloudeventsdemo.dto.AddressDto;
import com.example.cloudeventsdemo.service.ConsoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/console")
public class ConsoleController {

    private ConsoleService consoleService;

    public ConsoleController(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    @PostMapping
    public ResponseEntity<Void> SendMessage(@RequestBody AddressDto message) {
        this.consoleService.createAddress(message);
        return ResponseEntity.ok().build();
    }
}
