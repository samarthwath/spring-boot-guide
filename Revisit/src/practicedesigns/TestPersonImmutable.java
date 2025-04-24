package practicedesigns;

public class TestPersonImmutable {
    public static void main(String[] args) {
        PersonImmutable personImmutable = new PersonImmutable(100, "Samarth Wath");
        System.out.println(personImmutable.getName());
        System.out.println(personImmutable.getId());
    }
}
