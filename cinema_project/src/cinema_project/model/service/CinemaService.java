package cinema_project.model.service;

import static cinema_project.common.CinemaTemplate.close;
import static cinema_project.common.CinemaTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import cinema_project.model.dao.CinemaDao;
import cinema_project.model.vo.MovieVo;
import cinema_project.model.vo.ScreenVo;
import cinema_project.model.vo.UserVo;

public class CinemaService {
	private CinemaDao cd = new CinemaDao();
	
	// 날짜,시간으로 상영정보 검색
	public ScreenVo findScreenExist(String date, String time) {
		Connection conn = getConnection();
		ScreenVo result = cd.findScreenExist(date, time, conn);
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
		
	public int deleteUser(String pw) {
		Connection conn = getConnection();
		int result = cd.deleteUser(conn,pw);
		close(conn);
		return result;
	}
	
	
}
