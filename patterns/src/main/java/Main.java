import abstract_factory.CarFactory;
import abstract_factory.CarFactoryFactory;
import builder.Person;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Random;

public class Main {

    public static final Main INSTANCE = new Main();

    public static void main(String[] args) {
        CarFactory factory = CarFactoryFactory.createCarFactory("BMW");
        factory.createSedan();

        Person person = Person.createBuilder()
                .withId(1L)
                .withFirstName("Alex")
                .withLastName("Ivanov")
                .withGender("M")
                .withBirthdate(LocalDateTime.now().minusYears(25))
                .withProfession("programmer")
                .withTitle("PhD")
                .build();

        Iterable<Integer> random = new Iterable<Integer>() {

            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<>() {

                    private final Random rnd = new Random();

                    private int count = 0;

                    @Override
                    public boolean hasNext() {
                        return count < 10;
                    }

                    @Override
                    public Integer next() {
                        int val = rnd.nextInt();
                        count++;
                        return val;
                    }
                };
            }
        };

        for (Integer val : random) {
            System.out.println(val);
        }
    }
}
