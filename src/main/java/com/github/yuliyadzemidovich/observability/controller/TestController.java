package com.github.yuliyadzemidovich.observability.controller;

import com.github.yuliyadzemidovich.observability.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @SneakyThrows
    @GetMapping("/test")
    public String test() {
        log.info("controller received test call");
        Thread.sleep((long) (Math.random() * 100));
        testService.doSomething();
        return "test";
    }
}
