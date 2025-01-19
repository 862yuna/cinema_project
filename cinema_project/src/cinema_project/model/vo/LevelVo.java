package cinema_project.model.vo;

public class LevelVo {
	private int level_no;
	private String level_name;
	private int dis_rate;

	public LevelVo() {
		super();
	}

	public LevelVo(int level_no) {
		super();
		this.level_no = level_no;
	}

	public LevelVo(int level_no, String level_name) {
		super();
		this.level_no = level_no;
		this.level_name = level_name;
	}

	public LevelVo(int level_no, String level_name, int dis_rate) {
		super();
		this.level_no = level_no;
		this.level_name = level_name;
		this.dis_rate = dis_rate;
	}

	public int getLevel_no() {
		return level_no;
	}

	public void setLevel_no(int level_no) {
		this.level_no = level_no;
	}

	public String getLevel_name() {
		return level_name;
	}

	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}

	public int getDis_rate() {
		return dis_rate;
	}

	public void setDis_rate(int dis_rate) {
		this.dis_rate = dis_rate;
	}

	@Override
	public String toString() {
		return "등급 번호:" + level_no + ", 등급 이름:" + level_name + ", 할인율:" + dis_rate;
	}

}
