package cinema_project.model.service;

import static cinema_project.common.CinemaTemplate.close;
import static cinema_project.common.CinemaTemplate.getConnection;

import java.sql.Connection;

import cinema_project.model.dao.CinemaDao;
import cinema_project.model.vo.MovieVo;

public class CinemaService {
	private CinemaDao cd = new CinemaDao();
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
	// 영화명 기준 체크 : 영화테이블에 해당 영화가 존재하는지
	public MovieVo chkMovieByTitle(String movieTitle) {
		Connection conn = getConnection();
		MovieVo movie = cd.chkMovieByTitle(conn,movieTitle);
		close(conn);
		return movie;
	}
}
