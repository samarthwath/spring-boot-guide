package practicedesigns;

public class TestPrototypeDesignPattern {
    public static void main(String[] args) throws CloneNotSupportedException {
        PrototypeDesignPattern prototypeDesignPattern = new PrototypeDesignPattern();
        prototypeDesignPattern.setId(100);
        prototypeDesignPattern.setName("Samarth");
        prototypeDesignPattern.setCity("Indore");
        System.out.println("Logging hashCode of the originalObject" + prototypeDesignPattern.hashCode());

        PrototypeDesignPattern clonedObject = (PrototypeDesignPattern) prototypeDesignPattern.clone();
        System.out.println("Logging hashCode of the clonedObject: " + clonedObject.hashCode());
    }
}
