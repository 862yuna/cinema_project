package cinema_project.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;

import static cinema_project.common.CinemaTemplate.close;

public class CinemaDao {
	// 회원 탈퇴 
	public int deleteUser(Connection conn,String pw) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "DELETE FROM c_user "
					+ "WHERE user_pw = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	// 회원 정보 수정
	public int editUserInfo(Connection conn,int option,Object obj,String pw) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			switch(option){
				case 1 : 
					String sql = "UPDATE c_user SET user_pw = ? "
							+ "WHERE user_pw = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,(String)obj);
					pstmt.setString(2,pw);
					result=pstmt.executeUpdate();
				case 2 : 	
			}
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setString(1,pw);
//			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
