package designpatterns;

public class TestFactoryDesignPattern {
	public static void main(String[] args) {
		Employee employee = EmployeeFactory.getEmployeeInstance("android");
		int salary = employee.getSalary();
		System.out.println("Logging the Salary: " + salary);
	}
}
