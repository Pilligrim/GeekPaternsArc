package handler;


import domain.HttpRequest;
import domain.HttpResponse;
import service.RequestParser;
import service.ResponseSerializer;
import service.SocketService;

import java.io.IOException;
import java.util.List;

public class RequestHandler implements Runnable {
    private final MethodHandlerFactory methodHandlerFactory;

    private final SocketService socketService;
    private final RequestParser requestParser;


    private final ResponseSerializer responseSerializer;

    public RequestHandler(MethodHandlerFactory methodHandlerFactory, SocketService socketService, RequestParser requestParser, ResponseSerializer responseSerializer) {
        this.methodHandlerFactory = methodHandlerFactory;
        this.socketService = socketService;
        this.requestParser = requestParser;
        this.responseSerializer = responseSerializer;
    }

    @Override
    public void run() {
        HttpRequest httpRequest = requestParser.parse(socketService.readRequest());
        MethodHandler methodHandler = methodHandlerFactory.getHandler(httpRequest.getMethod());
        String rawResponse = responseSerializer.serialize(methodHandler.handle(httpRequest));
        socketService.writeResponse(rawResponse);
        try {
            socketService.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        System.out.println("Client disconnected!");
    }
}
