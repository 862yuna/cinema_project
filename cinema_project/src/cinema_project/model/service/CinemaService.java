package cinema_project.model.service;

import java.sql.Connection;
import static cinema_project.common.CinemaTemplate.getConnection;
import static cinema_project.common.CinemaTemplate.close;
import cinema_project.model.dao.CinemaDao;

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
	public int editUserInfo(int option,Object obj,String pw) {
		Connection conn = getConnection();
		int result = cd.editUserInfo(conn,option,obj,pw);
		close(conn);
		return result;
	}
}
