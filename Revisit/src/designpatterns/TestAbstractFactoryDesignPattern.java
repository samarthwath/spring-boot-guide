package designpatterns;

public class TestAbstractFactoryDesignPattern {

	public static void main(String[] args) {
		Employee employeeObject = EmployeeFactory.getEmployeeObject(new AndroidDevFactory());
		Employee employeeSecondObject = EmployeeFactory.getEmployeeObject(new WebDevFactory());
		System.out.println("Logging Employee Object salaries: ");
		System.out.println(employeeObject.getSalary());
		System.out.println(employeeSecondObject.getSalary());
		System.out.println("Logging HashCodes: ");
		System.out.println(employeeObject.hashCode());
		System.out.println(employeeSecondObject.hashCode());
	}
}
