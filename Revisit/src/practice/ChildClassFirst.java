package practice;

public class ChildClassFirst extends ParentClass{
	public void sayHello() {
		super.sayHello();
		System.out.println(super.x);
		System.out.println("Hi from sayHello method of ChildClassFirst.");
	}
}
