
public class NotExistSeatException extends Exception{
	public NotExistSeatException(String seat) {
		super(seat + " does not exist.");
	}
}
