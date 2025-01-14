package cinema_project.view;

import java.util.Scanner;

import cinema_project.controller.CinemaController;

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
		
		int result = cc.loginMember(id,pw);
		if(result>0) {
			// 관리자 아이디로 로그인시 관리자메뉴로 이동
			if(id.equals("admin")) {
				System.out.println("*** 관리자 메뉴 ***");
			}else {
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
					case 4 : myPage(id); break;
					default : System.out.println("처음으로 돌아갑니다.");
				}
			}
		}else System.out.println("아이디 혹은 비밀번호가 잘못되었습니다.");
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
	public void myPage(String id) {
		System.out.println("*** 마이페이지 ***");
		System.out.println("메뉴를 선택해주세요");
		System.out.println("1. 회원 정보 수정");
		System.out.println("2. 회원 탈퇴하기");
		System.out.println("0. 나가기");
		System.out.print("메뉴 : ");
		int menu = sc.nextInt();
		sc.nextLine();
		
		switch(menu) {
			case 1 : editUser(); break;
			case 2 : deleteUser(id); break;
			case 0 : System.out.println("마이페이지를 종료합니다."); return;
			default : System.out.println("메뉴를 잘못 입력하셨습니다.");
		}
	}
	
	// 사용자 메뉴(마이페이지 - 회원 정보 수정)
	public void editUser() {
		System.out.println("*** 회원 정보 수정 ***");
		
	}
	// 사용자 메뉴(마이페이지 - 회원 탈퇴)
	public void deleteUser(String id) {
		System.out.println("*** 회원 탈퇴 ***");
		System.out.print("비밀번호를 다시 입력하세요 : ");
		String pw = sc.nextLine();
		int cnt = cc.loginMember(id, pw);
		if(cnt>0) {
			int result = cc.deleteUser(pw);
			if(result > 0) {
				System.out.println(id+"님 회원 탈퇴되었습니다.");
			}else System.out.println("회원 탈퇴에 실패하였습니다.");
		}else System.out.println("비밀번호를 다시 확인해주세요.");
	}
}
