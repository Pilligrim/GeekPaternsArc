package domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

public class HttpResponse<T> {

    private ResponseCode status;
    private Map<String, String> headers;
    private String contentType;
    private T body;

    public HttpResponse(ResponseCode statusCode, Map<String, String> headers, String contentType, T body) {
        this.status = statusCode;
        this.headers = headers;
        this.contentType = contentType;
        this.body = body;
    }

    public HttpResponse() {
        headers = new HashMap<>();
    }

    public ResponseCode getStatusCode() {
        return status;
    }

    public void setStatusCode(ResponseCode statusCode) {
        this.status = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getHeader(String key) {
        return this.headers.get(key);
    }

    public void addHeaders(Map<String, String> headers) {
        Optional.ofNullable(headers).map(h -> h.entrySet()).orElse(new HashSet<>()).forEach(e -> this.headers.put(e.getKey(), e.getValue()));
    }
    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

}
