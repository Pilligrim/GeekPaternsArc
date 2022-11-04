package abstract_factory;

class BmwCarFactory implements CarFactory {

    @Override
    public String createSedan() {
        return "BMW Sedan";
    }

    @Override
    public String createHatchback() {
        return "BMV Hatchback";
    }
}
