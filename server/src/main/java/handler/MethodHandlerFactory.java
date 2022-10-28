package handler;


import config.Config;
import service.ResponseSerializer;
import service.SocketService;

public class MethodHandlerFactory {

    public static MethodHandler create(SocketService socketService, ResponseSerializer responseSerializer, Config config) {
        PutMethodHandler putHandler = new PutMethodHandler("PUT", null, socketService, responseSerializer, config);
        PostMethodHandler postHandler = new PostMethodHandler("POST", putHandler, socketService, responseSerializer, config);
        return new GetMethodHandler("GET", postHandler, socketService, responseSerializer, config);
    }
}
