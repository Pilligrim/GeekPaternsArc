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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Handler(method = HttpMethod.GET)
public class GetMethodHandler implements MethodHandler {
    private static final Logger logger = LoggerFactory.getLogger();
    private final Config config;

    public GetMethodHandler(Config config) {
        this.config = config;
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        Path path = Paths.get(config.getResource(), request.getUrl());

        if (!Files.exists(path)) {
            return HttpResponse.createBuilder().withStatus(ResponseCode.NOT_FOUND).withHeader("Content-Type", "text/html; charset=utf-8").withBody("<h1>Файл не найден!</h1>").build();
        }

        StringBuilder sb = new StringBuilder();
        try {
            Files.readAllLines(path).forEach(sb::append);
        } catch (IOException e) {
            logger.error(e);
        }

        return HttpResponse.createBuilder().withStatus(ResponseCode.OK).withHeader("Content-Type", "text/html; charset=utf-8").withBody(sb.toString()).build();
    }
}
