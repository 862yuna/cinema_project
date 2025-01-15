package cinema_project.view;

import java.util.Scanner;

import cinema_project.controller.CinemaController;
import cinema_project.model.vo.MovieVo;
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
		
	}
	
	// 영화 관리 
	public void manageMovie() {
		while(true) {			
			System.out.println("*** 영화 관리 ***");
			System.out.println("1. 영화 정보 추가");
			System.out.println("2. 영화 정보 삭제");
			System.out.println("3. 관리자 메뉴로 돌아가기");
			System.out.print("메뉴 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				insertMovie();
				break;
			case 2:
				break;
			case 3:
				return;
			default:
				System.out.println("올바른 메뉴를 선택해주세요.");
				continue;
			}
		}
	}
	
	// 상영정보 관리 - 추가
	public void manageScreen() {
		
	}
	
	// 영화 정보 추가
	public void insertMovie() {
		System.out.println("*** 영화 정보 추가 ***");
		System.out.println("조건에 맞게 입력해주세요.");
		System.out.print("제목 : ");
		String movieTitle = sc.nextLine();
			
		System.out.print("러닝타임(숫자) : ");
		int movieRuntime = sc.nextInt();
		sc.nextLine();
			
		System.out.print("연령제한(All, 15, 19) : ");
		String ageLimit = sc.nextLine();
		
		System.out.print("가격(숫자) : ");
		int moviePrice = sc.nextInt();
		sc.nextLine();
		
		System.out.print("누적 관객수(숫자) : ");
		int watched = sc.nextInt();
		
		MovieVo movie = cc.chkMovieByTitle(movieTitle);
		
		if(movie != null) {
			System.out.println("이미 해당 영화명을 가진 영화가 존재합니다.");
		} else {
			System.out.println("영화 추가가 가능합니다.");
		}
	}
}
