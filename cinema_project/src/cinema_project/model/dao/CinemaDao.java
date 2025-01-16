package cinema_project.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import cinema_project.model.vo.UserVo;

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
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	// 회원 정보 수정
	public int editUser(Connection conn,String pw) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,pw);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public UserVo searchUserById(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo user = null;

		try {
			String sql = "SELECT u.user_no ,u.user_id "
					+ ",u.user_pw ,u.user_name "
					+ ",u.user_birth ,u.user_email "
					+ ",u.user_phone ,u.user_views "
					+ ",l.level_name "
					+ "FROM c_user u "
					+ "JOIN c_level l "
					+ "ON u.level_no = l.level_no "
					+ "WHERE u.user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = new UserVo();
				user.setUser_no(rs.getInt("u.user_no"));
				user.setUser_id(rs.getString("u.user_id"));
				user.setUser_pw(rs.getString("u.user_pw"));
				user.setUser_name(rs.getString("u.user_name"));
				user.setUser_birth(rs.getString("u.user_birth"));
				user.setUser_email(rs.getString("u.user_email"));
				user.setUser_phone(rs.getString("u.user_phone"));
				user.setUser_views(rs.getInt("u.user_views"));
				user.setLevel_name(rs.getString("l.level_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return user;
	}

	public int adminEditUser(Connection conn, String id, int grade) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "UPDATE c_user SET level_no = ? WHERE user_id = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,grade);
			pstmt.setString(2,id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int adminEditUser(Connection conn, String id) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "DELETE FROM c_user WHERE user_id = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
