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
	public int deleteUser(String pw) {
		return cs.deleteUser(pw);
	}

}
