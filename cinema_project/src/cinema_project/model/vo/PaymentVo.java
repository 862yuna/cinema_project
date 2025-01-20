package cinema_project.model.vo;

public class PaymentVo {
	private int pay_no;
	private int res_no;
	private int total_pay;

	public PaymentVo() {
		super();
	}

	public PaymentVo(int pay_no, int res_no, int total_pay) {
		super();
		this.pay_no = pay_no;
		this.res_no = res_no;
		this.total_pay = total_pay;
	}

	public int getPay_no() {
		return pay_no;
	}

	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}

	public int getRes_no() {
		return res_no;
	}

	public void setRes_no(int res_no) {
		this.res_no = res_no;
	}

	public int getTotal_pay() {
		return total_pay;
	}

	public void setTotal_pay(int total_pay) {
		this.total_pay = total_pay;
	}

	@Override
	public String toString() {
		return "결제번호:" + pay_no + ", 예약번호:" + res_no + ", 최종결제액:" + total_pay;
	}
}
