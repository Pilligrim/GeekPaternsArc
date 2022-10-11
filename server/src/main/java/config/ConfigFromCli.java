package config;

class ConfigFromCli implements Config {

    private final String wwwHome;

    private final int port;

    public ConfigFromCli(String[] args) {
        if (args.length < 2) {
            throw new IllegalStateException("No parameters specified");
        }
        this.wwwHome = args[0];
        this.port = Integer.parseInt(args[1]);
    }

    @Override
    public String getWwwHome() {
        return this.wwwHome;
    }

    @Override
    public int getPort() {
        return this.port;
    }
}
