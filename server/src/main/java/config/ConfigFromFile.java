package config;

import java.io.IOException;
import java.util.Properties;

public class ConfigFromFile implements Config {
    private final static String  WWW_HOME = "www.home";
    private final static String  PORT = "port";

    private final String resource;

    private final int port;

    public ConfigFromFile(String fileName) {
        Properties prop = new Properties();
        try {
            prop.load(ConfigFromFile.class.getResourceAsStream(fileName));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        this.resource = prop.getProperty(WWW_HOME);
        this.port = Integer.parseInt(prop.getProperty(PORT));
    }

    @Override
    public String getResource() {
        return this.resource;
    }

    @Override
    public int getPort() {
        return this.port;
    }
}
