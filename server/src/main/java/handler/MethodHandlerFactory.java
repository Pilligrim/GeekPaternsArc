package handler;

import config.Config;
import domain.HttpMethod;
import logger.Logger;
import logger.LoggerFactory;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.*;

public class MethodHandlerFactory {
    private static final Logger logger = LoggerFactory.getLogger();
    private static final String HANDLERS_PACKAGE = "handler";
    private final Map<HttpMethod, MethodHandler> handlerMap;

    public MethodHandlerFactory(Config config) {
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

    public MethodHandler getHandler(String httpMethod) {
        return Optional.ofNullable(httpMethod)
                .map(HttpMethod::valueOf)
                .map(handlerMap::get)
                .orElseThrow(() -> new RuntimeException(String.format("Unknown http method %s", httpMethod)));
    }
}
