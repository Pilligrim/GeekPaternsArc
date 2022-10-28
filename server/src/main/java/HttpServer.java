import config.Config;
import config.ConfigFactory;
import handler.AnnotatedHandlerFactory;
import handler.RequestHandler;
import logger.Logger;
import logger.LoggerFactory;
import service.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class HttpServer {
    private static final Logger logger = LoggerFactory.getLogger();

    public static void main(String[] args) {
        Config config = ConfigFactory.create(args);
        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            logger.info(String.format("Server started at port %d!%n", config.getPort()));

            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("New client connected!");
                SocketService socketService = SocketServiceFactory.createSocketService(socket);
                ResponseSerializer responseSerializer = ResponseSerializerFactory.createResponseSerializer();
                RequestParser requestParser = RequestParserFactory.createRequestParser();
                new Thread(new RequestHandler(socketService,
                           requestParser,
                           AnnotatedHandlerFactory.create(socketService, responseSerializer, config))).start();
            }
        } catch (IOException e) {
            Arrays.stream(e.getStackTrace()).forEach(stackTraceElement -> logger.error(stackTraceElement.toString()));
        }
    }
}
