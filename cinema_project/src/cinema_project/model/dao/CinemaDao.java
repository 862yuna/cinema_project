package cinema_project.model.dao;

import static cinema_project.common.CinemaTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;

import cinema_project.model.vo.MovieVo;
import cinema_project.model.vo.ScreenVo;
import cinema_project.model.vo.UserVo;


public class CinemaDao {
	
	// 날짜,시간으로 상영정보 검색
	public ScreenVo findScreenExist(String date, String time, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ScreenVo screen = null;
		try {
			String sql = "select * from c_screen where screen_date = ? and screen_time = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, time);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				screen = new ScreenVo();
				screen.setScreen_no(rs.getInt("screen_no"));
				screen.setScreen_date(rs.getString("screen_date"));
				screen.setScreen_time(rs.getString("screen_time"));
				screen.setPos_seat(rs.getInt("pos_seat"));
				screen.setMovie_no(rs.getInt("movie_no"));
				screen.setTheater_no(rs.getInt("theater_no"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return screen;
	}
	
	// 영화 연령 제한 메소드
	public int ageLimitCheck(String title, UserVo user, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MovieVo movie = null;
		int result = 0;
		try {
			String sql = "select * from c_movie where movie_title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				movie = new MovieVo();
				movie.setMovie_no(rs.getInt("movie_no"));
				movie.setMovie_title(rs.getString("movie_title"));
				movie.setMovie_runtime(rs.getInt("movie_runtime"));
				movie.setAge_limit(rs.getString("age_limit"));
				movie.setMovie_price(rs.getInt("movie_price"));
				movie.setWatched(rs.getInt("watched"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		String age_limit = movie.getAge_limit();
		// 현재 날짜
		LocalDate today = LocalDate.now();
		String birth = user.getUser_birth();
		// 생년월일을 LocalDate 타입으로 변경
		LocalDate date = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		int age = (int)(ChronoUnit.YEARS.between(date, today)+1);
		if("All".equals(age_limit)) {
			result = 1;
		} else {
			if(age >= Integer.parseInt(movie.getAge_limit())) {
				result = 1;
			}
		}
		return result;
	}
	
	// 영화 존재 유무 메소드
	public MovieVo findMovieExist(String title, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MovieVo movie = null;
		
		try {
			String sql = "select * from c_movie where movie_title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				movie = new MovieVo();
				movie.setMovie_no(rs.getInt("movie_no"));
				movie.setMovie_title(rs.getString("movie_title"));
				movie.setMovie_runtime(rs.getInt("movie_runtime"));
				movie.setAge_limit(rs.getString("age_limit"));
				movie.setMovie_price(rs.getInt("movie_price"));
				movie.setWatched(rs.getInt("watched"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return movie;
	}
	
	
	// 영화 전체 목록 출력 메소드
	public List<MovieVo> showMovieAll(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		List<MovieVo> list = new ArrayList<MovieVo>();
			
		try {
			stmt = conn.createStatement();
			String sql = "select * from c_movie order by watched desc";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				MovieVo movie = new MovieVo();
				movie.setMovie_no(rs.getInt("movie_no"));
				movie.setMovie_title(rs.getString("movie_title"));
				movie.setMovie_runtime(rs.getInt("movie_runtime"));
				movie.setAge_limit(rs.getString("age_limit"));
				movie.setMovie_price(rs.getInt("movie_price"));
				movie.setWatched(rs.getInt("watched"));
				list.add(movie);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return list;
	}
	
	public int deleteUser(Connection conn,String pw) {
		int result=0;
		return result;
	}
}
