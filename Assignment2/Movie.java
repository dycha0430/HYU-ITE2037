
public class Movie {
	private String name;
	private int begin;
	private int end;
	private char[][] seat;
	private int reservedNum;
	
	public String getName() {
		return name;
	}

	public int getReservedNum() {
		return reservedNum;
	}

	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}

	public char getSeat(int i, int j) {
		return seat[i][j];
	}
	
	public Movie(String name, int begin, int end){
		this.name = name;
		this.begin = begin;
		this.end = end;
		seat = new char[6][6];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++)
				seat[i][j] = 'O';
		}
		reservedNum = 0;
	}

	public Movie(Movie anotherMovie){
		name = anotherMovie.getName();
		begin = anotherMovie.getBegin();
		end = anotherMovie.getEnd();
		seat = new char[6][6];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++)
				seat[i][j] = anotherMovie.getSeat(i, j);
		}
	}
	
	public boolean reserve(String seatNum) throws NotExistSeatException, DuplicatedReservationException {
		char row = seatNum.charAt(0);
		String col_ = seatNum.substring(1);
		int col = Integer.parseInt(col_)-1;
		if (row - 'A' < 0 || row - 'A' > 5 || col < 0 || col > 5) {
			throw new NotExistSeatException(seatNum);
		}
		else if (seat[row - 'A'][col] == 'O') {
			seat[row - 'A'][col] = 'X';
			reservedNum++;
			return true;
		}
		else {
			throw new DuplicatedReservationException(seatNum);
		}
	}
	
	public String toString() {
		return "제목 : " + name + " / 상영 시간 : " + begin + ":00 - " + end + ":00";
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
	
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		else if (!(obj instanceof Member))
			return false;
		else {
			Movie movie = (Movie)obj;
			return name.equals(movie.name) && begin == movie.begin && end == movie.end;
		}
	}
}
