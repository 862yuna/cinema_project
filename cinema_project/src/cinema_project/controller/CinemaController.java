package cinema_project.controller;

import java.util.List;

import cinema_project.model.service.CinemaService;
import cinema_project.model.vo.MovieVo;
import cinema_project.model.vo.UserVo;


public class CinemaController {
	private CinemaService cs = new CinemaService();
	
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
}
