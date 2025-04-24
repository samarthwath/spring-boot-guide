package practice;

public class Student implements Comparable<Student> {
	private String name;
	private int marks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public Student(String name, int marks) {
		super();
		this.name = name;
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", marks=" + marks + "]";
	}

	@Override
	public int compareTo(Student o) {
		if (this.marks < o.marks) {
			return -1;
		} else if (this.marks > o.marks) {
			return 1;
		} else {
			return 0;
		}
	}

}
