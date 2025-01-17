package cinema_project.model.dao;


import static cinema_project.common.CinemaTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cinema_project.model.vo.MovieVo;
import cinema_project.model.vo.UserVo;

public class CinemaDao {
	
	//회원가입
	public int insertUser(UserVo user, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "INSERT INTO c_user(user_id ,user_pw ,user_name ,user_birth ,user_email ,user_phone) "
					+ "VALUES(?,?,?,?,?,?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,user.getUser_id());
			pstmt.setString(2,user.getUser_pw());
			pstmt.setString(3,user.getUser_name());
			pstmt.setString(4,user.getUser_birth());
			pstmt.setString(5,user.getUser_email());
			pstmt.setString(6,user.getUser_phone());
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
		UserVo user = new UserVo();
		try {
			String sql=" SELECT * FROM c_user "
					+ " WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setUser_id(userId);
			}else {
				return null;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			 close(rs);
		     close(pstmt);	
		}
		return user;
		
	}
	
	public UserVo login(String userId, String userPw , Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo user = new UserVo();
		try {
			String sql=" SELECT * FROM c_user "
					+ " WHERE user_id = ? AND user_pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setUser_no(rs.getInt("userno"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_pw(rs.getString("user_pw"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_birth(rs.getString("user_birth"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_views(rs.getInt("user_views"));
			}else {
				return null;
			}
			 
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			 close(rs);
		     close(pstmt);	
		}
		return user;
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
	
	
	// 영화명 기준 체크 : 영화테이블에 해당 영화가 존재하는지
	public MovieVo chkMovieByTitle(Connection conn, String movieTitle) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MovieVo movie = null;	
		try {
			String sql = "SELECT * FROM c_movie where movie_title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,movieTitle);
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
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return movie;
	}
	
	// 영화번호 기준 체크 : 영화테이블에 해당 영화가 존재하는지
	public MovieVo chkMovieByNo(Connection conn, int movieNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MovieVo movie = null;	
		try {
			String sql = "SELECT * FROM c_movie where movie_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,movieNo);
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
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return movie;
	}
	
	// 영화 정보 추가 : 영화명, 러닝타임, 연령제한, 가격, 누적관객수를 입력받아서 영화테이블에 인서트
	public int insertMovieInfo(Connection conn, String movieTitle, int movieRuntime, String ageLimit, int moviePrice, int watched) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String sql = "INSERT INTO c_movie (movie_title ,movie_runtime ,age_limit ,movie_price ,watched) VALUES (? ,? ,? ,? ,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieTitle);
			pstmt.setInt(2, movieRuntime);
			pstmt.setString(3, ageLimit);
			pstmt.setInt(4, moviePrice);
			pstmt.setInt(5, watched);
			
			result = pstmt.executeUpdate();	
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 영화 정보 삭제 : 영화번호를 입력받아서 영화테이블에서 딜리트
	public int deleteMovie(Connection conn, int delMovieNo) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String sql = "DELETE FROM c_movie WHERE movie_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, delMovieNo);
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 영화 정보 조회 : 영화테이블의 모든 정보를 출력해주는 메소드
	public List<MovieVo> selectMovieAll(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		
		List<MovieVo> list = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM c_movie";
			rs = stmt.executeQuery(sql);
			
			list = new ArrayList<>();
			while(rs.next()) {
				MovieVo mv = new MovieVo();
				mv.setMovie_no(rs.getInt("movie_no"));
				mv.setMovie_title(rs.getString("movie_title"));
				mv.setMovie_runtime(rs.getInt("movie_runtime"));
				mv.setAge_limit(rs.getString("age_limit"));
				mv.setMovie_price(rs.getInt("movie_price"));
				mv.setWatched(rs.getInt("watched"));
				list.add(mv);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return list;
	}
}
