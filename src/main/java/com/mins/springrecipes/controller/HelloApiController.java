package com.mins.springrecipes.controller;

import com.mins.springrecipes.service.HelloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.yaml.snakeyaml.emitter.Emitter;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hello")
public class HelloApiController {

    private final HelloService helloService;
    private AsyncListenableTaskExecutor taskExecutor;

    @PostMapping("blocking")
    public String submit() throws InterruptedException {
        Thread.sleep(3000L);
        helloService.test();
        return "result";
    }

    @PostMapping("/callable")
    public Callable<String> submitCallable() {
        log.info("Main: " + Thread.currentThread().getName());
        return () -> {
            log.info("Work: " + Thread.currentThread().getName());
            Thread.sleep(3000L);
            helloService.test();
            return "result";
        };
    }

    @PostMapping("/completable-future")
    public CompletableFuture<String> submitCompletableFuture() {
        System.out.println("Main: " + Thread.currentThread().getName());

        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Work: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            helloService.test();
            return "result";
        });
    }

    @PostMapping("/listenable-future")
    public ListenableFuture<String> submitListenableFuture() {
        return taskExecutor.submitListenable(() -> {
            System.out.println("Work: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            helloService.test();
            return "result";
        });
    }

}
