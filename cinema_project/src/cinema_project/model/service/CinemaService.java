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
		

	// 유저 번호로 예매 내역 조회
	public List<ReservationVo> selectByMyTicket(int userNo){
		Connection conn = getConnection();
		List<ReservationVo> reservationList = cd.selectByMyTicket(userNo, conn);
		close(conn);
		return reservationList;
	}


	// 회원가입
	public int insertUser(UserVo uv) {
		Connection conn = getConnection();
		int result = cd.insertUser(uv,conn);
		close(conn);
		return result;
	}
	 
	// 아이디 중복 확인
	public UserVo checkUserId(String userId) {
		Connection conn = getConnection();
		UserVo uv = cd.checkUserId(userId, conn);
		close(conn);
		return uv;
	}
	
	// 로그인
	public UserVo login(String userId, String UserPw) {
		Connection conn = getConnection();
		UserVo uv = cd.login(userId,UserPw, conn);
		close(conn);
		return uv;
	}
	
	// 회원 탈퇴

	public int deleteUser(UserVo user) {
		Connection conn = getConnection();
		int result = cd.deleteUser(conn,user);
		close(conn);
		return result;
	}
	// 회원 정보 수정(비밀번호) user_no 기준 체크
	public int editUserPw(UserVo user,String newPw) {
		Connection conn = getConnection();
		int result = cd.editUserPw(conn,user, newPw);
		close(conn);
		return result;
	}
	
	//회원 정보 수정(이메일) user_no 기준 체크
	public int editUserEmail(UserVo user,String newMail) {
		Connection conn = getConnection();
		int result = cd.editUserEmail(conn,user,newMail);
		close(conn);
		return result;
	}
	// 회원 정보 수정(전화번호) user_no 기준 체크
	public int editUserPhone(UserVo user,String newPhone) {
		Connection conn = getConnection();
		int result = cd.editUserPhone(conn,user,newPhone);
		close(conn);
		return result;
	}


	// 관리자 계정 회원 조회
	public UserVo searchUserById(String id) {
		Connection conn = getConnection();
		UserVo user = cd.searchUserById(conn,id);
		close(conn);
		return user;
	}

	// 관리자 권한 회원 등급 수정
	public int adminEditUser(String id, int grade) {
		Connection conn = getConnection();
		int result = cd.adminEditUser(conn, id, grade);
		close(conn);
		return result;
	}

	// 관리자 권한 회원 삭제
	public int adminDeleteUser(String id) {
		Connection conn = getConnection();
		int result = cd.adminEditUser(conn, id);
		close(conn);
		return result;
	}


	// 영화명 기준 체크 : 영화테이블에 해당 영화가 존재하는지
	public MovieVo chkMovieByTitle(String movieTitle) {
		Connection conn = getConnection();
		MovieVo movie = cd.chkMovieByTitle(conn,movieTitle);
		close(conn);
		return movie;
	}
	 
	// 영화번호 기준 체크 : 영화테이블에 해당 영화가 존재하는지
	public MovieVo chkMovieByNo(int movieNo) {
		Connection conn = getConnection();
		MovieVo movie = cd.chkMovieByNo(conn,movieNo);
		close(conn);
		return movie; 
	}
	
	// 영화 정보 추가 : 영화명, 러닝타임, 연령제한, 가격, 누적관객수를 입력받아서 영화테이블에 인서트
	public int insertMovieInfo(String movieTitle, int movieRuntime, String ageLimit, int moviePrice, int watched) {
		Connection conn = getConnection();
		int result = cd.insertMovieInfo(conn, movieTitle, movieRuntime, ageLimit, moviePrice, watched);
		close(conn);
		return result;
	}
	
	// 영화 정보 삭제 : 영화번호를 입력받아서 영화테이블에서 딜리트
	public int deleteMovie(int delMovieNo) {
		Connection conn = getConnection();
		int result = cd.deleteMovie(conn, delMovieNo);
		close(conn);
		return result;
	}
	
	// 영화 정보 조회 : 영화테이블의 모든 정보를 출력해주는 메소드
	public List<MovieVo> selectMovieAll() {
		Connection conn = getConnection();
		List<MovieVo> list = cd.selectMovieAll(conn);
		close(conn);
		return list;
	}
	
	// 상영 정보 조회 : 상영정보테이블의 모든 정보를 출력해주는 메소드
	public List<ScreenVo> selectScreenAll() {
		Connection conn = getConnection();
		List<ScreenVo> list = cd.selectScreenAll(conn);
		return list;
	}
}