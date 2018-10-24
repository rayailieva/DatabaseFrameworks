package p02MultipleImplementation;

public class Citizen implements Person, Identifiable, Birthable {
    private String name;
    private int age;
    private String birthdate;
    private String id;

    public Citizen(String name, int age, String birthdate, String id) {
        this.name = name;
        this.age = age;
        this.birthdate = birthdate;
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public String getBirthdate() {
        return this.birthdate;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
