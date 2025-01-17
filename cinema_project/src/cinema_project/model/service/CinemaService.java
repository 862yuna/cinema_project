package cinema_project.model.service;

import static cinema_project.common.CinemaTemplate.close;
import static cinema_project.common.CinemaTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import cinema_project.model.dao.CinemaDao;

import cinema_project.model.vo.MovieVo;
import cinema_project.model.vo.PaymentVo;
import cinema_project.model.vo.ReservationVo;
import cinema_project.model.vo.ScreenVo;

import cinema_project.model.vo.UserVo;

public class CinemaService {
	private CinemaDao cd = new CinemaDao();
	
	// 예약번호로 결제정보 조회하기
	public PaymentVo findPayment(ReservationVo reserved) {
		Connection conn = getConnection();
		PaymentVo result = cd.findPayment(reserved, conn);
		close(conn);
		return result;
	}
	
	// 결제내역 생성 메소드
	public int paymentMovie(int money, ReservationVo reserved) {
		Connection conn = getConnection();
		int result = cd.paymentMovie(money, reserved, conn);
		close(conn);
		return result;
	}
	
	// 상영번호, 유저번호로 예약정보 찾기
	public ReservationVo findReservation(ScreenVo screen, UserVo user) {
		Connection conn = getConnection();
		ReservationVo result = cd.findReservation(screen, user, conn);
		close(conn);
		return result;
	}
	
	// 좌석 예약하는 메소드
	public int reverseSeat(int number, ScreenVo screen, UserVo user) {
		Connection conn = getConnection();
		int result = cd.reverseSeat(number, screen, user, conn);
		close(conn);
		return result;
	}

	// 날짜,시간으로 상영정보 검색
	public ScreenVo findScreenExist(String date, String time, String title) {
		Connection conn = getConnection();
		ScreenVo result = cd.findScreenExist(date, time, title, conn);
		close(conn);
		return result;
	}
	
	// 영화 연령 제한 메소드
	public int ageLimitCheck(String title, UserVo user) {
		Connection conn = getConnection();
		int result = cd.ageLimitCheck(title, user, conn);
		close(conn);
		return result;
	}
	
	// 영화 존재 유무 메소드
	public MovieVo findMovieExist(String title) {
		Connection conn = getConnection();
		MovieVo result = cd.findMovieExist(title, conn);
		close(conn);
		return result;
	}
	
	// 영화 목록 전체 출력
	public List<MovieVo> showMovieAll() {
		Connection conn = getConnection();
		List<MovieVo> movieList = cd.showMovieAll(conn);
		close(conn);
		return movieList;
	}
		

	//회원가입
	public int insertUser(UserVo uv) {
		Connection conn = getConnection();
		int result = cd.insertUser(uv,conn);
		close(conn);
		return result;
		
	}
	 
	//아이디 중복 확인
	public UserVo checkUserId(String userId) {
		Connection conn = getConnection();
		UserVo uv = cd.checkUserId(userId, conn);
		close(conn);
		return uv;
		
	}
	
	//로그인
	public UserVo login(String userId, String UserPw) {
		Connection conn = getConnection();
		UserVo uv = cd.login(userId,UserPw, conn);
		close(conn);
		return uv;
	}
	
	// 회원 탈퇴

	public int deleteUser(String pw) {
		Connection conn = getConnection();
		int result = cd.deleteUser(conn,pw);
		close(conn);
		return result;
	}

	// 회원 정보 수정
	public int editUser(String pw) {
		Connection conn = getConnection();
		int result = cd.editUser(conn,pw);
		close(conn);
		return result;
	}

}
