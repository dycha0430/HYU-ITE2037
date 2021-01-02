import java.util.Scanner;
import java.io.*;

public class Theater {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int maxUser = 101;
		int first_comm, sec_comm, thr_comm, selec_movie, userNum = 0, currentUser = 0, allTicketNum = 0;
		String ID, Password, seatNum;
		User[] userSet = new User[maxUser]; //��ü User�� �� 100�� ����
		Movie movieSet[] = new Movie[8];
		movieSet[0] = new Movie("�����", "0:00 - 3:00");
		movieSet[1] = new Movie("����", "3:00 - 6:00");
		movieSet[2] = new Movie("�ظ�����", "6:00 - 9:00");
		movieSet[3] = new Movie("��󷣵�", "9:00 - 12:00");
		movieSet[4] = new Movie("�ܿ�ձ�", "12:00 - 15:00");
		movieSet[5] = new Movie("��Ž�� �ڳ�", "15:00 - 18:00");
		movieSet[6] = new Movie("�����", "18:00 - 21:00");
		movieSet[7] = new Movie("��������", "21:00 - 24:00");
		
		while(true) 
		{
			System.out.printf("*******��ȭ ���� ���α׷�*******\n");
			System.out.printf("1. �α���\n2. ȸ������\n3. ����\n");
			System.out.printf("�޴��� �������ּ��� : ");
			first_comm = sc.nextInt();
			sc.nextLine();
			System.out.println();
			
			if (first_comm == 1) {
				int login = 0;
				System.out.printf("*******�α���*******\nID : ");
				ID = sc.nextLine();
				System.out.printf("Password : ");
				Password = sc.nextLine();
				
				User temp = new User(ID, Password);
				
				for (int i = 1; i <= userNum; i++) {
					if (temp.equals(userSet[i])) {
						login = 1;
						currentUser = i;
					}
				}
				
				if (login == 1) {
					while(true) 
					{
						System.out.printf("\n*******���� ���α׷�*******\n");
						System.out.printf("1. ��ȭ ���\n2. ���� Ȯ��\n3. ����\n");
						System.out.printf("�޴��� �������ּ��� : ");
						sec_comm = sc.nextInt();
						sc.nextLine();
						System.out.println();
						
						if (sec_comm == 1) { //��ȭ ���
							System.out.printf("*******��ȭ ���*******\n");
							for (int i = 0; i < 8; i++) {
								System.out.println(movieSet[i].toString());
							}
							
							System.out.printf("\n1. ����\n2. ����\n�޴��� �������ּ��� : ");
							
							thr_comm = sc.nextInt();
							sc.nextLine();
							System.out.println();
							
							if (thr_comm == 1) { //����
								System.out.printf("*******��ȭ ���*******\n");
								for (int i = 0; i < 8; i++) {
									System.out.println((i+1) + "." + movieSet[i].toString());
								}
								
								System.out.printf("\n������ ��ȭ�� �������ּ��� : ");
								selec_movie = sc.nextInt();
								sc.nextLine();
								
								System.out.println(movieSet[selec_movie-1].toString());
								movieSet[selec_movie-1].printSeat();
								
								System.out.printf("�¼��� �������ּ���(ex A1) : ");
								seatNum = sc.nextLine();
								
								if (movieSet[selec_movie-1].reserve(seatNum) && userSet[currentUser].isLeftTicket()) {
									userSet[currentUser].reserve(movieSet[selec_movie-1], seatNum, allTicketNum);
									allTicketNum++;
								}
								
							}
							else { //����
								System.out.printf("���� ���α׷����� ���ư��ϴ�.\n");
								continue;
							}
						}
						else if (sec_comm == 2) { //���� Ȯ��
							System.out.println("*******���� ���*******");
							userSet[currentUser].printTickets();
							
							System.out.printf("\nPress enter to go back to User program");
							try {
								System.in.read();
							} catch(IOException e) {}
						}
						else {
							System.out.printf("��ȭ ���� ���α׷����� ���ư��ϴ�.\n\n");
							break;
						}
					}
				}
				else {
					System.out.printf("�α��ο� �����Ͽ����ϴ�.\n\n");
					continue;
				}
			}
			else if (first_comm == 2) {
				System.out.printf("*******ȸ�� ����*******\n");
				
				if (userNum >= maxUser-1) { //��ü ������ �� �ʰ�
					System.out.printf("���̻� ������ �� �����ϴ�.\n");
					continue;
				}
				else {
					System.out.printf("ID : ");
					ID = sc.nextLine();
					System.out.printf("Password : ");
					Password = sc.nextLine();
					
					User temp = new User(ID, Password);
					for (int i = 1; i <= userNum; i++) {
						if (temp.equals(userSet[i])) {
							System.out.printf("�� ������ ������ �� �����ϴ�.\n");
							continue;
						}
					}
					System.out.println();
					
					userSet[++userNum] = new User(ID, Password);
				}
			}
			else if (first_comm == 3) {
				System.out.printf("��ȭ ���� ���α׷��� �����մϴ�.\n");
				break;
			}
		}
	}
}
