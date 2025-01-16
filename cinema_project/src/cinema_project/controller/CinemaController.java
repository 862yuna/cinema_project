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
	// 영화 정보 추가 : 영화명, 러닝타임, 연령제한, 가격, 누적관객수를 입력받아서 영화테이블에 인서트
	public int insertMovieInfo(String movieTitle, int movieRuntime, String ageLimit, int moviePrice, int watched) {
		return cs.insertMovieInfo(movieTitle, movieRuntime, ageLimit, moviePrice, watched);
	}
}
