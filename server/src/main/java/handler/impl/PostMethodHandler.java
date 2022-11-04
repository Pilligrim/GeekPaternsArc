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

@Handler(method = HttpMethod.POST)
public class PostMethodHandler implements MethodHandler {
    private static final Logger logger = LoggerFactory.getLogger();
    private final Config config;

    public PostMethodHandler(Config config) {
        this.config = config;
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        return HttpResponse.createBuilder().withStatus(ResponseCode.OK).withHeader("Content-Type", "text/html; charset=utf-8").withBody("<h1>POST method handled</h1>").build();
    }

}
