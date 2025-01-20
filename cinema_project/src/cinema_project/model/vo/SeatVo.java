package cinema_project.model.vo;

public class SeatVo {
	private int seat_no;
	private String seat_name;
	private int seat_status;

	public SeatVo() {
		super();
	}

	public SeatVo(int seat_no, String seat_name, int seat_status) {
		super();
		this.seat_no = seat_no;
		this.seat_name = seat_name;
		this.seat_status = seat_status;
	}

	public int getSeat_no() {
		return seat_no;
	}

	public void setSeat_no(int seat_no) {
		this.seat_no = seat_no;
	}

	public String getSeat_name() {
		return seat_name;
	}

	public void setSeat_name(String seat_name) {
		this.seat_name = seat_name;
	}

	public int getSeat_status() {
		return seat_status;
	}

	public void setSeat_status(int seat_status) {
		this.seat_status = seat_status;
	}

	@Override
	public String toString() {
		return "좌석번호:" + seat_no + ", 좌석이름:" + seat_name + ", 좌석예매상태:" + seat_status;
	}
}
