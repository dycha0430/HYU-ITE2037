import java.util.Scanner;

public class ExceptionDemo {

	public static void main(String[] args) {
		Employee emp = new Employee("Lee");
		int hours;
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.printf(emp.getWorkDay() + "일차 근무 시간을 입력하세요 : ");
			try {
				hours = sc.nextInt();
				if (hours < 0) {
					throw new NegativeException();
				}
				else if (hours == 0) {
					throw new Exception("Program Exit");
				}
				else if (hours > 24) {
					throw new TooMuchStuffException(hours);
				}
				
				emp.addWorkHours(hours);
				emp.addWorkDay();
				System.out.println("이름 : " + emp.getName());
				System.out.println("누적 근무 시간 : " + emp.getWorkHours());
				System.out.println("No Exception has been occurred");
			}catch(NegativeException e) {
				System.out.println(e.getMessage());
			}catch(TooMuchStuffException e) {
				System.out.println(e.getInputNum() + ", " + e.getMessage());
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}finally {
				System.out.println("End of try-catch statement");
			}
		}
	}

}
