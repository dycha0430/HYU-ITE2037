
public class Ticket {
	private static int AllTicketNum = 0;
	private int ticketNum;
	private Movie movie;
	private String seat;
	
	Ticket(Movie movie, String seat){
		AllTicketNum++;
		ticketNum = AllTicketNum;
		this.movie = new Movie(movie);
		this.seat = seat;
	}
	
	public int getTicketNum() {
		return ticketNum;
	}

	public static int getAllTicketNum() {
		return AllTicketNum;
	}

	public String toString() {
		return "Ticket number : " + ticketNum + " / " + movie.getName() + " / " + movie.getBegin() + ":00 - " + movie.getEnd() + ":00 / " + "Seat : " + seat;
	}
	
	public Movie getMovie() {
		return movie;
	}

	public String getSeat() {
		return seat;
	}

	public String returnTicket() {
		return movie.getName() + "/" + seat;
	}
}
