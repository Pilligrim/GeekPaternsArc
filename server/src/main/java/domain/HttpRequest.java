package domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

public class HttpRequest<T> {

    private final Map<String, String> headers;
    private String method;
    private T body;

    private String url;

    public static Builder createBuilder() {
        return new Builder();
    }

    private HttpRequest(String method, String url, Map<String, String> headers, T body) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
    }

    public HttpRequest() {
        headers = new HashMap<>();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public String getHeader(String key) {
        return this.headers.get(key);
    }

    public void addHeaders(Map<String, String> headers) {
        Optional.ofNullable(headers).map(h -> h.entrySet()).orElse(new HashSet<>()).forEach(e -> this.headers.put(e.getKey(), e.getValue()));
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "HttpRequest{" + "method='" + method + '\'' + ", url='" + url + '\'' + ", headers=" + headers + ", body='" + body + '\'' + '}';
    }

    public static class Builder {

        private final Map<String, String> headers = new HashMap<>();
        private String method;
        private String url;
        private String body;

        private Builder() {
        }

        public Builder withMethod(String method) {
            this.method = method;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withHeader(String header, String value) {
            this.headers.put(header, value);
            return this;
        }

        public Builder withHeaders(Map<String, String> headers) {
            this.headers.putAll(headers);
            return this;
        }

        public Builder withBody(String body) {
            this.body = body;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(method, url, headers, body);
        }
    }
}
