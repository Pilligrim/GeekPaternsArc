import logger.ConsoleLogger;
import logger.Logger;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RequestHandler implements Runnable {

    private static final String WWW = "C:\\Users\\shpn\\IdeaProjects\\GeekPatternsArc\\server\\www";

    private static final Logger logger = new ConsoleLogger();

    private final SocketService socketService;

    public RequestHandler(SocketService socketService) {
        this.socketService = socketService;
    }

    @Override
    public void run() {
        List<String> request = socketService.readRequest();
        // TODO use here implementation of interface RequestParser
        String[] parts = request.get(0).split(" ");
        Path path = Paths.get(WWW, parts[1]);
        if (!Files.exists(path)) {
            // TODO use implementation of interface ResponseSerializer
            socketService.writeResponse("HTTP/1.1 404 NOT_FOUND\n" + "Content-Type: text/html; charset=utf-8\n" + "\n", new StringReader("<h1>Файл не найден!</h1>\n"));
            return;
        }
        try {
            // TODO use implementation of interface ResponseSerializer
            socketService.writeResponse("HTTP/1.1 200 OK\n" + "Content-Type: text/html; charset=utf-8\n" + "\n", Files.newBufferedReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("Client disconnected!");
    }
}
