
public class NegativeException extends Exception{
	NegativeException(){
		super("work time must be positive");
	}
	
	NegativeException(String message){
		super(message);
	}
}
