package com.github.cherry.http.client.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.github.cherry.http.client.event.ProgressListener;

public abstract class WebServiceRequest {

    public static final WebServiceRequest NOOP = new WebServiceRequest() {
    };

    private ProgressListener progressListener = ProgressListener.NOOP;

    private Map<String, String> parameters = new LinkedHashMap<String, String>();

    private Map<String, String> headers = new LinkedHashMap<String, String>();

    public void setProgressListener(ProgressListener progressListener) {
        this.progressListener = (progressListener == null) ? ProgressListener.NOOP : progressListener;
    }

    public ProgressListener getProgressListener() {
        return progressListener;
    }

    public <T extends WebServiceRequest> T withProgressListener(ProgressListener progressListener) {
        setProgressListener(progressListener);
        @SuppressWarnings("unchecked")
        T t = (T) this;
        return t;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(String key, String value) {
        this.parameters.put(key, value);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }
}
