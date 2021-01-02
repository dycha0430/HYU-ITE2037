package practice07;

public class Manager extends Employee {
	private int officeNum;
	private String team;
	
	Manager(String name, int employeeNum, int officeNum, String team){
		super(name, employeeNum, "Management");
		this.officeNum = officeNum;
		this.team = team;
	}
	
	public String toString() {
		return super.toString() + "\nlocation : " + super.getDepartment() + ", office : " + officeNum;
	}
	
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if(getClass() != obj.getClass())
			return false;
		else {
			Manager manager = (Manager)obj;
			return super.getName() == manager.getName() && super.getEmployeeNum() == manager.getEmployeeNum() && officeNum == manager.officeNum;
		}
	}
}
