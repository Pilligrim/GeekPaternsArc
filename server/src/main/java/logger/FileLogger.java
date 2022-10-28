package logger;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FileLogger implements Logger{
    private final Logger logger;
    private final File file;

    public FileLogger(Logger logger, String fileName) {
        this.logger = logger;
        file = new File(fileName);
        if (file.exists() && !file.canWrite()) {
            throw new IllegalArgumentException("Can't write to file " + fileName);
        } else if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public void info(String msg) {
        try {
            FileUtils.writeStringToFile(file, msg + "\n", StandardCharsets.UTF_8, true);
            logger.info(msg);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    public void warn(String msg) {
        try {
            FileUtils.writeStringToFile(file, msg + "\n", StandardCharsets.UTF_8, true);
            logger.warn(msg);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    public void error(String msg) {
        try {
            FileUtils.writeStringToFile(file, msg + "\n", StandardCharsets.UTF_8, true);
            logger.error(msg);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    public void error(Throwable e) {
        Arrays.stream(e.getStackTrace()).forEach(stackTraceElement ->error(stackTraceElement.toString()));
    }
}
