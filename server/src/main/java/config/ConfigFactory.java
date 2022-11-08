package config;

import static config.ServerConst.PROPERTIES_PATH;

public class ConfigFactory {

    public static Config create(String[] args) {
        if (args.length > 0) {
            return new ConfigFromCli(args);
        } else {
            return new ConfigFromFile(PROPERTIES_PATH);
        }
    }
}
