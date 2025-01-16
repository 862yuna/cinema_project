package cinema_project.controller;

import java.util.List;

import cinema_project.model.service.CinemaService;

import cinema_project.model.vo.MovieVo;
import cinema_project.model.vo.ScreenVo;

import cinema_project.model.vo.UserVo;

public class CinemaController {
	private CinemaService cs = new CinemaService();
	

	// 날짜,시간으로 상영정보 검색
	public ScreenVo findScreenExist(String date, String time) {
		ScreenVo result = cs.findScreenExist(date, time);
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
