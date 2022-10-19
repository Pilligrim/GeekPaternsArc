import config.Config;
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

    private static final Logger logger = new ConsoleLogger();

    private final SocketService socketService;
    private final RequestParser requestParser;
    private final ResponseSerializer responseSerializer;

    private final Config config;

    public RequestHandler(SocketService socketService, RequestParser requestParser, ResponseSerializer responseSerializer, Config config) {
        this.socketService = socketService;
        this.requestParser = requestParser;
        this.responseSerializer = responseSerializer;
        this.config = config;
    }

    @Override
    public void run() {
        HttpRequest<String> request = requestParser.parse(socketService.readRequest());
        Path path = Paths.get(config.getWwwHome(), request.getUrl());
        HttpResponse<String> response;
        if (!Files.exists(path)) {
            response = HttpResponse.createBuilder().withStatus(ResponseCode.NOT_FOUND).withHeader("Content-Type", "text/html; charset=utf-8").withBody("<h1>Файл не найден!</h1>").build();
            socketService.writeResponse(responseSerializer.serialize(response));
            return;
        }
        try {
            StringBuilder sb = new StringBuilder();
            Files.readAllLines(path).forEach(sb::append);

            response = HttpResponse.createBuilder().withStatus(ResponseCode.OK).withHeader("Content-Type", "text/html; charset=utf-8").withBody(sb.toString()).build();
            socketService.writeResponse(responseSerializer.serialize(response));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("Client disconnected!");
    }
}
