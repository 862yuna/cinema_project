package cinema_project.model.vo;

import java.util.List;

public class ReservationVo {
	private int res_no;
	private int user_no;
	private int screen_no;
	private int amount;
	private String movieTitle;
	private String screenDate;
	private String sreenTime;
	private int theaterNo;
	
	
	
	
	public ReservationVo(String movieTitle, String screenDate, String sreenTime, int theaterNo ,int amount) {
		this.movieTitle = movieTitle;
		this.screenDate = screenDate;
		this.sreenTime = sreenTime;
		this.theaterNo = theaterNo;
		this.amount = amount;
	}

	public ReservationVo() {
		super();
	}

	public ReservationVo(int res_no, int user_no, int screen_no, int amount) {
		super();
		this.res_no = res_no;
		this.user_no = user_no;
		this.screen_no = screen_no;
		this.amount = amount;
	}

	
	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getScreenDate() {
		return screenDate;
	}

	public void setScreenDate(String screenDate) {
		this.screenDate = screenDate;
	}

	public String getSreenTime() {
		return sreenTime;
	}

	public void setSreenTime(String sreenTime) {
		this.sreenTime = sreenTime;
	}

	public int getTheaterNo() {
		return theaterNo;
	}

	public void setTheaterNo(int theaterNo) {
		this.theaterNo = theaterNo;
	}

	public int getRes_no() {
		return res_no;
	}

	public void setRes_no(int res_no) {
		this.res_no = res_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getScreen_no() {
		return screen_no;
	}

	public void setScreen_no(int screen_no) {
		this.screen_no = screen_no;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "예약번호:" + res_no + ", 사용자번호:" + user_no + ", 상영번호:" + screen_no + ", 예매 수:" + amount;
	}

	public String revervationView(List<ReservationVo> reservationList) {
		return "영화명 : "+movieTitle + ", 상영 날짜" + screenDate +", 상영 시간 : "+ sreenTime
				+", 상영관 : "+theaterNo+"관, 예매 좌석 수 : "+ amount;
	}
}
