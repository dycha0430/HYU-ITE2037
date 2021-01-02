import java.util.ArrayList;

public class Member extends Thread{
	private String ID;
	private String Password;
	private ArrayList<Ticket> tickets;
	private int ticketNum;
	private Member thisMember;
	private Movie waitingMovie;
	private int waitingTime;
	
	public Member(String ID, String Password) {
		this.ID = ID;
		this.Password = Password;
		tickets = new ArrayList<>();
		ticketNum = 0;
		thisMember = this;
	}
	
	public Member(Member otherMember) {
		thisMember = otherMember;
	}
	
	public Member getThisMember() {
		return thisMember;
	}
	
	public boolean equalMember(Object obj) {
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

	public void cancel(int cancel_ticket) {
		Movie movie = tickets.get(cancel_ticket).getMovie();
		int i = Theater.reservation.indexOf(getID() + "/" + movie.getMovieNum() + "/" + tickets.get(cancel_ticket).getSeat());
		Theater.reservation.remove(i);
		movie.cancel(tickets.get(cancel_ticket).getSeat());
		tickets.remove(cancel_ticket);
		ticketNum--;
		System.out.println("해당 티켓을 취소하였습니다.");
		synchronized(movie) {
			movie.notifyAll();
		}
	}
	
	public void printTickets() {
		for (int i = 0; i < ticketNum; i++) {
			System.out.println(tickets.get(i).toString());
		}
	}
	
	public void printOrderedTickets() {
		for (int i = 0; i < ticketNum; i++) {
			System.out.println((i+1) + ". " + tickets.get(i).toString());
		}
	}

	public synchronized void reserve(Movie movie, String seatNum) throws NotExistSeatException, DuplicatedReservationException {
		movie.reserve(seatNum);
		Theater.reservation.add(getID() + "/" + movie.getMovieNum() + "/" + seatNum);
		tickets.add(new Ticket(movie, seatNum));
		ticketNum++;
	}
	
	public String toString() {
		return ID + " " + Password;
	}
	
	public String ticketInfo(int ticketNum) {
		return tickets.get(ticketNum).getTicketNum() + "/" + tickets.get(ticketNum).getMovie().getMovieName() + "/" + tickets.get(ticketNum).getSeat();
	}
	
	public void run() {
		try {
			try {
				waitingMovie.waiting(this, waitingTime);
			} catch (NotExistSeatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicatedReservationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void waiting(Movie waitingMovie, int waitingTime) {
		this.waitingMovie = waitingMovie;
		this.waitingTime = waitingTime;
		start();
	}
}
