package cinema_project.model.vo;

public class ReservationVo {
	private int res_no;
	private int user_no;
	private int screen_no;
	private int amount;

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

}
