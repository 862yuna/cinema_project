package cinema_project.view;

import java.util.Scanner;

import cinema_project.controller.CinemaController;
import cinema_project.model.vo.UserVo;

public class CinemaMenu {
	private Scanner sc = new Scanner(System.in);
	private CinemaController cc = new CinemaController();
	// 메인메뉴
	public void mainMenu() {
		System.out.println("환영합니다.");
		while(true) {
			System.out.println("*** 메뉴를 선택해주세요 ***");
			System.out.println("1. 회원 가입");
			System.out.println("2. 로그인");
			System.out.println("0. 프로그램 종료");
			
			System.out.print("메뉴 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			switch(menu) {
				case 1 : signIn(); break;
				case 2 : login(); break;
				case 0 : System.out.println("이용해주셔서 감사합니다."); return;
				default : System.out.println("메뉴를 잘못 입력하셨습니다.");
			}
		}
	}
	// 회원가입 
	public void signIn() {
		System.out.println("*** 회원 가입 ***");
	}
	// 로그인
	public void login() {
		System.out.println("*** 로그인 ***");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();
		
//		UserVo user = cc.loginMember(id,pw);
		UserVo user = new UserVo();
		if(user != null) {
			// 관리자 아이디로 로그인시 관리자메뉴로 이동
			if(id.equals("admin")) {
				adminMenu();
			}else {
				//유저 메뉴
				userMenu(user);
			}
		}else System.out.println("아이디 혹은 비밀번호가 잘못되었습니다.");
	}
	
	// 사용자 메뉴
	public void userMenu(UserVo user) {
		System.out.println("*** 사용자 메뉴 ***");
		System.out.println("1. 티켓 예매하기");
		System.out.println("2. 예매 내역 조회");
		System.out.println("3. 예매 취소");
		System.out.println("4. 마이페이지");
		System.out.print("메뉴 선택 : ");
		int menu = sc.nextInt();
		sc.nextLine();
		switch(menu) {
			case 1 : reserveTicket(); break;
			case 2 : selectByMyTicket(); break;
			case 3 : cancelTicket(); break;
			case 4 : myPage(user); break;
			default : System.out.println("처음으로 돌아갑니다.");
		}
	}	
	
	// 사용자 메뉴(티켓 예매)
	public void reserveTicket() {
		
	}
	
	// 사용자 메뉴(예매 내역 조회)
	public void selectByMyTicket() {
		
	}
	
	// 사용자 메뉴(예매 취소)
	public void cancelTicket() {
		
	}
	
	// 사용자 메뉴(마이페이지)
	public void myPage(UserVo user) {
		System.out.println("*** 마이페이지 ***");
		System.out.println("메뉴를 선택해주세요");
		System.out.println("1. 회원 정보 수정");
		System.out.println("2. 회원 탈퇴하기");
		System.out.println("0. 나가기");
		System.out.print("메뉴 : ");
		int menu = sc.nextInt();
		sc.nextLine();
		
		switch(menu) {
			case 1 : editUser(user); break;
			case 2 : deleteUser(user); break;
			case 0 : System.out.println("마이페이지를 종료합니다."); return;
			default : System.out.println("메뉴를 잘못 입력하셨습니다.");
		}
	}
	
	// 사용자 메뉴(마이페이지 - 회원 정보 수정)
	public void editUser(UserVo user) {
		System.out.println("*** 회원 정보 수정 ***");
		System.out.print("비밀번호를 다시 입력하세요 : ");
		String pw = sc.nextLine();
		if(user.getUser_pw().equals(pw)) {
			int result = cc.editUser(pw);
			if(result > 0) {
				System.out.println(user.getUser_id()+"님의 정보가 수정되었습니다.");
			}else System.out.println("정보 수정에 실패하였습니다.");
		}else System.out.println("비밀번호를 다시 확인해주세요.");
		
	}
	// 사용자 메뉴(마이페이지 - 회원 탈퇴)
	public void deleteUser(UserVo user) {
		System.out.println("*** 회원 탈퇴 ***");
		System.out.print("비밀번호를 다시 입력하세요 : ");
		String pw = sc.nextLine();
		if(user.getUser_pw().equals(pw)) {
			int result = cc.deleteUser(pw);
			if(result > 0) {
				System.out.println(user.getUser_id()+"님 회원 탈퇴되었습니다.");
			}else System.out.println("회원 탈퇴에 실패하였습니다.");
		}else System.out.println("비밀번호를 다시 확인해주세요.");
	}
	// 관리자 메뉴
	public void adminMenu() {
		while(true) {
			System.out.println("*** 관리자 메뉴 ***");
			System.out.println("1. 회원관리");
			System.out.println("2. 영화관리");
			System.out.println("3. 상영정보관리");
			System.out.println("4. 로그아웃");
			System.out.print("메뉴  : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				manageUser();
				break;
			case 2:
				manageMovie();
				break;
			case 3:
				manageScreen();
				break;
			case 4:
				System.out.println("로그아웃");
				return;
			default:
				System.out.println("올바른 메뉴를 선택해주세요.");
				continue;
			}
		}
	}
	
	// 회원 관리
	public void manageUser() {
		System.out.println("*** 회원 관리 ***");
		System.out.println("1. 회원 조회");
		System.out.println("2. 관리자 메뉴 돌아가기");
		int menu = sc.nextInt();
		sc.nextLine();
		
		switch (menu) {
		case 1:
			searchUser();
			break;
		case 2:
			return;
		default:
			System.out.println("올바른 메뉴를 선택해주세요.");
			break;
		}
	}
	
	private void searchUser() {
		System.out.println("*** 회원 조회 ***");
		System.out.print("조회 아이디 : ");
		String id = sc.nextLine();
		UserVo user = cc.searchUserById(id);
		if (user != null) {
			System.out.println(user);
			System.out.println();
			System.out.println("1. 회원 등급 수정");
			System.out.println("2. 회원 삭제");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				adminEditUser(id);
				break;
			case 2:
				adminDeleteUser(id);
			default:
				break;
			}
		} else {
			System.out.println("조회된 결과가 없습니다.");
		}
	}
	public void adminDeleteUser(String id) {
		System.out.println("삭제하시려면 1을 입력해주세요(취소하시려면 아무키나 눌러주세요)");
		int menu = sc.nextInt();
		sc.nextLine();
		
		switch (menu) {
		case 1:
			int result = cc.adminDeleteUser(id);
			if(result>0) {
				System.out.println("삭제되었습니다.");
			}else {
				System.out.println("삭제 시도 중 오류가 발생하였습니다.");
			}
			break;
		default:
			System.out.println("취소되었습니다.");
			return;
		}
	}
	
	public void adminEditUser(String id) {
		System.out.println("*** 변경 등급 선택 ***");
		System.out.println("1. 일반");
		System.out.println("2. VIP");
		System.out.println("3. VVIP");
		System.out.println("다른 키 입력 시 취소");
		int grade = sc.nextInt();
		sc.nextLine();
		
		if(grade<1||grade>3) {
			System.out.println("취소되었습니다.");
			return;
		}
		int result = cc.adminEditUser(id,grade);
		if(result>0) {
			if(grade==1) {
				System.out.println("회원 등급이 일반 등급으로 수정되었습니다.");
			}else if(grade==2) {
				System.out.println("회원 등급이 VIP 등급으로 수정되었습니다.");
			}else if(grade==3) {
				System.out.println("회원 등급이 VVIP 등급으로 수정되었습니다.");
			}
		} else {
			System.out.println("수정 실패");
		}
	}
	// 영화 관리 - 추가, 
	public void manageMovie() {
		
	}
	
	// 상영정보 관리 - 추가
	public void manageScreen() {
		
	}
}

