package com.mins.springrecipes.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AsyncMeasurementInterceptor implements AsyncHandlerInterceptor {

    private static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("preHandle() 호출 !!");

        if(request.getAttribute(START_TIME) == null)
            request.setAttribute(START_TIME, System.currentTimeMillis());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("postHandle() 호출 !!");

//        long startTime = (long) request.getAttribute(START_TIME);
//        request.removeAttribute(START_TIME);
//        long endTime = System.currentTimeMillis();
//
//        log.info("Response-Processing-Time: " + (endTime - startTime) + "ms");
//        log.info("Response-Processing-Thread: " + Thread.currentThread().getName());
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("afterConcurrentHandlingStarted() 호출 !!");

//        long startTime = (long) request.getAttribute(START_TIME);
//        request.setAttribute(START_TIME, System.currentTimeMillis());
//        long endTime = System.currentTimeMillis();
//
//        log.info("Response-Processing-Time: " + (endTime - startTime) + "ms");
//        log.info("Response-Processing-Thread: " + Thread.currentThread().getName());
    }
}