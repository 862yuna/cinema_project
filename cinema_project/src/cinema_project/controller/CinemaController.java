package cinema_project.controller;

import cinema_project.model.service.CinemaService;
import cinema_project.model.vo.MovieVo;

public class CinemaController {
	private CinemaService cs = new CinemaService();
	// 로그인
	public int loginMember(String id,String pw) {
		return 0;
	}
	// 회원 탈퇴
	public int deleteUser(String pw) {
		return cs.deleteUser(pw);
	}
	// 회원 정보 수정
	public int editUser(String pw) {
		return cs.editUser(pw);
	}
	// 영화명 기준 체크 : 영화테이블에 해당 영화가 존재하는지
	public MovieVo chkMovieByTitle(String movieTitle) {
		return cs.chkMovieByTitle(movieTitle);
	}
}
