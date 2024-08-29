package com.luvsic.interceptor;

import com.luvsic.annotation.Log;
import com.luvsic.entity.TraceInfo;
import io.micrometer.common.lang.NonNullApi;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.UUID;

/**
 * @Author: zyy
 * @Date: 2024/8/29 09:34
 * @Version:
 * @Description:
 */
@Slf4j
@NonNullApi
public class LogInterceptor implements HandlerInterceptor {

    //子线程TraceInfo能够取到父线程的值
    private static final ThreadLocal<TraceInfo> TRACE_INFO_CONTEXT = new InheritableThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) return true;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Log logAno = handlerMethod.getMethodAnnotation(Log.class);
        if (Objects.nonNull(logAno)) {
            long start = System.currentTimeMillis();
            String uri = request.getRequestURI();
            String method = handlerMethod.getMethod().getDeclaringClass() + "#" + handlerMethod.getMethod();
            String traceId = UUID.randomUUID().toString();
            TraceInfo traceInfo = new TraceInfo(traceId, start, uri, method);
            MDC.put(TraceInfo.TRACE_ID, traceId);
            TRACE_INFO_CONTEXT.set(traceInfo);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Log logAno = handlerMethod.getMethodAnnotation(Log.class);
        if (Objects.nonNull(logAno)) {
            long end = System.currentTimeMillis();
            TraceInfo traceInfo = TRACE_INFO_CONTEXT.get();
            Long start = traceInfo.getStart();
            log.warn("reUri:{},reqMethod:{},耗时:{} ms", traceInfo.getRequestUri(), traceInfo.getRequestMethod(), end - start);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
