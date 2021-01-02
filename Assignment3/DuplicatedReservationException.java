
public class DuplicatedReservationException extends Exception{
	public DuplicatedReservationException(String seat) {
		super(seat + " is already reserved.");
	}
}
