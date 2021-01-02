
public class InvalidMenuException extends Exception{
	public InvalidMenuException() {
		super("Invalid Access.");
	}
	public InvalidMenuException(int num) {
		super(num + " is an invalid menu number");
	}
}
