package cinema_project.model.vo;

public class UserVo {
	private int user_no;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_birth;
	private String user_email;
	private String user_phone;
	private int user_views;
	private int level_no;
 
	public UserVo() {
		super();
	}

	public UserVo(String user_id) {
		super();
		this.user_id = user_id;
	}

	public UserVo(String user_id, String user_pw) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
	}

	public UserVo(String user_id, String user_pw, String user_name) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
	}

	public UserVo(String user_id, String user_pw, String user_name, String user_birth, String user_email,
			String user_phone) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_birth = user_birth;
		this.user_email = user_email;
		this.user_phone = user_phone;
	}

	public UserVo(int user_no, String user_id, String user_pw, String user_name, String user_birth, String user_email,
			String user_phone, int user_views, int level_no) {
		super();
		this.user_no = user_no;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_birth = user_birth;
		this.user_email = user_email;
		this.user_phone = user_phone;
		this.user_views = user_views;
		this.level_no = level_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public int getUser_views() {
		return user_views;
	}

	public void setUser_views(int user_views) {
		this.user_views = user_views;
	}

	public int getLevel_no() {
		return level_no;
	}

	public void setLevel_no(int level_no) {
		this.level_no = level_no;
	}

	@Override
	public String toString() {
		return "사용자번호:" + user_no + ", 아이디:" + user_id + ", 비밀번호:" + user_pw + ", 사용자이름:" + user_name + ", 생년월일:"
				+ user_birth + ", 이메일:" + user_email + ", 전화번호:" + user_phone + ", 관람횟수:" + user_views + ", 등급번호:"
				+ level_no;
	}

}
