import domain.HttpRequest;
import domain.HttpResponse;
import domain.ResponseCode;
import logger.ConsoleLogger;
import logger.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestHandler implements Runnable {

    private static final String WWW = "C:\\Users\\shpn\\IdeaProjects\\GeekPatternsArc\\server\\www";

    private static final Logger logger = new ConsoleLogger();

    private final SocketService socketService;
    private final RequestParser requestParser;

    private final ResponseSerializer responseSerializer;

    public RequestHandler(SocketService socketService, RequestParser requestParser, ResponseSerializer responseSerializer) {
        this.socketService = socketService;
        this.requestParser = requestParser;
        this.responseSerializer = responseSerializer;
    }

    @Override
    public void run() {
        HttpRequest<String> request = requestParser.parse(socketService.readRequest());
        Path path = Paths.get(WWW, request.getUrl());
        HttpResponse<String> response = new HttpResponse<>();
        if (!Files.exists(path)) {
            response.setStatusCode(ResponseCode.NOT_FOUND);
            response.addHeader("Content-Type", "text/html; charset=utf-8");
            response.setBody("<h1>Файл не найден!</h1>");
            socketService.writeResponse(responseSerializer.serialize(response));
            return;
        }
        try {
            StringBuilder sb = new StringBuilder();
            Files.readAllLines(path).forEach(sb::append);

            response.setStatusCode(ResponseCode.OK);
            response.addHeader("Content-Type", "text/html; charset=utf-8");
            response.setBody(sb.toString());
            socketService.writeResponse(responseSerializer.serialize(response));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("Client disconnected!");
    }
}
