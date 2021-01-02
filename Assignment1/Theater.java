import java.util.Scanner;
import java.io.*;

public class Theater {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int maxUser = 101;
		int first_comm, sec_comm, thr_comm, selec_movie, userNum = 0, currentUser = 0, allTicketNum = 0;
		String ID, Password, seatNum;
		User[] userSet = new User[maxUser]; //전체 User의 수 100명 제한
		Movie movieSet[] = new Movie[8];
		movieSet[0] = new Movie("기생충", "0:00 - 3:00");
		movieSet[1] = new Movie("감기", "3:00 - 6:00");
		movieSet[2] = new Movie("해리포터", "6:00 - 9:00");
		movieSet[3] = new Movie("라라랜드", "9:00 - 12:00");
		movieSet[4] = new Movie("겨울왕국", "12:00 - 15:00");
		movieSet[5] = new Movie("명탐정 코난", "15:00 - 18:00");
		movieSet[6] = new Movie("어벤져스", "18:00 - 21:00");
		movieSet[7] = new Movie("국제시장", "21:00 - 24:00");
		
		while(true) 
		{
			System.out.printf("*******영화 예매 프로그램*******\n");
			System.out.printf("1. 로그인\n2. 회원가입\n3. 종료\n");
			System.out.printf("메뉴를 선택해주세요 : ");
			first_comm = sc.nextInt();
			sc.nextLine();
			System.out.println();
			
			if (first_comm == 1) {
				int login = 0;
				System.out.printf("*******로그인*******\nID : ");
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
						System.out.printf("\n*******유저 프로그램*******\n");
						System.out.printf("1. 영화 목록\n2. 예매 확인\n3. 종료\n");
						System.out.printf("메뉴를 선택해주세요 : ");
						sec_comm = sc.nextInt();
						sc.nextLine();
						System.out.println();
						
						if (sec_comm == 1) { //영화 목록
							System.out.printf("*******영화 목록*******\n");
							for (int i = 0; i < 8; i++) {
								System.out.println(movieSet[i].toString());
							}
							
							System.out.printf("\n1. 예매\n2. 종료\n메뉴를 선택해주세요 : ");
							
							thr_comm = sc.nextInt();
							sc.nextLine();
							System.out.println();
							
							if (thr_comm == 1) { //예매
								System.out.printf("*******영화 목록*******\n");
								for (int i = 0; i < 8; i++) {
									System.out.println((i+1) + "." + movieSet[i].toString());
								}
								
								System.out.printf("\n예매할 영화를 선택해주세요 : ");
								selec_movie = sc.nextInt();
								sc.nextLine();
								
								System.out.println(movieSet[selec_movie-1].toString());
								movieSet[selec_movie-1].printSeat();
								
								System.out.printf("좌석을 선택해주세요(ex A1) : ");
								seatNum = sc.nextLine();
								
								if (movieSet[selec_movie-1].reserve(seatNum) && userSet[currentUser].isLeftTicket()) {
									userSet[currentUser].reserve(movieSet[selec_movie-1], seatNum, allTicketNum);
									allTicketNum++;
								}
								
							}
							else { //종료
								System.out.printf("유저 프로그램으로 돌아갑니다.\n");
								continue;
							}
						}
						else if (sec_comm == 2) { //예매 확인
							System.out.println("*******예매 목록*******");
							userSet[currentUser].printTickets();
							
							System.out.printf("\nPress enter to go back to User program");
							try {
								System.in.read();
							} catch(IOException e) {}
						}
						else {
							System.out.printf("영화 예매 프로그램으로 돌아갑니다.\n\n");
							break;
						}
					}
				}
				else {
					System.out.printf("로그인에 실패하였습니다.\n\n");
					continue;
				}
			}
			else if (first_comm == 2) {
				System.out.printf("*******회원 가입*******\n");
				
				if (userNum >= maxUser-1) { //전체 유저의 수 초과
					System.out.printf("더이상 가입할 수 없습니다.\n");
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
							System.out.printf("이 정보로 가입할 수 없습니다.\n");
							continue;
						}
					}
					System.out.println();
					
					userSet[++userNum] = new User(ID, Password);
				}
			}
			else if (first_comm == 3) {
				System.out.printf("영화 예매 프로그램을 종료합니다.\n");
				break;
			}
		}
	}
}
