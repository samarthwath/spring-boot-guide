package practicedesigns;

public class EmployeeFactory {

    public static Employee getEmployee(String employeeType) {
        if (employeeType.equals("Android")) {
            return new AndroidDeveloper();
        } else if (employeeType.equals("Web")) {
            return new WebDeveloper();
        }
        return null;
    }
}
