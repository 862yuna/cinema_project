package cinema_project.controller;

import java.util.List;

import cinema_project.model.service.CinemaService;
import cinema_project.model.vo.MovieVo;
import cinema_project.model.vo.PaymentVo;
import cinema_project.model.vo.ReservationVo;
import cinema_project.model.vo.ScreenVo;
import cinema_project.model.vo.UserVo;

public class CinemaController {
	private CinemaService cs = new CinemaService();
	
	// 예약번호로 결제정보 조회하기
	public PaymentVo findPayment(ReservationVo reserved) {
		PaymentVo result = cs.findPayment(reserved);
		return result;
	}
	
	// 결제내역 생성 메소드
	public int paymentMovie(int money, ReservationVo reserved) {
		int result = cs.paymentMovie(money, reserved);
		return result;
	}
	
	// 상영번호, 유저번호로 예약정보 찾기
	public ReservationVo findReservation(ScreenVo screen, UserVo user) {
		ReservationVo result = cs.findReservation(screen, user);
		return result;
	}
	
	// 좌석 예약하는 메소드
	public int reverseSeat(int number, ScreenVo screen, UserVo user) {
		int result = cs.reverseSeat(number, screen, user);
		return result;
	}

	// 날짜,시간으로 상영정보 검색
	public ScreenVo findScreenExist(String date, String time, String title) {
		ScreenVo result = cs.findScreenExist(date, time, title);
		return result;
	}
	
	// 영화 연령 제한 확인 메소드
	public int ageLimitCheck(String title, UserVo user) {
		int result = cs.ageLimitCheck(title, user);
		return result;
	}
	
	// 영화 존재 유무 메소드
	public MovieVo findMovieExist(String title) {
		MovieVo result = cs.findMovieExist(title);
		return result;
	}
	
	// 영화 목록 전체 출력
	public List<MovieVo> showMovieAll() {
		List<MovieVo> movieList = cs.showMovieAll();
		return movieList;
	}
	
	public int loginMember(String id,String pw) {
		return 0;
	}

	//회원가입
	public int insertUser(String userId, String userPw, String userName, String userBirth, String userEmail, String userPhone) {
		UserVo uv = new UserVo(userId, userPw, userName, userBirth, userEmail, userPhone);
		int result = cs.insertUser(uv);
		return result;
	}
	 
	//아이디 중복 확인
	public UserVo checkUserId(String userId) {
		UserVo result = cs.checkUserId(userId);
		return result;
	}
	
	
	// 로그인
	public UserVo login(String UserId,String userPw) {
		UserVo result = cs.login(UserId, userPw);
		return result;
	}
	
	// 회원 탈퇴
	public int deleteUser(String pw) {
		return cs.deleteUser(pw);
	}
	// 회원 정보 수정
	public int editUser(String pw) {
		return cs.editUser(pw);
	}
	

}
