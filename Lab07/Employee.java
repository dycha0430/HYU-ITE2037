package practice07;

public class Employee {
	private String name;
	private int employeeNum;
	private String department;
	
	Employee(String name, int employeeNum, String department){
		this.name = name;
		this.employeeNum = employeeNum;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(int employeeNum) {
		this.employeeNum = employeeNum;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if(getClass() != obj.getClass())
			return false;
		else {
			Employee employee = (Employee)obj;
			return name.equals(employee.name) && (employeeNum == employee.employeeNum);
		}
	}
	
	public String toString() {
		return "Name : " + name + "\nEmp# : " + employeeNum;
	}
	
}
