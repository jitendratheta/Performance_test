package com.flipkart;

/**
 * Created by jitendra.k on 13/07/16.
 */
public class Response {
    private int responseCode;
    private String responseBody;
    private long responseTime;

    @Override
    public String toString() {
        return "Response{" +
            "responseCode=" + responseCode +
            ", responseBody='" + responseBody + '\'' +
            ", responseTime=" + responseTime +
            '}';
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }
}
