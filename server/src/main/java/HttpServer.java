import config.Config;
import config.ConfigFactory;
import logger.Logger;
import logger.LoggerFactory;

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
                new Thread(new RequestHandler(new SocketService(socket), new RequestParserImpl(), new ResponseSerializerImpl(), config)).start();
            }
        } catch (IOException e) {
            Arrays.stream(e.getStackTrace()).forEach(stackTraceElement -> logger.error(stackTraceElement.toString()));
        }
    }
}
