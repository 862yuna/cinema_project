package cinema_project.model.vo;

public class TheaterVo {
	private int theater_no;
	private String theater_name;
	private int theater_seats;

	public TheaterVo() {
		super();
	}

	public TheaterVo(int theater_no, String theater_name, int theater_seats) {
		super();
		this.theater_no = theater_no;
		this.theater_name = theater_name;
		this.theater_seats = theater_seats;
	}

	public int getTheater_no() {
		return theater_no;
	}

	public void setTheater_no(int theater_no) {
		this.theater_no = theater_no;
	}

	public String getTheater_name() {
		return theater_name;
	}

	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
	}

	public int getTheater_seats() {
		return theater_seats;
	}

	public void setTheater_seats(int theater_seats) {
		this.theater_seats = theater_seats;
	}

	@Override
	public String toString() {
		return "상영관번호:" + theater_no + ", 상영관이름:" + theater_name + ", 총 좌석수:" + theater_seats;
	}

}
