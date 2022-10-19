package domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

public class HttpResponse<T> {

    private ResponseCode status;
    private Map<String, String> headers;
    private T body;

    private HttpResponse(ResponseCode statusCode, Map<String, String> headers,  T body) {
        this.status = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public HttpResponse() {
        headers = new HashMap<>();
    }
    public static Builder createBuilder() {
        return new Builder();
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

    public static class Builder {

        private final Map<String, String> headers = new HashMap<>();
        private ResponseCode status;
        private String body;

        private Builder() {
        }

        public Builder withStatus(ResponseCode status) {
            this.status = status;
            return this;
        }

        public Builder withHeader(String header, String value) {
            this.headers.put(header, value);
            return this;
        }

        public Builder withBody(String body) {
            this.body = body;
            return this;
        }

        public HttpResponse build() {
            return new HttpResponse(status, headers, body);
        }

    }
}
