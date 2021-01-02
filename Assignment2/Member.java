public class Member {
	private String ID;
	private String Password;
	private Ticket[] tickets;
	private int ticketNum;
	
	public Member(String ID, String Password) {
		this.ID = ID;
		this.Password = Password;
		tickets = new Ticket[100]; //����� ���� �� �ִ� Ƽ���� �� 100�� ����
		ticketNum = 0;
	}
	
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		else if (!(obj instanceof Member))
			return false;
		else {
			Member member = (Member)obj;
			return ID.equals(member.ID) && Password.equals(member.Password);
		}
	}
	
	public boolean equalID(String ID) {
		if (this.ID.equals(ID))
			return true;
		return false;
	}
	
	public String getID() {
		return ID;
	}

	public int getTicketNum() {
		return ticketNum;
	}

	public void printTickets() {
		for (int i = 0; i < ticketNum; i++) {
			System.out.println(tickets[i].toString());
		}
	}

	public boolean reserve(Movie movie, String seatNum) {
		if (ticketNum >= 100) {
			System.out.println("������ �� �ִ� Ƽ�� ���� �ʰ��߽��ϴ�.");
			return false;
		}
		
		tickets[ticketNum++] = new Ticket(movie, seatNum);
		return true;
	}
	
	public String toString() {
		return ID + " " + Password;
	}
	
	public String ticketInfo(int ticketNum) {
		return tickets[ticketNum].getTicketNum() + "/" + tickets[ticketNum].getMovie().getName() + "/" + tickets[ticketNum].getSeat();
	}
}
