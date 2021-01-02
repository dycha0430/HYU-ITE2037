import java.util.Scanner;
import java.io.*;

public class Theater {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Scanner inputStream = null;
		Scanner movieList = null;
		Scanner reservationList = null;
		PrintWriter outputUserList = null;
		PrintWriter outputReservation = null;
		final int maxMember = 100;
		int first_comm, sec_comm, thr_comm, selec_movie, movieNum, memberNum = 0;
		int currentMember = 0, isManager, state;
		String ID, Password, seatNum;
		Member[] memberSet = new Member[maxMember]; //전체 Member의 수 100명 제한
		Movie[] movieSet;
		
		try {
			inputStream = new Scanner(new FileInputStream("UserList.txt"));
			movieList = new Scanner(new FileInputStream("MovieList.txt"));
			reservationList = new Scanner(new FileInputStream("Reservation.txt"));
		} 
		catch(FileNotFoundException e) {
			System.out.println("File was not found");
			System.exit(0);
		}
		
		//영화 정보 받아옴.
		movieNum = movieList.nextInt();
		movieList.nextLine();
		String line;
		
		movieSet = new Movie[movieNum];			
		
		for (int i = 0; i < movieNum; i++) {
			line = movieList.nextLine();
			String[] movie_info = line.split("/");
			movieSet[i] = new Movie(movie_info[0], Integer.parseInt(movie_info[1]), Integer.parseInt(movie_info[2]));
		}
	
		//유저 정보 받아옴.
		int tmpNum = inputStream.nextInt();
		for (int i = 0; i < tmpNum; i++) {
			state = inputStream.nextInt();
			String inputID = inputStream.next();
			String inputPW = inputStream.next();
			if (state == 1) {
				memberSet[memberNum++] = new Manager(inputID, inputPW);
			}
			else {
				memberSet[memberNum++] = new User(inputID, inputPW);
			}
		}
		
		while(reservationList.hasNextLine()) {
			int thisUser = -1;
			line = reservationList.nextLine();
			String[] reserve_info = line.split("/");
			for (int j = 0; j < memberNum; j++) {
				if (memberSet[j].equalID(reserve_info[0]))
					thisUser = j;
			}
			int movie_num = Integer.parseInt(reserve_info[1])-1;
			try {
				if (movieSet[movie_num].reserve(reserve_info[2])) {
					memberSet[thisUser].reserve(movieSet[movie_num], reserve_info[2]);
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		
		try {
			outputReservation = new PrintWriter(new FileOutputStream("Reservation.txt", true));
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		
		
		while(true) //영화 예매 프로그램 시작
		{
			System.out.printf("*******영화 예매 프로그램*******\n");
			System.out.printf("1. 로그인\n2. 회원가입\n3. 종료\n");
			System.out.printf("메뉴를 선택해주세요 : ");
			
			try {
				first_comm = sc.nextInt();
				if (first_comm < 1 || first_comm > 3)
					throw new InvalidMenuException();
			} catch(InvalidMenuException e) {
				System.out.println(e.getMessage());
				continue;
			}
			sc.nextLine();
			System.out.println();
			
			if (first_comm == 1) {
				int login = 0;
				System.out.printf("*******로그인*******\nID : ");
				ID = sc.nextLine();
				System.out.printf("Password : ");
				Password = sc.nextLine();
				
				Member temp = new Member(ID, Password);
				
				try {
					for (int i = 0; i < memberNum; i++) {
						if (temp.equals(memberSet[i])) {
							login = 1;
							currentMember = i;
						}
					}
					
					if (login == 0)
						throw new InvalidLoginException();
				} catch (InvalidLoginException e){
					System.out.println(e.getMessage());
					continue;
				}
				
				while(true) //로그인 성공. 유저프로그램 시작.
				{
					System.out.printf("\n*******유저 프로그램*******\n");
					System.out.printf("1. 영화 목록\n2. 예매 확인\n3. 영화관 관리\n4. 종료\n");
					System.out.printf("메뉴를 선택해주세요 : ");
					try{
						sec_comm = sc.nextInt();
						if (sec_comm < 1 || sec_comm > 4)
							throw new InvalidMenuException(sec_comm);
					} catch (InvalidMenuException e){
						System.out.println(e.getMessage());
						continue;
					}
					
					sc.nextLine();
					System.out.println();
					
					if (sec_comm == 1) { //영화 목록
						System.out.printf("*******영화 목록*******\n");
						for (int i = 0; i < movieNum; i++) {
							System.out.println(movieSet[i].toString());
						}
						
						while(true) {
							try{
								System.out.printf("\n1. 예매\n2. 종료\n메뉴를 선택해주세요 : ");
								thr_comm = sc.nextInt();
								if (thr_comm < 1|| thr_comm > 2)
									throw new InvalidMenuException(thr_comm);
								break;
							} catch (InvalidMenuException e) {
								System.out.println(e.getMessage());
								continue;
							}
						}
						sc.nextLine();
						System.out.println();
						
						if (thr_comm == 1) { //예매
							System.out.printf("*******영화 목록*******\n");
							for (int i = 0; i < movieNum; i++) {
								System.out.println((i+1) + ". " + movieSet[i].toString());
							}
							
							while(true) {
								try{
										System.out.printf("\n예매할 영화를 선택해주세요 : ");
										selec_movie = sc.nextInt();
										if (selec_movie < 1 || selec_movie > movieNum)
											throw new InvalidMenuException(selec_movie);
										break;
								}catch(InvalidMenuException e) {
									System.out.println(e.getMessage());
									continue;
								}
							}
							
							sc.nextLine();	
							if (memberSet[currentMember] instanceof Manager) {
								System.out.println(String.format("\"%s\" 영화의 좌석 예매 점유율 : %.2f%% ", movieSet[selec_movie-1].getName(), (float)movieSet[selec_movie-1].getReservedNum()/36*100));
								System.out.println("\"" + movieSet[selec_movie-1].getName() + "\" 영화의 총 매출액 : " + movieSet[selec_movie-1].getReservedNum()*10000);
							}
							else {
								System.out.println(movieSet[selec_movie-1].toString());
							}
							movieSet[selec_movie-1].printSeat();
							while(true) {
								try {
									System.out.printf("좌석을 선택해주세요(ex A1) : ");
									seatNum = sc.nextLine();
									if (movieSet[selec_movie-1].reserve(seatNum)) {
										memberSet[currentMember].reserve(movieSet[selec_movie-1], seatNum);
										outputReservation.println(memberSet[currentMember].getID() + "/" + selec_movie + "/" + seatNum);									}
									break;
								} 
								catch (Exception e) {
									System.out.println(e.getMessage());
									continue;
								}
							}
						}
						else { //종료
							System.out.printf("유저 프로그램으로 돌아갑니다.\n");
							continue;
						}
					}
					else if (sec_comm == 2) { //예매 확인
						if (memberSet[currentMember] instanceof Manager) {
							System.out.println("관리자가 발행한 티켓 수 : " + memberSet[currentMember].getTicketNum());
							System.out.println("매출액 : " + memberSet[currentMember].getTicketNum()*10000);
							System.out.println();
						}
						
						System.out.println("*******예매 목록*******");
						memberSet[currentMember].printTickets();
						
						System.out.printf("Press enter to go back to User program");
						sc.nextLine();		 
					}
					else if (sec_comm == 3){ //영화관 관리
						try {
							if (memberSet[currentMember] instanceof User) {
								throw new InvalidMenuException();
							} 
						} catch(InvalidMenuException e){
							System.out.println(e.getMessage());
							continue;
						}
						
						while(true) {			
							System.out.println("*******영화관 관리*******");
							System.out.println("1.영화관 정보\n2.유저 정보\n3.종료");
							while(true) {
								try {
									System.out.printf("메뉴를 선택해주세요 : ");
									thr_comm = sc.nextInt();
									if (thr_comm < 1 || thr_comm > 3)
										throw new InvalidMenuException();
									break;
								} catch(InvalidMenuException e) {
									System.out.println(e.getMessage());
									continue;
								}
							}
							
							sc.nextLine();
							
							if (thr_comm == 1) {
								System.out.println("점유된 전체 좌석 수 : " + Ticket.getAllTicketNum());
								System.out.println(String.format("전체 좌석 예매 점유율 : %.2f%% ", (float)Ticket.getAllTicketNum()/(36*movieNum)*100));
								System.out.println("영화관 총 매출액 : " + Ticket.getAllTicketNum()*10000);
								
								System.out.println("\n예매율 현황");
								System.out.println("------------------------------------------");
								reserveRanking(movieSet, movieNum);
								System.out.println("------------------------------------------");
								System.out.println("Press enter to go back to Theater Management");
								sc.nextLine();
							}
							else if (thr_comm == 2) {
								System.out.printf("찾으려는 ID : ");
								String findID = sc.nextLine();
								int findUser = -1;
								for (int i = 0; i < memberNum; i++) {
									if (memberSet[i].equalID(findID))
										findUser = i;
								}
								if (findUser == -1) {
									System.out.println("그런 유저는 존재하지 않습니다.\n");
								}
								else {
									System.out.println(memberSet[findUser].getID() + " 고객님이 발행한 티켓 수 : " + memberSet[findUser].getTicketNum());
									System.out.println("------------------------------------------");
									memberSet[findUser].printTickets();
									System.out.println("------------------------------------------");
									System.out.println("Press enter to go back to Theater Management");
									sc.nextLine();
								}
							}
							else {
								System.out.println("유저 프로그램으로 돌아갑니다.");
								break;
							}
						}
					}
					else {
						System.out.printf("영화 예매 프로그램으로 돌아갑니다.\n\n");
						break;
					}
				}
			}
			
			else if (first_comm == 2) {
				System.out.printf("*******회원 가입*******\n");
				
				if (memberNum >= maxMember-1) { //전체 유저의 수 초과
					System.out.printf("더이상 가입할 수 없습니다.\n");
					continue;
				}
				else {
					System.out.printf("ID : ");
					ID = sc.nextLine();
					System.out.printf("Password : ");
					Password = sc.nextLine();
					System.out.printf("Manager : ");
					isManager = sc.nextInt();
					
					try {
						for (int i = 0; i < memberNum; i++) {
							if (memberSet[i].equalID(ID)) {
								throw new DuplicatedIdException(ID);
							}
						}
						System.out.println();
						
						if(isManager == 1) {
							memberSet[memberNum++] = new Manager(ID, Password);
						} else {
							memberSet[memberNum++] = new User(ID, Password);
						}
					} catch(DuplicatedIdException e) {
						System.out.println(e.getMessage());
						continue;
					}
				}
			}
			else if (first_comm == 3) {
				System.out.printf("영화 예매 프로그램을 종료합니다.\n");
				break;
			}
		}
		
		//유저 정보 저장
		try {
			outputUserList = new PrintWriter(new FileOutputStream("UserList.txt"));
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		outputUserList.println(memberNum);
		for(int i = 0; i < memberNum; i++) {
			if (memberSet[i] instanceof Manager)
				outputUserList.println("1 " + memberSet[i].toString());
			else
				outputUserList.println("0 " + memberSet[i].toString());
		}
		
		sc.close();
		outputReservation.close();
		movieList.close();
		inputStream.close();
		outputUserList.close();
	}
	
	//예매율 현황
	private static void reserveRanking(Movie[] movieList, int movieNum) {
		int[] rank = new int[movieNum];
		for (int i = 0; i < movieNum; i++)
			rank[i] = 1;
		
		for (int i = 0; i < movieNum; i++) {
			for (int j = 0; j < movieNum; j++) {
				if (movieList[i].getReservedNum() < movieList[j].getReservedNum())
					rank[i]++;
			}
		}
		
		for (int i = 0; i < movieNum; i++) {
			if (rank[i] == 1)
				System.out.println("1위 : " + movieList[i].getName() + "(예매 좌석 : " + movieList[i].getReservedNum() + ")");
		}
		for (int i = 0; i < movieNum; i++) {
			if (rank[i] == 2)
				System.out.println("2위 : " + movieList[i].getName() + "(예매 좌석 : " + movieList[i].getReservedNum() + ")");
		}
		for (int i = 0; i < movieNum; i++) {
			if (rank[i] == 3)
				System.out.println("3위 : " + movieList[i].getName() + "(예매 좌석 : " + movieList[i].getReservedNum() + ")");
		}
	}
}
