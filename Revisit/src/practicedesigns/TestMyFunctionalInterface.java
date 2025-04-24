package practicedesigns;

public class TestMyFunctionalInterface {

    public static void main(String[] args) {
        MyFunctionalInterface myFunctionalInterface = new MyFunctionalInterface() {
            @Override
            public void displayMethod() {
                System.out.println("Inside the displayMethod of Anonymous Class Implementation");
            }
        };
        myFunctionalInterface.displayMethod();
        MyFunctionalInterface myFunctionalInterfaceSecond = () -> {
            System.out.println("Inside the Lambda expression of myFunctionalInterface display method.");
        };
        myFunctionalInterfaceSecond.displayMethod();
    }
}
