package practicedesigns;

public class TestEmployeeFactory {
    public static void main(String[] args) {
        Employee androidEmployee = EmployeeFactory.getEmployee("Android");
        Employee webEmployee = EmployeeFactory.getEmployee("Web");
        System.out.println("Log androidEmployeeSalary: " + androidEmployee.getSalary());
        System.out.println("Log webEmployeeSalary: " + webEmployee.getSalary());
    }
}
