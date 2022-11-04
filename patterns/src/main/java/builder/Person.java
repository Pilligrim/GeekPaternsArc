package builder;

import java.time.LocalDateTime;

public class Person {

    private Long id;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDateTime birthdate;

    private String profession;

    private String title;

    private Person() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public String getProfession() {
        return profession;
    }

    public String getTitle() {
        return title;
    }

    public static Builder createBuilder() {
        return new Builder();
    }

    public static class Builder {

        private final Person person;

        private Builder() {
            this.person = new Person();
        }

        public Builder withId(Long id) {
            this.person.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.person.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.person.lastName = lastName;
            return this;
        }

        public Builder withGender(String gender) {
            this.person.gender = gender;
            return this;
        }

        public Builder withBirthdate(LocalDateTime birthdate) {
            this.person.birthdate = birthdate;
            return this;
        }

        public Builder withProfession(String profession) {
            this.person.profession = profession;
            return this;
        }

        public Builder withTitle(String title) {
            this.person.title = title;
            return this;
        }

        public Person build() {
            return this.person;
        }
    }
}
