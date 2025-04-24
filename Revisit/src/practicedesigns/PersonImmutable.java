package practicedesigns;

public final class PersonImmutable {
    private final int id;
    private final String name;

    public PersonImmutable(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
}
