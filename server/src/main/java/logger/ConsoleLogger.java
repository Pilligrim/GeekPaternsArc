package logger;

import java.util.Arrays;

public class ConsoleLogger implements Logger {
    @Override
    public void info(String msg) {
        System.out.println(ConsoleColors.RESET + msg);
    }

    @Override
    public void warn(String msg) {
        System.out.println(ConsoleColors.YELLOW + msg);
    }

    @Override
    public void error(String msg) {
        System.out.println(ConsoleColors.RED + msg);
    }

    @Override
    public void error(Throwable e) {
        Arrays.stream(e.getStackTrace()).forEach(stackTraceElement ->error(stackTraceElement.toString()));
    }
}
