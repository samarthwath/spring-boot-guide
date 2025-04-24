package practicedesigns;

public class TestSingletonClass {

    public static void main(String[] args) {
        SingletonClass firstSingletonPOject = SingletonClass.getObject();
        SingletonClass secondSingletonObject = SingletonClass.getObject();
        System.out.println("Log hashCode of firstSingletonObject: " + firstSingletonPOject.hashCode());
        System.out.println("Log hashCode of secondSingletonObject: " + secondSingletonObject.hashCode());
    }
}
