package kr.co.lab05.employee;

public class Employee {
	private String  name;
	private double yearly_salary;
	private double monthly_salary;
	private double balance;
	
	public Employee(String name, double yearly_salary) {
		this.name = name;
		this.yearly_salary = yearly_salary;
		this.monthly_salary = yearly_salary / 12;
		this.balance = 0;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void increaseYearlySalary(int byPercent) {
		yearly_salary += yearly_salary * byPercent / 100;
		monthly_salary = yearly_salary / 12;
	}
	
	public void receiveSalary() {
		balance += monthly_salary;
	}
	
	public String toString() {
		return "이름 : " + name + ", 연봉 : " + yearly_salary + ", 월급 : " + monthly_salary + ", 재산 : " + balance;
	}
}
