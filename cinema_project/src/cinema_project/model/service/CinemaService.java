package cinema_project.model.service;

import java.sql.Connection;
import static cinema_project.common.CinemaTemplate.getConnection;
import static cinema_project.common.CinemaTemplate.close;
import cinema_project.model.dao.CinemaDao;
import cinema_project.model.vo.UserVo;

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

	public UserVo searchUserById(String id) {
		Connection conn = getConnection();
		UserVo user = cd.searchUserById(conn,id);
		close(conn);
		return user;
	}

	public int adminEditUser(String id, int grade) {
		Connection conn = getConnection();
		int result = cd.adminEditUser(conn, id, grade);
		close(conn);
		return result;
	}

	public int adminDeleteUser(String id) {
		Connection conn = getConnection();
		int result = cd.adminEditUser(conn, id);
		close(conn);
		return result;
	}
}
