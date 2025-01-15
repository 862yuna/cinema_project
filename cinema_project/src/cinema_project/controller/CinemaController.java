package cinema_project.controller;

import cinema_project.model.service.CinemaService;
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
	public int deleteUser(String pw) {
		return cs.deleteUser(pw);
	}
	// 회원 정보 수정
	public int editUser(String pw) {
		return cs.editUser(pw);
	}
	

}
