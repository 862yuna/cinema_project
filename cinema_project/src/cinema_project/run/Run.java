package cinema_project.run;

import cinema_project.view.CinemaMenu;

public class Run {
	public static void main(String[] args) {
		CinemaMenu cm = new CinemaMenu();
		String id = "admin";
		cm.adminEditUser(id);
	}
}
