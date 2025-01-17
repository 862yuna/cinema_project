package cinema_project.model.vo;

public class MovieVo {
	private int movie_no;
	private String movie_title;
	private int movie_runtime;
	private String age_limit;
	private int movie_price;
	private int watched;

	public MovieVo() {
		super();
	}

	public MovieVo(int movie_no, String movie_title, int movie_runtime, String age_limit, int movie_price,
			int watched) {
		super();
		this.movie_no = movie_no;
		this.movie_title = movie_title;
		this.movie_runtime = movie_runtime;
		this.age_limit = age_limit;
		this.movie_price = movie_price;
		this.watched = watched;
	}

	public int getMovie_no() {
		return movie_no;
	}

	public void setMovie_no(int movie_no) {
		this.movie_no = movie_no;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public int getMovie_runtime() {
		return movie_runtime;
	}

	public void setMovie_runtime(int movie_runtime) {
		this.movie_runtime = movie_runtime;
	}

	public String getAge_limit() {
		return age_limit;
	}

	public void setAge_limit(String age_limit) {
		this.age_limit = age_limit;
	}

	public int getMovie_price() {
		return movie_price;
	}

	public void setMovie_price(int movie_price) {
		this.movie_price = movie_price;
	}

	public int getWatched() {
		return watched;
	}

	public void setWatched(int watched) {
		this.watched = watched;
	}

	@Override
	public String toString() {
		return "[no." + movie_no + ", 제목 : " + movie_title + ", 러닝타임 : " + movie_runtime + ", 연령제한 : " + age_limit
				+ ", 가격 : " + movie_price + ", 누적관객수 : " + watched +"명]";
	}

}
