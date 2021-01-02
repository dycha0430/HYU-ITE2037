
public class User {
	private String ID;
	private String Password;
	private Ticket[] tickets;
	private int ticketNum;
	
	User(String ID, String Password){
		this.ID = ID;
		this.Password = Password;
		tickets = new Ticket[100]; //������ ���� �� �ִ� Ƽ���� �� 100�� ����
		ticketNum = 0;
	}
	
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		else if (getClass() != obj.getClass())
			return false;
		else {
			User user = (User)obj;
			return ID.equals(user.ID) && Password.equals(user.Password);
		}
	}
	
	public boolean isLeftTicket() {
		if (ticketNum > 99) {
			System.out.println("������ �� �ִ� Ƽ�� ���� �ʰ��߽��ϴ�.");
			return false;
		}
		return true;
	}
	
	public void reserve(Movie movie, String seatNum, int allTicketNum) {
		tickets[ticketNum++] = new Ticket(allTicketNum, movie, seatNum);
	}
	
	public void printTickets() {
		for (int i = 0; i < ticketNum; i++) {
			System.out.println(tickets[i].toString());
		}
	}
}