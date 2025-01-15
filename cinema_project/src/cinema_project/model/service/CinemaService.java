package cinema_project.model.service;

import java.sql.Connection;
import static cinema_project.common.CinemaTemplate.getConnection;
import static cinema_project.common.CinemaTemplate.close;
import cinema_project.model.dao.CinemaDao;

public class CinemaService {
	private CinemaDao cd = new CinemaDao();

	public int deleteUser(String pw) {
		Connection conn = getConnection();
		int result = cd.deleteUser(conn,pw);
		close(conn);
		return result;
	}
}
