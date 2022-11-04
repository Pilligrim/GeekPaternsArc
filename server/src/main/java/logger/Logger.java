package logger;

public interface Logger {
    void info(String msg);
    void warn(String msg);
    void error(String msg);
    void error(Throwable e);
}
