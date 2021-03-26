package com.mins.springrecipes.controller;

import com.mins.springrecipes.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hello")
public class HelloController {

    private final HelloService helloService;
    private final TaskExecutor taskExecutor;

    @GetMapping("responsebody-emitter")
    public ResponseBodyEmitter findResponseBodyEmitter() {
        final ResponseBodyEmitter emitter = new ResponseBodyEmitter();

        taskExecutor.execute(() -> {
            List<String> list = helloService.list();
            try {
                for (String s : list)
                    emitter.send(s);

                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }

    @GetMapping("sse-emitter")
    public SseEmitter findSseEmitter() {
        final SseEmitter emitter = new SseEmitter();

        taskExecutor.execute(() -> {
            List<String> list = helloService.list();
            try {
                for (String s : list)
//                    emitter.send(s);
                    emitter.send(SseEmitter.event().id(String.valueOf(s.hashCode())).data(s));

                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }

}
