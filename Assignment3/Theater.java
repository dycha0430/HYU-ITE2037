import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Theater {
	static ArrayList<String> reservation = new ArrayList<>(); 	//�������� ���� ����Ʈ
	static ArrayList<String> success = new ArrayList<>(); //���༺�� ���� ���� ����Ʈ
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Scanner inputStream = null;
		Scanner movieList = null;
		Scanner reservationList = null;
		PrintWriter display = null;
		PrintWriter outputUserList = null;
		PrintWriter outputReservation = null;
		int first_comm, sec_comm, thr_comm, soldout_comm, cancel_ticket, selec_movie, waitTime, memberNum, movieNum;
		int currentMember = 0, isManager, state;
		String ID, Password, seatNum;
		ArrayList<Member> memberSet = new ArrayList<>();
		ArrayList<Movie> movieSet = new ArrayList<>();
		
		
		try {
			inputStream = new Scanner(new FileInputStream("UserList.txt"));
			movieList = new Scanner(new FileInputStream("MovieList.txt"));
			reservationList = new Scanner(new FileInputStream("Reservation.txt"));
		} 
		catch(FileNotFoundException e) {
			System.out.println("File was not found");
			System.exit(0);
		}
		//��ȭ ���� �޾ƿ�.
		movieNum = movieList.nextInt();
		movieList.nextLine();
		String line;
		
		for (int i = 0; i < movieNum; i++) {
			line = movieList.nextLine();
			String[] movie_info = line.split("/");
			movieSet.add(new Movie(movie_info[0], Integer.parseInt(movie_info[1]), Integer.parseInt(movie_info[2]), i+1));
		}
	
		//���� ���� �޾ƿ�.
		memberNum = inputStream.nextInt();
		for (int i = 0; i < memberNum; i++) {
			state = inputStream.nextInt();
			String inputID = inputStream.next();
			String inputPW = inputStream.next();
			if (state == 1) {
				memberSet.add(new Manager(inputID, inputPW));
			}
			else {
				memberSet.add(new User(inputID, inputPW));
			}
		}
		
		while(reservationList.hasNextLine()) {
			int thisUser = -1;
			line = reservationList.nextLine();
			String[] reserve_info = line.split("/");
			for (int i = 0; i < memberSet.size(); i++) {
				if (memberSet.get(i).equalID(reserve_info[0]))
					thisUser = i;
			}
			int movie_num = Integer.parseInt(reserve_info[1])-1;
			try {
				memberSet.get(thisUser).reserve(movieSet.get(movie_num), reserve_info[2]);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		
		while(true) //��ȭ ���� ���α׷� ����
		{
			System.out.printf("*******��ȭ ���� ���α׷�*******\n");
			System.out.printf("1. �α���\n2. ȸ������\n3. ����\n");
			System.out.printf("�޴��� �������ּ��� : ");
			
			try {
				first_comm = sc.nextInt();
				if (first_comm < 1 || first_comm > 3)
					throw new InvalidMenuException();
			} catch(Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			sc.nextLine();
			System.out.println();
			
			if (first_comm == 1) {
				int login = 0;
				System.out.printf("*******�α���*******\nID : ");
				ID = sc.nextLine();
				System.out.printf("Password : ");
				Password = sc.nextLine();
				
				Member temp = new Member(ID, Password);
				
				try {
					for (int i = 0; i < memberSet.size(); i++) {
						if (temp.equalMember(memberSet.get(i))) {
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
				
				while(true) //�α��� ����. �������α׷� ����.
				{
					System.out.printf("\n*******���� ���α׷�*******\n");
					System.out.printf("1. ��ȭ ���\n2. ���� Ȯ��\n3. ��ȭ�� ����\n4. ���� ���\n5. ����\n");
					System.out.printf("�޴��� �������ּ��� : ");
					try{
						sec_comm = sc.nextInt();
						if (sec_comm < 1 || sec_comm > 5)
							throw new InvalidMenuException(sec_comm);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						continue;
					}
					
					sc.nextLine();
					System.out.println();
					
					if (sec_comm == 1) { //��ȭ ���
						System.out.printf("*******��ȭ ���*******\n");
						for (int i = 0; i < movieSet.size(); i++) {
							System.out.println(movieSet.get(i).toString());
						}
						
						while(true) {
							try{
								System.out.printf("\n1. ����\n2. ����\n�޴��� �������ּ��� : ");
								thr_comm = sc.nextInt();
								if (thr_comm < 1|| thr_comm > 2)
									throw new InvalidMenuException(thr_comm);
								break;
							} catch (Exception e) {
								System.out.println(e.getMessage());
								continue;
							}
						}
						sc.nextLine();
						System.out.println();
						
						if (thr_comm == 1) { //����
							System.out.printf("*******��ȭ ���*******\n");
							for (int i = 0; i < movieSet.size(); i++) {
								System.out.println((i+1) + ". " + movieSet.get(i).toString());
							}
							
							while(true) {
								try{
										System.out.printf("\n������ ��ȭ�� �������ּ��� : ");
										selec_movie = sc.nextInt();
										if (selec_movie < 1 || selec_movie > movieSet.size())
											throw new InvalidMenuException(selec_movie);
										break;
								}catch(Exception e) {
									System.out.println(e.getMessage());
									continue;
								}
							}
							
							sc.nextLine();	
							if (movieSet.get(selec_movie-1).getReservedNum() < 36) {
								if (memberSet.get(currentMember) instanceof Manager) {
									System.out.println(String.format("\"%s\" ��ȭ�� �¼� ���� ������ : %.2f%% ", movieSet.get(selec_movie-1).getMovieName(), (float)movieSet.get(selec_movie-1).getReservedNum()/36*100));
									System.out.println("\"" + movieSet.get(selec_movie-1).getMovieName() + "\" ��ȭ�� �� ����� : " + movieSet.get(selec_movie-1).getReservedNum()*10000);
								}
								else {
									System.out.println(movieSet.get(selec_movie-1).toString());
								}
								movieSet.get(selec_movie-1).printSeat();
								while(true) {
									try {
										System.out.printf("�¼��� �������ּ���(ex A1) : ");
										seatNum = sc.nextLine();
										memberSet.get(currentMember).reserve(movieSet.get(selec_movie-1), seatNum);
										break;
									} 
									catch (Exception e) {
										System.out.println(e.getMessage());
										continue;
									}
								}
							}
							else {
								movieSet.get(selec_movie-1).printSeat();
								System.out.println("�ش� ��ȭ�� �����Դϴ�.");
								System.out.println("������ �ϸ� ������ �ڸ��� ���� ���� �� �ֽ��ϴ�.");
								while(true) {
									try{
										System.out.print("������ �����Ͻðڽ��ϱ�?(1. ��/ 2. �ƴϿ�)");
										soldout_comm = sc.nextInt();
										if (soldout_comm < 1|| soldout_comm > 2)
											throw new InvalidMenuException(soldout_comm);
										break;
									} catch (Exception e) {
										System.out.println(e.getMessage());
										continue;
									}
								}
								
								if (soldout_comm == 1) {
									while(true) {
										try{
											System.out.print("�� �а� ����Ͻðڽ��ϱ�?");
											waitTime = sc.nextInt();
											if (waitTime < 0)
												throw new InvalidMenuException();
											break;
										} catch (Exception e) {
											System.out.println(e.getMessage());
											continue;
										}
									}
									(new Member(memberSet.get(currentMember))).waiting(movieSet.get(selec_movie-1), waitTime*1000*60);
									System.out.println("������ �Ϸ�Ǿ����ϴ�.");
								}
							}
							
						}
						else { //����
							System.out.printf("���� ���α׷����� ���ư��ϴ�.\n");
							continue;
						}
					}
					else if (sec_comm == 2) { //���� Ȯ��
						if (memberSet.get(currentMember) instanceof Manager) {
							System.out.println("�����ڰ� ������ Ƽ�� �� : " + memberSet.get(currentMember).getTicketNum());
							System.out.println("����� : " + memberSet.get(currentMember).getTicketNum()*10000);
							System.out.println();
						}
						
						System.out.println("*******���� ���*******");
						memberSet.get(currentMember).printTickets();
						
						System.out.printf("Press enter to go back to User program");
						sc.nextLine();		 
					}
					else if (sec_comm == 3){ //��ȭ�� ����
						try {
							if (memberSet.get(currentMember) instanceof User) {
								throw new InvalidMenuException();
							} 
						} catch(Exception e){
							System.out.println(e.getMessage());
							continue;
						}
						
						while(true) {			
							System.out.println("*******��ȭ�� ����*******");
							System.out.println("1.��ȭ�� ����\n2.���� ����\n3.����");
							while(true) {
								try {
									System.out.printf("�޴��� �������ּ��� : ");
									thr_comm = sc.nextInt();
									if (thr_comm < 1 || thr_comm > 3)
										throw new InvalidMenuException(thr_comm);
									break;
								} catch(Exception e) {
									System.out.println(e.getMessage());
									continue;
								}
							}
							
							sc.nextLine();
							
							if (thr_comm == 1) {
								System.out.println("������ ��ü �¼� �� : " + Ticket.getAllTicketNum());
								System.out.println(String.format("��ü �¼� ���� ������ : %.2f%% ", (float)Ticket.getAllTicketNum()/(36*movieSet.size())*100));
								System.out.println("��ȭ�� �� ����� : " + Ticket.getAllTicketNum()*10000);
								
								System.out.println("\n������ ��Ȳ");
								System.out.println("------------------------------------------");
								reserveRanking(movieSet, movieSet.size());
								System.out.println("------------------------------------------");
								System.out.println("Press enter to go back to Theater Management");
								sc.nextLine();
							}
							else if (thr_comm == 2) {
								System.out.printf("ã������ ID : ");
								String findID = sc.nextLine();
								int findUser = -1;
								for (int i = 0; i < memberSet.size(); i++) {
									if (memberSet.get(i).equalID(findID))
										findUser = i;
								}
								if (findUser == -1) {
									System.out.println("�׷� ������ �������� �ʽ��ϴ�.\n");
								}
								else {
									System.out.println(memberSet.get(findUser).getID() + " ������ ������ Ƽ�� �� : " + memberSet.get(findUser).getTicketNum());
									System.out.println("------------------------------------------");
									memberSet.get(findUser).printTickets();
									System.out.println("------------------------------------------");
									System.out.println("Press enter to go back to Theater Management");
									sc.nextLine();
								}
							}
							else {
								System.out.println("���� ���α׷����� ���ư��ϴ�.");
								break;
							}
						}
					}
					else if (sec_comm == 4) {
						if (memberSet.get(currentMember).getTicketNum() != 0) {
							memberSet.get(currentMember).printOrderedTickets();
							
							while(true) {
								try {
									System.out.print("\n� Ƽ���� ����Ͻðڽ��ϱ�?(���ư��� : 0)");
									cancel_ticket = sc.nextInt();
									if (cancel_ticket == 0) break;
									else if (cancel_ticket < 1 || cancel_ticket > memberSet.get(currentMember).getTicketNum())
										throw new InvalidMenuException(cancel_ticket);
									break;
								} catch(Exception e) {
									System.out.println(e.getMessage());
									continue;
								}
							}
							
							if (cancel_ticket != 0) {
								memberSet.get(currentMember).cancel(cancel_ticket-1);
							}
							
						}
						else {
							System.out.println("������ ��ȭ�� �����ϴ�.");
						}
					}
					else {
						System.out.printf("��ȭ ���� ���α׷����� ���ư��ϴ�.\n\n");
						break;
					}
				}
			}
			
			else if (first_comm == 2) {
				System.out.printf("*******ȸ�� ����*******\n");
				System.out.printf("ID : ");
				ID = sc.nextLine();
				System.out.printf("Password : ");
				Password = sc.nextLine();
				System.out.printf("Manager : ");
				isManager = sc.nextInt();
					
				try {
					for (int i = 0; i < memberSet.size(); i++) {
						if (memberSet.get(i).equalID(ID)) {
							throw new DuplicatedIdException(ID);
						}
					}
					System.out.println();
					
					if(isManager == 1) {
						memberSet.add(new Manager(ID, Password));
					} else {
						memberSet.add(new User(ID, Password));
					}
				} catch(Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
			}
			else if (first_comm == 3) {
				System.out.printf("��ȭ ���� ���α׷��� �����մϴ�.\n");
				break;
			}
		}
		
		//���� ���� ����
		try {
			outputUserList = new PrintWriter(new FileOutputStream("UserList.txt"));
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		outputUserList.println(memberSet.size());
		for(int i = 0; i < memberSet.size(); i++) {
			if (memberSet.get(i) instanceof Manager)
				outputUserList.println("1 " + memberSet.get(i).toString());
			else
				outputUserList.println("0 " + memberSet.get(i).toString());
		}
		
		try {
			outputReservation = new PrintWriter(new FileOutputStream("Reservation.txt", false));
			display = new PrintWriter(new FileOutputStream("Display.txt", true));
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	
		for (int i = 0; i < reservation.size(); i++) {
			outputReservation.println(reservation.get(i));
		}
		
		for (int i = 0; i < success.size(); i++) {
			display.println(success.get(i));
		}
		
		sc.close();
		outputReservation.close();
		movieList.close();
		inputStream.close();
		outputUserList.close();
		display.close();
	}
	
	
	
	//������ ��Ȳ
	private static void reserveRanking(ArrayList<Movie> movieList, int movieNum) {
		int[] rank = new int[movieNum];
		for (int i = 0; i < movieNum; i++)
			rank[i] = 1;
		
		for (int i = 0; i < movieNum; i++) {
			for (int j = 0; j < movieNum; j++) {
				if (movieList.get(i).getReservedNum() < movieList.get(j).getReservedNum())
					rank[i]++;
			}
		}
		
		for (int i = 0; i < movieNum; i++) {
			if (rank[i] == 1)
				System.out.println("1�� : " + movieList.get(i).getMovieName() + "(���� �¼� : " + movieList.get(i).getReservedNum() + ")");
		}
		for (int i = 0; i < movieNum; i++) {
			if (rank[i] == 2)
				System.out.println("2�� : " + movieList.get(i).getMovieName() + "(���� �¼� : " + movieList.get(i).getReservedNum() + ")");
		}
		for (int i = 0; i < movieNum; i++) {
			if (rank[i] == 3)
				System.out.println("3�� : " + movieList.get(i).getMovieName() + "(���� �¼� : " + movieList.get(i).getReservedNum() + ")");
		}
	}
}
