package abstract_factory;

class VwCarFactory implements CarFactory {

    @Override
    public String createSedan() {
        return "VW Sedan";
    }

    @Override
    public String createHatchback() {
        return "VW hatchback";
    }
}
