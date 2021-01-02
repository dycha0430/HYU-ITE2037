package oop__3;

public class Employee {
	private String name;
	private int age;
	private String position;
	private int salary;
	private int vacationDays;
	
	public Employee(String name, int age) {
		this.name = name;
		this.age = age;
		position = "Employee";
		salary = 5000;
		vacationDays = 20;
	}
	
	public Employee(String name, int age, String position, int salary) {
		this.name = name;
		this.age = age;
		this.position = position;
		this.salary = salary;
		vacationDays = 20;
	}
	
	public Employee(String name, int age, String position, int salary, int vacationDays) {
		this.name = name;
		this.age = age;
		this.position = position;
		this.salary = salary;
		this.vacationDays = vacationDays;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public int getVacationDays() {
		return vacationDays;
	}
	public void setVacationDays(int vacationDays) {
		this.vacationDays = vacationDays;
	}
	
	public Boolean equals(Employee anotherEmployee) {
		if (this.name.equals(anotherEmployee.name) && this.age == anotherEmployee.age && this.position.equals(anotherEmployee.position))
			return true;
		else
			return false;
	}
	
	public String toString() {
		return "Name : " + name + ", Age : " + age + ", Position : " + position + ", Salary : " + salary + ", VacationDays : " + vacationDays;
	}
	
	public Boolean vacation(int wantVacate) {
		if (wantVacate > vacationDays) {
			System.out.println("남은 휴가 일수가 부족합니다.");
			return false;
		}
		
		vacationDays -= wantVacate;
		System.out.printf("휴가를 사용하였습니다. 남은 휴가 일 수 : %d\n", vacationDays);
		return true;
	}
}