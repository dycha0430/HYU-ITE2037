
public class Engineer extends Employee{
	private double rate;
	
	Engineer(String name, int employeeNum){
		super(name, employeeNum);
		rate = 4;
		super.setDepartment("Engineering");
	}
	
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		else if (getClass() != obj.getClass())
			return false;
		else {
			return  super.equals(obj);
		}
	}
	
	public String toString() {
		return super.toString() + "Dept : " + this.getDepartment() + "\n";
	}
	
	public double getPaid() {
		salary = this.getWorkHrs()*rate;
		return salary;
	}
}
