package abstract_factory;

public class CarFactoryFactory {

    public static CarFactory createCarFactory(String brand) {
        switch (brand) {
            case "VW":
                return new VwCarFactory();
            case "BMW":
                return new BmwCarFactory();
            default:
                throw new IllegalArgumentException("Unknown brand");
        }
    }
}
