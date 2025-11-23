package com.github.yuliyadzemidovich.observability.repository;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestRepository {

    @SneakyThrows
    @WithSpan
    public void fakeDbCall() {
        log.info("starting fake DB call...");
        Thread.sleep((long) (Math.random() * 500));
        log.info("fake DB call finished");
    }
}
