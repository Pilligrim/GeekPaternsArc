import config.Config;
import config.ConfigFactory;
import handler.RequestHandler;
import handler.MethodHandlerReceiver;
import logger.Logger;
import logger.LoggerFactory;
import service.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static final Logger logger = LoggerFactory.getLogger();

    public static void main(String[] args) {
        Config config = ConfigFactory.create(args);
        ResponseSerializer responseSerializer = ResponseSerializerFactory.createResponseSerializer();
        MethodHandlerReceiver methodHandlerReceiver = new MethodHandlerReceiver(config, responseSerializer);
        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            logger.info(String.format("Server started at port %d!%n", config.getPort()));

            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("New client connected!");
                SocketService socketService = SocketServiceFactory.createSocketService(socket);
                RequestParser requestParser = RequestParserFactory.createRequestParser();
                new Thread(new RequestHandler(methodHandlerReceiver, socketService, requestParser)).start();
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
