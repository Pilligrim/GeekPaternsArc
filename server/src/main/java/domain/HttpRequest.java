package domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

public class HttpRequest<T> {

    private final Map<String, String> headers;
    private String method;
    private String path;
    private T body;

    private String url;

    public HttpRequest(Map<String, String> headers, String method, String path, T body, String url) {
        this.headers = headers;
        this.method = method;
        this.path = path;
        this.body = body;
        this.url = url;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
}
