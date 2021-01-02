package kr.co.lab05.manager;

import java.time.LocalDate;
import kr.co.lab05.employee.*;

public class EmployeeManager {

	public static void main(String[] args) {
		Employee Employee1 = new Employee("Cha", 4500);
		LocalDate date = LocalDate.of(2020, 4, 16);
		LocalDate currentDate = LocalDate.of(2020, 4, 16);
		int incentiveMonth, byPercent;
		
		System.out.println("계약일 : " + date);
		System.out.println(Employee1);
		
		incentiveMonth = (int)(Math.random() * 12 + 1);
		
		while (Employee1.getBalance() < 20000) {
			currentDate = currentDate.plusMonths(1);
			if (currentDate.getYear() - date.getYear() >= 1 && currentDate.getMonth() == date.getMonth()) {
				byPercent = (int)(Math.random()*11);
				Employee1.increaseYearlySalary(byPercent);
				System.out.println(currentDate.getYear() - date.getYear() + 1 +"년차 연봉이 " + byPercent + "% 인상되었습니다.");
				incentiveMonth = (int)(Math.random() * 12 + 1);
			}
			
			Employee1.receiveSalary();
			
			if (currentDate.getMonthValue() == incentiveMonth) {
				Employee1.receiveSalary();
				System.out.println(currentDate.getYear() - date.getYear() + 1 + "년차 " + currentDate.getMonthValue() + "월에 인센티브를 받았습니다.");
			}
		}
		
		System.out.println(currentDate);
		System.out.println(Employee1);
	}

}
