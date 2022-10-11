
import config.Config;
import config.ConfigFactory;
import logger.ConsoleLogger;
import logger.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static final Logger logger = new ConsoleLogger();

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
            e.printStackTrace();
        }
    }
}
