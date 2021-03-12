package com.mins.springrecipes.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

@Slf4j
public class MeasurementInterceptor implements HandlerInterceptor {

    // handler 요청 처리 전
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getRemoteAddr();
        log.info(String.format("START URL ==> %-35s TIME ==> %-25s IP ==> %s", request.getRequestURI(), now().format(ofPattern("yyyy-MM-dd HH:mm:ss")), ip));
        request.setAttribute("startTime", System.nanoTime());

        // true를 반환해야 DispatcherServlet이 요청 처리를 계속 진행하며,
        // false일 경우 핸들러까지 요청이 전달되지 않고 바로 응답을 반환함
        return true;
    }

    // handler 요청 처리 후
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long endTime = System.nanoTime();
        long startTime = (long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        log.info(String.format("END URL   ==> %-35s {executed in %d msec}", request.getRequestURI(), (endTime - startTime) / 1000000));
    }

    // View 렌더링 완료 후
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}