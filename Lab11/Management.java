import java.util.ArrayList;

public class Management {
	public static <T extends Employee> T moveDepartment(T t, String department) {
		t.setDepartment(department);
		return t;
	}
	
	public static <T extends Employee> T raiseSalary(T t, double rate) {
		t.setSalary(t.getSalary()*rate);
		return t;
	}
	
	public static <T extends Employee> int findIndexByEmpNum(ArrayList<T> tList, int employeeNum) {
		int i = 0;
		for (T emp : tList) {
			if (emp.getEmployeeNum() == employeeNum) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public static <T extends Employee> ArrayList<T> raiseAllSalary(ArrayList<T> tList, double rate){
		for (T emp : tList) {
			emp.setSalary(emp.getSalary()*rate);
		}
		return tList;
	}
}
