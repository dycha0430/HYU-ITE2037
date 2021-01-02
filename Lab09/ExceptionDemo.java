import java.util.Scanner;

public class ExceptionDemo {

	public static void main(String[] args) {
		Employee emp = new Employee("Lee");
		int hours;
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.printf(emp.getWorkDay() + "���� �ٹ� �ð��� �Է��ϼ��� : ");
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
				System.out.println("�̸� : " + emp.getName());
				System.out.println("���� �ٹ� �ð� : " + emp.getWorkHours());
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
