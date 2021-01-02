
public class Movie {
	private String name;
	private String time;
	private char[][] seat;
	
	public String getName() {
		return name;
	}

	public String getTime() {
		return time;
	}

	public char getSeat(int i, int j) {
		return seat[i][j];
	}
	
	Movie(String name, String time){
		this.name = name;
		this.time = time;
		seat = new char[6][6];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++)
				seat[i][j] = 'O';
		}
	}

	Movie(Movie anotherMovie){
		name = anotherMovie.getName();
		time = anotherMovie.getTime();
		seat = new char[6][6];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++)
				seat[i][j] = anotherMovie.getSeat(i, j);
		}
	}
	
	public boolean reserve(String seatNum) {
		char row = seatNum.charAt(0);
		char col = seatNum.charAt(1);
		
		if (seat[row - 'A'][col - '1'] == 'O') {
			seat[row - 'A'][col - '1'] = 'X';
			return true;
		}
		else {
			System.out.printf("이미 예약된 좌석입니다.\n");
			return false;
		}
		
	}
	
	public String toString() {
		return "제목 : " + name + " / 상영 시간 : " + time;
	}
	
	public void printSeat() {
		System.out.println("*******좌 석*******");
		System.out.println("  1 2 3 4 5 6");
		for (int i = 0; i < 6; i++) {
			System.out.printf("%c ", i+65);
			for (int j = 0; j < 6; j++) {
				System.out.printf("%c ", seat[i][j]);
			}
			System.out.println();
		}
		System.out.println("\n*******************");
	}
}