package practicedesigns;

public class SingletonClass {
    private static SingletonClass singletonClassObject;

    private SingletonClass() {

    }

    public static SingletonClass getObject() {
        if (singletonClassObject == null) {
            singletonClassObject = new SingletonClass();
            return singletonClassObject;
        } else {
            return singletonClassObject;
        }
    }
}
