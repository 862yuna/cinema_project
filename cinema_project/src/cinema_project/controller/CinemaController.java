package cinema_project.controller;

import java.util.List;

import cinema_project.model.service.CinemaService;
import cinema_project.model.vo.MovieVo;
import cinema_project.model.vo.PaymentVo;
import cinema_project.model.vo.ReservationVo;
import cinema_project.model.vo.ScreenVo;
import cinema_project.model.vo.UserVo;
import cinema_project.model.vo.MovieVo;
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
	
	//유저 번호로 예매 내역 조회
	public List<ReservationVo> selectByMyTicket(int userNo){
		List<ReservationVo> reservationList = cs.selectByMyTicket(userNo);
		return reservationList;
	}
	
	// 예매 취소 결과 확인
	public int cancelTicket() {
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
	public int deleteUser(UserVo user) {
		return cs.deleteUser(user);
	}
	// 회원 정보 수정(비밀번호)
	public int editUserPw(UserVo user,String pw) {
		return cs.editUserPw(user,pw);
	}
	// 회원 정보 수정(이메일)
	public int editUserEmail(UserVo user,String newMail) {
		return cs.editUserEmail(user,newMail);
	}
	// 회원 정보 수정(전화번호)
	public int editUserPhone(UserVo user,String newPhone) {
		return cs.editUserPhone(user,newPhone);
	}
 
	// 회원 검색(아이디)
	public UserVo searchUserById(String id) {
		return cs.searchUserById(id);
	}
	// 관리자 : 회원정보 수정
	public int adminEditUser(String id, int grade) {
		return cs.adminEditUser(id, grade);
	}
	// 관리자 : 회원 삭제
	public int adminDeleteUser(String id) {
		return cs.adminDeleteUser(id);
	}

	// 영화명 기준 체크 : 영화테이블에 해당 영화가 존재하는지
	public MovieVo chkMovieByTitle(String movieTitle) {
		return cs.chkMovieByTitle(movieTitle);
	}
	// 영화번호 기준 체크 : 영화테이블에 해당 영화가 존재하는지
	public MovieVo chkMovieByNo(int movieNo) {
		return cs.chkMovieByNo(movieNo);
	}
	// 영화 정보 추가 : 영화명, 러닝타임, 연령제한, 가격, 누적관객수를 입력받아서 영화테이블에 인서트
	public int insertMovieInfo(String movieTitle, int movieRuntime, String ageLimit, int moviePrice, int watched) {
		return cs.insertMovieInfo(movieTitle, movieRuntime, ageLimit, moviePrice, watched);
	}
	// 영화 정보 삭제 : 영화번호를 입력받아서 영화테이블에서 딜리트
	public int deleteMovie(int delMovieNo) {
		return cs.deleteMovie(delMovieNo);
	}
	// 영화 정보 조회 : 영화테이블의 모든 정보를 출력해주는 메소드
	public List<MovieVo> selectMovieAll() {
		return cs.selectMovieAll();
	}
	// 상영 정보 조회 : 상영정보테이블의 모든 정보를 출력해주는 메소드
	public List<ScreenVo> selectScreenAll() {
		return cs.selectScreenAll();	
	}
}
