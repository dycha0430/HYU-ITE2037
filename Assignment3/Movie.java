import java.util.ArrayList;
import java.util.Calendar;

public class Movie{
	private String movieName;
	private int begin;
	private int end;
	private char[][] seat;
	private int reservedNum;
	private int movieNum; //영화의 번호
	private ArrayList<Member> waitingList; 	//예약 대기중인 리스트

	
	public String getMovieName() {
		return movieName;
	}

	public int getMovieNum() {
		return movieNum;
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
	
	public Movie(String movieName, int begin, int end, int movieNum){
		this.movieName = movieName;
		this.begin = begin;
		this.end = end;
		seat = new char[6][6];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++)
				seat[i][j] = 'O';
		}
		reservedNum = 0;
		this.movieNum = movieNum;
		waitingList = new ArrayList<>();
	}

	public synchronized void waiting(Member member, int waitingTime) throws InterruptedException, NotExistSeatException, DuplicatedReservationException {
		waitingList.add(member);
		String seat;
		boolean tmp = false;
		long remainTime = waitingTime;
		while (!tmp) { //리스트의 맨 처음이 아니라면 계속 wait.
			long startTime = System.currentTimeMillis();
			wait(remainTime);
			remainTime = remainTime - (System.currentTimeMillis() - startTime);
			tmp = waitingList.get(0) == member;
			wait(10);
			
			if (remainTime <= 0) { //timeout이 끝남.
				waitingList.remove(waitingList.indexOf(member));
				return;
			}

		}
		
		seat = "A0";
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (getSeat(i, j) == 'O') {
					String row = Character.toString('A' + i);
					String col = Character.toString('1' + j);
					seat = row + col;
					break;
				}
			}
		}
		member.getThisMember().reserve(this, seat);
		waitingList.remove(waitingList.indexOf(member));
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		Theater.success.add(year + "." + month + "." + day + ". " + hours + ":" + minutes + "에 " + member.getThisMember().getID() + "님의 " + movieName + " 영화 " + seat + "좌석이 예매되었습니다.");
	}
	
	public void cancel(String seatNum) {
		char row = seatNum.charAt(0);
		String col_ = seatNum.substring(1);
		int col = Integer.parseInt(col_)-1;
		seat[row - 'A'][col] = 'O';
		reservedNum--;
	}
	
	public void reserve(String seatNum) throws NotExistSeatException, DuplicatedReservationException {
		char row = seatNum.charAt(0);
		String col_ = seatNum.substring(1);
		int col = Integer.parseInt(col_)-1;
		if (row - 'A' < 0 || row - 'A' > 5 || col < 0 || col > 5) {
			throw new NotExistSeatException(seatNum);
		}
		else if (seat[row - 'A'][col] == 'O') {
			seat[row - 'A'][col] = 'X';
			reservedNum++;
			return;
		}
		else {
			throw new DuplicatedReservationException(seatNum);
		}
	}
	
	public String toString() {
		return "제목 : " + movieName + " / 상영 시간 : " + begin + ":00 - " + end + ":00";
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
			return movieName.equals(movie.movieName) && begin == movie.begin && end == movie.end;
		}
	}
}
