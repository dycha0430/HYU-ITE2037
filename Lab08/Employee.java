public abstract class Employee {
	private String name;
	private int employeeNum;
	private String department;
	private int workHrs;
	protected double salary;
	
	Employee(String name, int employeeNum){
		this.name = name;
		this.employeeNum = employeeNum;
		workHrs = 0;
		salary = 0;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getWorkHrs() {
		return workHrs;
	}
	
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		else if (getClass() != obj.getClass())
			return false;
		else {
			Employee emp = (Employee)obj;
			return (name.equals(emp.name))&&(employeeNum == emp.employeeNum);
		}
	}
	
	public String toString() {
		return "Name : " + name + "\nEmp# : " + employeeNum + "\n";
	}
	
	public void doWork(int hrs) {
		workHrs += hrs;
		getPaid();
	}
	
	public abstract double getPaid();
	
	public boolean equalPay(Employee emp) {
		return salary == emp.salary;
	}
	
}
