package com.luvsic.entity;

/**
 * @Author: zyy
 * @Date: 2024/8/29 09:34
 * @Version:
 * @Description:
 */
public class TraceInfo {
    public static final String TRACE_ID = "TRACE_ID";
    private String traceId;
    private Long start;
    private String requestUri;
    private String requestMethod;

    public TraceInfo(String traceId, Long start, String requestUri, String requestMethod) {
        this.traceId = traceId;
        this.start = start;
        this.requestUri = requestUri;
        this.requestMethod = requestMethod;
    }

    public TraceInfo() {
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    @Override
    public String toString() {
        return "TraceInfo{" +
                "traceId='" + traceId + '\'' +
                ", start=" + start +
                ", requestUri='" + requestUri + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                '}';
    }
}
