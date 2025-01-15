package cinema_project.model.service;

import java.sql.Connection;
import static cinema_project.common.CinemaTemplate.getConnection;
import static cinema_project.common.CinemaTemplate.close;
import cinema_project.model.dao.CinemaDao;
import cinema_project.model.vo.UserVo;

public class CinemaService {
	private CinemaDao cd = new CinemaDao();
	
	//회원가입
	public int insertUser(UserVo uv) {
		Connection conn = getConnection();
		int result = cd.insertUser(uv,conn);
		close(conn);
		return result;
		
	}
	
	//아이디 중복 확인
	public UserVo checkUserId(String userId) {
		Connection conn = getConnection();
		UserVo uv = cd.checkUserId(userId, conn);
		close(conn);
		return uv;
		
	}
	
	//로그인
	public UserVo login(String userId, String UserPw) {
		Connection conn = getConnection();
		UserVo uv = cd.login(userId,UserPw, conn);
		close(conn);
		return uv;
	}
	
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
}
