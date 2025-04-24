package designpatterns;

public class EmployeeFactory {
	/**
	 * Normal Factory Design Pattern:
	 * 
	 * @param type
	 * @return
	 */

	public static Employee getEmployeeInstance(String type) {
		if (type.trim().equalsIgnoreCase("android")) {
			return new AndroidDeveloper();
		} else if (type.trim().equals("web")) {
			return new WebDeveloper();
		} else {
			return null;
		}
	}

	public static Employee getEmployeeObject(EmployeeAbstractFactory employeeAbstractFactory) {
		return employeeAbstractFactory.createEmployee();
	}

}
