package practice07;

public class Engineer extends Employee{
	private String workZone;
	private String project;
	
	Engineer(String name, int employeeNum, String workZone, String project){
		super(name, employeeNum, "Engineering");
		this.workZone = workZone;
		this.project = project;
	}
	
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if(getClass() != obj.getClass())
			return false;
		else {
			Engineer engineer = (Engineer)obj;
			return super.getName() == engineer.getName() && super.getEmployeeNum() == engineer.getEmployeeNum() && workZone == engineer.workZone;
		}
	}
	
	public String toString() {
		return super.toString() + "\nlocation : " + super.getDepartment() + ", zone : " + workZone;
	}
}
