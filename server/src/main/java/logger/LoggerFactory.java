package logger;

import config.ConfigFromFile;

import java.io.IOException;
import java.util.Properties;

import static config.ServerConst.PROPERTIES_PATH;


public class LoggerFactory {

    public static Logger getLogger() {
        Properties prop = new Properties();
        try {
            prop.load(ConfigFromFile.class.getResourceAsStream(PROPERTIES_PATH));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        LoggerType loggerType = LoggerType.valueOf(prop.getProperty("logger"));
        String fileName = prop.getProperty("logger.fileName");
        switch (loggerType) {
            case CONSOLE:
                return new ConsoleLogger();
            case FILE:
                if (fileName == null || fileName.isEmpty()) {
                    throw new RuntimeException("Please fill logger.fileName in server.properties");
                }
                return new FileLogger(new ConsoleLogger(), fileName);
        }
        return new ConsoleLogger();
    }
}
