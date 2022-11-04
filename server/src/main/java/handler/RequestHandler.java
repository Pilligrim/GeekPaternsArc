package handler;


import domain.HttpRequest;
import service.RequestParser;
import service.SocketService;

import java.io.IOException;

public class RequestHandler implements Runnable {
    private final MethodHandlerReceiver methodHandlerReceiver;


    private final SocketService socketService;
    private final RequestParser requestParser;




    public RequestHandler(MethodHandlerReceiver methodHandlerReceiver, SocketService socketService, RequestParser requestParser) {
        this.methodHandlerReceiver = methodHandlerReceiver;
        this.socketService = socketService;
        this.requestParser = requestParser;
    }

    @Override
    public void run() {
        HttpRequest httpRequest = requestParser.parse(socketService.readRequest());
        socketService.writeResponse(methodHandlerReceiver.handle(httpRequest));
        try {
            socketService.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        System.out.println("Client disconnected!");
    }
}
