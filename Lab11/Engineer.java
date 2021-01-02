
public class Engineer extends Employee{
	public static int initial_engineer_number = 2000;
	public static String initial_engineer_dept = "Computational Management";
	public static int initial_engineer_salary = 3300;
	
	public Engineer(String name) {
		super.setName(name);
		super.setDepartment(initial_engineer_dept);
		super.setSalary(initial_engineer_salary);
		super.setEmployeeNum(++initial_engineer_number);
	}
}
