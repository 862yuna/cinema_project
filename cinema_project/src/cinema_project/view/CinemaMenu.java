package cinema_project.view;

import java.util.List;
import java.util.Scanner;

import cinema_project.controller.CinemaController;
import cinema_project.model.vo.MovieVo;
import cinema_project.model.vo.ScreenVo;
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
				System.out.println("*** 관리자 메뉴 ***");
			}else {
				//유저 메뉴
				userMenu(user);
			}
		} else System.out.println("아이디 혹은 비밀번호가 잘못되었습니다.");
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
			case 1 : reserveTicket(user); break;
			case 2 : selectByMyTicket(); break;
			case 3 : cancelTicket(); break;
			case 4 : myPage(user); break;
			default : System.out.println("처음으로 돌아갑니다.");
		}
	}
	
	// 사용자 메뉴(티켓 예매)
	public void reserveTicket(UserVo user) {
		System.out.println("*** 영화 목록(관객순) ***");
		List<MovieVo> movieList = cc.showMovieAll();
		printList(movieList);
		String movieTitle = null;
		while(true) {
			System.out.println("예매할 영화의 제목을 입력해주세요.");
			movieTitle = sc.nextLine();
			// 영화가 존재하는지 확인
			MovieVo movie = cc.findMovieExist(movieTitle);
			if(movie != null) {
				break;
			} else {
				System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
			}
			// 연령 확인
			int result = cc.ageLimitCheck(movieTitle, user);
			if(result > 0) {
				break;
			} else {
				System.out.println("해당 영화는 고객님의 연령에 적합하지 않습니다.");
			}
		}
		String date = null;
		String time = null;
		ScreenVo screen = null;
		while(true) {
			System.out.println("예매할 날짜와 시간을 입력해주세요.");
			System.out.print("날짜(OOOO-OO-OO 형식으로 입력해주세요.) : ");
			date = sc.nextLine();
			System.out.print("시간 : ");
			time = sc.nextLine();
			screen = cc.findScreenExist(date, time);
			if(screen != null) {
				break;
			} else {
				System.out.println("날짜 또는 시간을 잘못 입력하셨습니다. 입력하신 날짜 또는 시간의 영화가 없습니다.");
			}
		}
		System.out.println("예약하실 좌석수를 입력해주세요.");
		System.out.print("예약 좌석수 : ");
		int reserve_seat = sc.nextInt();
		
	}
	
	// 영화리스트 출력하는 메서드
	public void printList(List<MovieVo> list) {
		if(list.isEmpty()) {
			System.out.println("영화가 없습니다.");
		} else {
			for(MovieVo movie : list) {
				System.out.println(movie);
			}
		}
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
			case 1 : editUser(); break;
			case 2 : deleteUser(user); break;
			case 0 : System.out.println("마이페이지를 종료합니다."); return;
			default : System.out.println("메뉴를 잘못 입력하셨습니다.");
		}
	}
	
	// 사용자 메뉴(마이페이지 - 회원 정보 수정)
	public void editUser() {
		System.out.println("*** 회원 정보 수정 ***");
		
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
}