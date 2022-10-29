package handler;

import config.Config;
import domain.HttpMethod;
import domain.HttpRequest;
import logger.Logger;
import logger.LoggerFactory;
import org.reflections.Reflections;
import service.ResponseSerializer;

import java.lang.reflect.Constructor;
import java.util.*;

public class MethodHandlerReceiver {
    private static final Logger logger = LoggerFactory.getLogger();
    private static final String HANDLERS_PACKAGE = "handler";
    private final Map<HttpMethod, MethodHandler> handlerMap;

    private final ResponseSerializer responseSerializer;

    public MethodHandlerReceiver(Config config, ResponseSerializer responseSerializer) {
        this.responseSerializer = responseSerializer;
        handlerMap = new HashMap<>();
        Reflections reflections = new Reflections(HANDLERS_PACKAGE);
        List<Class<?>> classes = new ArrayList<>(reflections.getTypesAnnotatedWith(Handler.class));
        MethodHandler handler;
        try {
            for (Class<?> clazz : classes) {
                Constructor<?> constructor = clazz.getConstructor(Config.class);
                handler = (MethodHandler) constructor.newInstance(config);
                handlerMap.put(getMethod(clazz), handler);
            }
        } catch (Exception e) {
            logger.error(e);
        }

    }

    private HttpMethod getMethod(Class<?> clazz) {
        return clazz.getAnnotation(Handler.class).method();
    }

    public String handle(HttpRequest httpRequest) {
        return Optional.ofNullable(httpRequest)
                .map(HttpRequest::getMethod)
                .map(HttpMethod::valueOf)
                .map(handlerMap::get)
                .map(m -> m.handle(httpRequest))
                .map(responseSerializer::serialize)
                .orElseThrow(() -> new RuntimeException(String.format("Unknown http method %s", httpRequest.getMethod())));
    }
}
