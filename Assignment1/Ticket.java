
public class Ticket {
	private int ticketNum;
	private Movie movie;
	private String seat;
	
	Ticket(int ticketNum, Movie movie, String seat){
		this.ticketNum = ticketNum;
		this.movie = new Movie(movie);
		this.seat = seat;
	}
	
	public String toString() {
		return "Ticket number : " + (ticketNum+1) + " / " + movie.getName() + " / " + movie.getTime() + " / " + "Seat : " + seat;
	}
}
