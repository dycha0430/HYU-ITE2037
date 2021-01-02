
public class Manager extends Employee{
	private double overtimeRate;
	private double rate;
	private int regularHrs;
	
	Manager(String name, int employeeNum){
		super(name, employeeNum);
		rate = 5.0;
		overtimeRate = 8.0;
		regularHrs = 40;
		super.setDepartment("Management");
	}
	
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		else if (getClass() != obj.getClass())
			return false;
		else {
			return super.equals(obj);
		}
	}
	
	public String toString() {
		return super.toString() + "Dept : " + this.getDepartment() + "\n";
	}
	
	public double getPaid() {
		int overtimeHrs = super.getWorkHrs() - regularHrs;
		if (super.getWorkHrs() < 40)
			salary = super.getWorkHrs()*rate;
		else
			salary = (regularHrs*rate) + (overtimeHrs*overtimeRate);
		
		return salary;
	}
}
