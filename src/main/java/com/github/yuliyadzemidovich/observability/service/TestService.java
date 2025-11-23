package com.github.yuliyadzemidovich.observability.service;

import com.github.yuliyadzemidovich.observability.repository.TestRepository;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    @SneakyThrows
    @WithSpan
    public void doSomething() {
        log.info("do something");
        Thread.sleep((long) (Math.random() * 200));

        testRepository.fakeDbCall();
    }
}
