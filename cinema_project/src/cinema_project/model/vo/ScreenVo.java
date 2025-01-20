package cinema_project.model.vo;

public class ScreenVo {
	private int screen_no;
	private String screen_date;
	private String screen_time;
	private int pos_seat;
	private int movie_no;
	private int theater_no;

	public ScreenVo() {
		super();
	}

	public ScreenVo(int screen_no, String screen_date, String screen_time, int pos_seat, int movie_no, int theater_no) {
		super();
		this.screen_no = screen_no;
		this.screen_date = screen_date;
		this.screen_time = screen_time;
		this.pos_seat = pos_seat;
		this.movie_no = movie_no;
		this.theater_no = theater_no;
	}

	public int getScreen_no() {
		return screen_no;
	}

	public void setScreen_no(int screen_no) {
		this.screen_no = screen_no;
	}

	public String getScreen_date() {
		return screen_date;
	}

	public void setScreen_date(String screen_date) {
		this.screen_date = screen_date;
	}

	public String getScreen_time() {
		return screen_time;
	}

	public void setScreen_time(String screen_time) {
		this.screen_time = screen_time;
	}

	public int getPos_seat() {
		return pos_seat;
	}

	public void setPos_seat(int pos_seat) {
		this.pos_seat = pos_seat;
	}
	
	public int getMovie_no() {
		return movie_no;
	}

	public void setMovie_no(int movie_no) {
		this.movie_no = movie_no;
	}

	public int getTheater_no() {
		return theater_no;
	}

	public void setTheater_no(int theater_no) {
		this.theater_no = theater_no;
	}

	@Override
	public String toString() {
		return "상영번호:" + screen_no + ", 상영날짜:" + screen_date + ", 상영시간:" + screen_time + ", 예약가능좌석수:" + pos_seat + ", 영화번호:" + movie_no
				+ ", 상영관번호:" + theater_no;
	}
	
}
