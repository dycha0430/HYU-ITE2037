public class Lab04 {

	public static void main(String[] args) {
		City c1 = new City("Seoul", 23, 45);
		System.out.println(c1);
		
		City c2 = new City("Paris", 123, 41);
		System.out.println(c2);
		
		City c3 = new City("Racoon City");
		System.out.println(c3);
		
		City c4 = new City("Mega City");
		System.out.println(c4);
		
		System.out.println("Seoul-Paris : " + City.getDistance(c1, c2));
		System.out.println("Seoul-Racoon City : " + City.getDistance(c1, c3));
		System.out.println("Paris-Mega City : " + City.getDistance(c2, c4));
	}

}
