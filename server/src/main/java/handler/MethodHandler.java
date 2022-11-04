package handler;

import domain.HttpRequest;
import domain.HttpResponse;

public interface MethodHandler {
    HttpResponse handle(HttpRequest request);
}
