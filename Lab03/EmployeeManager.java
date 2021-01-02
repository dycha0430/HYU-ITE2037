package oop__3;

public class EmployeeManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee employee1 = new Employee("James Wright", 42, "Manager", 20000);
		System.out.println(employee1);
		
		Employee employee2 = new Employee("Amy Smith", 27, "Design Coordinator", 8000, 15);
		System.out.println(employee2);
		
		Employee employee3 = new Employee("Peter Coolidge", 32, "Assistant Manager", 12000, 7);
		System.out.println(employee3);
		
		Employee employee4 = new Employee("John Doe", 22, "Engineer", 10000, 10);
		System.out.println(employee4);
		
		Employee newEmployee = new Employee("Dayun Cha", 21);
		System.out.println(newEmployee.equals(employee2));
		
		employee1.vacation(10);
		employee3.vacation(10);
		
		System.out.println(employee1);
		System.out.println(employee2);
		System.out.println(employee3);
		System.out.println(employee4);
	}

}
