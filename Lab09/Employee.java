
public class Employee {
	private String name;
	private int workDay;
	private int workHours;
	
	public Employee(String name) {
		this.name = name;
		workDay = 1;
		workHours = 0;
	}

	public String getName() {
		return name;
	}

	public int getWorkDay() {
		return workDay;
	}

	public int getWorkHours() {
		return workHours;
	}
	
	public void addWorkDay() {
		workDay += 1;
	}
	
	public void addWorkHours(int hours){
		workHours += hours;
	}
}
