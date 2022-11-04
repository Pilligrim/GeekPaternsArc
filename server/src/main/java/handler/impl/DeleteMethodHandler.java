package handler.impl;

import config.Config;
import domain.HttpMethod;
import domain.HttpRequest;
import domain.HttpResponse;
import domain.ResponseCode;
import handler.Handler;
import handler.MethodHandler;
import logger.Logger;
import logger.LoggerFactory;


@Handler(method = HttpMethod.DELETE)
public class DeleteMethodHandler implements MethodHandler {

    private static final Logger logger = LoggerFactory.getLogger();
    private final Config config;

    public DeleteMethodHandler(Config config) {
        this.config = config;
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        return HttpResponse.createBuilder()
                .withStatus(ResponseCode.OK)
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody("<h1>DELETE method handled</h1>")
                .build();
    }
}
