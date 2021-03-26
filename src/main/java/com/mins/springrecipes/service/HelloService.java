package com.mins.springrecipes.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class HelloService {

    public String test() {
        log.info("test");
        return "test";
    }

    public List<String> list() {
        log.info("list");
        return Arrays.asList("a", "b", "c", "d");
    }

}
