package system_patterns.register;

public class Registry {

    private final static Registry registry = new Registry();

    public static Registry getInstance() {
        return registry;
    }

    private final ProductService productService = new ProductService();

    private final BrandService brandService = new BrandService();

    private final UserService userService = new UserService();

    public ProductService getProductService() {
        return this.productService;
    }

    public BrandService getBrandService() {
        return this.brandService;
    }

    public UserService getUserService() {
        return this.userService;
    }

    public <T> T findService(String name, Class<T> clazz) {
        if (name.equals("productService")) {
            return clazz.cast(productService);
        } else if (name.equals("brandService")) {
            return clazz.cast(brandService);
        } else if (name.equals("userService")) {
            return clazz.cast(userService);
        } else {
            throw new IllegalArgumentException("Unknown service");
        }
    }
}
