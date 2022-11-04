package system_patterns;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Vector vector1 = new Vector(1, 2);
        Vector vector2 = new Vector(2, 3);
        Vector vector3 = vector1.add(vector2);

        BigDecimal val = new BigDecimal(0);
        BigDecimal add = val.add(new BigDecimal(10));
    }
}
