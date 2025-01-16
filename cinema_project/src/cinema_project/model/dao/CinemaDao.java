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

	//회원가입
	public int insertUser(UserVo uv, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "INSERT INTO c_user(user_id ,user_pw ,user_name ,user_birth ,user_email ,user_phone) "
					+ "VALUES(?,?,?,?,?,?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,uv.getUser_id());
			pstmt.setString(2,uv.getUser_pw());
			pstmt.setString(3,uv.getUser_name());
			pstmt.setString(4,uv.getUser_birth());
			pstmt.setString(5,uv.getUser_email());
			pstmt.setString(6,uv.getUser_phone());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	//아이디 중복 확인
	public UserVo checkUserId(String userId , Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo uv = new UserVo();
		try {
			String sql=" SELECT * FROM c_user "
					+ " WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				uv.setUser_id(userId);
			}else {
				return null;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			 close(rs);
		     close(pstmt);	
		}
		return uv;
		
	}
	
	public UserVo login(String userId, String userPw , Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo uv = new UserVo();
		try {
			String sql=" SELECT * FROM c_user "
					+ " WHERE user_id = ? AND user_pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				uv.setUser_id(rs.getString("user_id"));
				uv.setUser_pw(rs.getString("user_pw"));
				uv.setUser_name(rs.getString("user_name"));
			}else {
				return null;
			}
			 
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			 close(rs);
		     close(pstmt);	
		}
		return uv;
	}

	
	// 회원 탈퇴 

	public int deleteUser(Connection conn,String pw) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "DELETE FROM c_user "
					+ "WHERE user_pw = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	// 회원 정보 수정
	public int editUser(Connection conn,String pw) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,pw);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
