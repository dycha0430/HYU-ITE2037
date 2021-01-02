
public class DuplicatedIdException extends Exception{
	public DuplicatedIdException(String ID) {
		super(ID + " already exists.");
	}
}
