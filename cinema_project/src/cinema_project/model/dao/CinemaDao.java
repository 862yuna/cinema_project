package cinema_project.model.dao;

import static cinema_project.common.CinemaTemplate.close;
import static cinema_project.common.CinemaTemplate.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import cinema_project.model.vo.MovieVo;

import cinema_project.model.vo.PaymentVo;
import cinema_project.model.vo.ReservationVo;

import cinema_project.model.vo.ScreenVo;
import cinema_project.model.vo.UserVo;

public class CinemaDao {
	
	// 예약번호로 결제정보 조회하기
	public PaymentVo findPayment(ReservationVo reserved, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PaymentVo payment = null;
		try {
			String sql = "select * from c_payment where res_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reserved.getRes_no());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				payment = new PaymentVo();
				payment.setPay_no(rs.getInt("pay_no"));
				payment.setRes_no(rs.getInt("res_no"));
				payment.setTotal_pay(rs.getInt("total_pay"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return payment;
	}
	
	// 결제내역 생성 메소드
	public int paymentMovie(int money, ReservationVo reserved, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = "insert into c_payment(total_pay ,res_no) "
					+ "values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setInt(2, reserved.getRes_no());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 상영번호, 유저번호로 예약정보 찾기
	public ReservationVo findReservation(ScreenVo screen, UserVo user, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReservationVo reserved = null;
		
		try {
			String sql = "select * from c_reservation where user_no = ? "
					+ "and screen_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getUser_no());
			pstmt.setInt(2, screen.getScreen_no());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reserved = new ReservationVo();
				reserved.setRes_no(rs.getInt("res_no"));
				reserved.setAmount(rs.getInt("amount"));
				reserved.setUser_no(rs.getInt("user_no"));
				reserved.setScreen_no(rs.getInt("screen_no"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return reserved;
	}
	
	// 좌석 예약하는 메소드
	public int reverseSeat(int number, ScreenVo screen, UserVo user, Connection conn) {
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement checkStmt = null;
		int result1 = 0;
		int result2 = 0;
		
		try {
			// 트랜잭션 시작
			conn.setAutoCommit(false);
			
			// 현재 좌석 갯수 확인
			String checkSeatAmountSql = "select pos_seat from c_screen where screen_no = ?";
			checkStmt = conn.prepareStatement(checkSeatAmountSql);
			checkStmt.setInt(1, screen.getScreen_no());
			ResultSet rs = checkStmt.executeQuery();
			if(rs.next()) {
				int empty_seat = rs.getInt("pos_seat");
				if(empty_seat < number) {
					System.out.println("좌석 부족) 현재 좌석 : " + empty_seat + ", 예약하신 좌석 수 : " + number);
					return 0;
				}
			}
			
			// 예약가능 확인 후 예약 상태 올리기
			String sql1 = "insert into c_reservation(amount ,user_no ,screen_no) "
					+ "values(?, ?, ?)";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, number);
			pstmt1.setInt(2, user.getUser_no());
			pstmt1.setInt(3, screen.getScreen_no());
			result1 = pstmt1.executeUpdate();
			
			// 상영 정보에 예약 가능 좌석 갯수 변경
			String sql2 = "update c_screen set pos_seat = pos_seat - ? "
					+ "where screen_no = ?";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, number);
			pstmt2.setInt(2, screen.getScreen_no());
			result2 = pstmt2.executeUpdate();
			
			// 트랜잭션 커밋
			conn.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
			if(conn != null)
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(checkStmt);
			close(pstmt2);
			close(pstmt1);
		}
		// 트랜잭션 결과 반환
		return (result1 >= 1) && (result2 >= 1) ? 1 : 0;
	}
	
	// 날짜,시간으로 상영정보 검색
	public ScreenVo findScreenExist(String date, String time, String title, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ScreenVo screen = null;
		try {
			String sql = "select * from c_screen where screen_date = ? and screen_time = ? "
					+ "and movie_no = (select movie_no from c_movie where movie_title = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, time);
			pstmt.setString(3, title);
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
		//포맷터
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// 문자열 -> Date
		LocalDate date = LocalDate.parse(birth, formatter);
		
		// 생년월일을 LocalDate 타입으로 변경
		int age = (int)(ChronoUnit.YEARS.between(date, today)+2);
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

	// 회원가입
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
	
	// 아이디 중복 확인
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
	
	// 로그인
	public UserVo login(String userId, String userPw , Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo uv = null;
		try {
			String sql=" SELECT * FROM c_user "
					+ " WHERE user_id = ? AND user_pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				uv = new UserVo();
				uv.setUser_no(rs.getInt("user_no"));
				uv.setUser_id(rs.getString("user_id"));
				uv.setUser_pw(rs.getString("user_pw"));
				uv.setUser_name(rs.getString("user_name"));
				uv.setUser_birth(rs.getString("user_birth"));
				uv.setUser_views(rs.getInt("user_views"));
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
	
	// 관리자 계정 회원 조회
	public UserVo searchUserById(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo user = null;

		try {
			String sql = "SELECT * FROM c_user WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = new UserVo();
				user.setUser_no(rs.getInt("user_no"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_pw(rs.getString("user_pw"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_birth(rs.getString("user_birth"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_views(rs.getInt("user_views"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return user;
	}
	
	// 관리자 권한 회원 등급 수정
	public int adminEditUser(Connection conn, String id, int grade) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "UPDATE c_user SET level_no = ? WHERE user_id = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,grade);
			pstmt.setString(2,id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	// 관리자 권한 회원 삭제
	public int adminEditUser(Connection conn, String id) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "DELETE FROM c_user WHERE user_id = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,id);
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

	public int insertScreen(Connection conn, int mvNo, String scDate, String scTime, int thNo) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String sql = "INSERT INTO c_screen (movie_no ,screen_date ,screen_time ,theater_no) VALUES (? ,? ,? ,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mvNo);
			pstmt.setString(2, scDate);
			pstmt.setString(3, scTime);
			pstmt.setInt(4, thNo);
			
			result = pstmt.executeUpdate();	
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteScreen(Connection conn, int scNo) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String sql = "DELETE FROM c_screen WHERE screen_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scNo);
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 상영 정보 조회 : 상영정보테이블의 모든 정보를 출력해주는 메소드
	public List<ScreenVo> selectScreenAll(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		
		List<ScreenVo> list = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM c_screen";
			rs = stmt.executeQuery(sql);
			
			list = new ArrayList<>();
			while(rs.next()) {
				ScreenVo sv = new ScreenVo();
				sv.setScreen_no(rs.getInt("screen_no"));
				sv.setScreen_date(rs.getString("screen_date"));
				sv.setScreen_time(rs.getString("screen_time"));
				sv.setPos_seat(rs.getInt("pos_seat"));
				sv.setMovie_no(rs.getInt("movie_no"));
				sv.setTheater_no(rs.getInt("theater_no"));
				list.add(sv);
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
