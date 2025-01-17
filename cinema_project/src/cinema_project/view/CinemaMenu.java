package cinema_project.view;

import java.util.List;
import java.util.Scanner;

import cinema_project.controller.CinemaController;
import cinema_project.model.vo.MovieVo;
import cinema_project.model.vo.PaymentVo;
import cinema_project.model.vo.ReservationVo;
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
				case 1 : insertUser(); break;
				case 2 : login(); break;
				case 0 : System.out.println("이용해주셔서 감사합니다."); return;
				default : System.out.println("메뉴를 잘못 입력하셨습니다.");
			}
		}
	}
	// 회원가입 
	public void insertUser() {
		System.out.println("*** 회원 가입 ***");
		while(true) {
			System.out.print("아이디 입력 : ");
			String userId = sc.nextLine();
			UserVo check = cc.checkUserId(userId);
			if(check != null) {
				System.out.println("중복되는 아이디입니다. 다시 입력해주세요.");
			}else {
				while(true) {
					System.out.print("비밀번호 입력 : ");
					String userPw = sc.nextLine();
					System.out.print("비밀번호 확인 : ");
					String checkPw = sc.nextLine();
					if(!userPw.equals(checkPw)) {
						System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
					}else {
						System.out.print("이름 입력 : ");
						String userName = sc.nextLine();
						System.out.print("생년월일 (ex)2002-06-15 : ");
						String userBirth = sc.nextLine();
						System.out.print("이메일 : ");
						String userEmail = sc.nextLine();
						System.out.print("전화번호(-) : ");
						String userPhone = sc.nextLine();
						int result = cc.insertUser(userId, userPw, userName, userBirth, userEmail, userPhone);
						printResult(result, "회원가입");
						break;
					}
				}break;
			}
		}	
	}

	// 로그인
	public void login() {
		System.out.println("*** 로그인 ***");
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userpw = sc.nextLine();
		UserVo user = cc.login(userId, userpw);
		if(user != null) {
			System.out.println(user.getUser_name()+"님 환영합니다!");
			if(user.getUser_id().equals("admin")) {
				//관리자 메뉴 호출 부탁해염 
			}else {
				userMenu(user);
			}
		}else {
			System.out.println("비밀번호 혹은 아이디가 일치하지 않습니다.");
		}
		
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
		MovieVo movie = null;
		while(true) {
			System.out.println("예매할 영화의 제목을 입력해주세요.");
			System.out.print("예약할 영화의 제목 : ");
			movieTitle = sc.nextLine();
			// 영화가 존재하는지 확인
			movie = cc.findMovieExist(movieTitle);
			if(movie != null) {
				break;
			} else {
				System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
			}
		}
		// 연령 확인
		int result = cc.ageLimitCheck(movieTitle, user);
		if(result == 0) {
			System.out.println("해당 영화는 고객님의 연령에 적합하지 않습니다.");
			userMenu(user);
		}
		String date = null;
		String time = null;
		ScreenVo screen = null;
		while(true) {
			System.out.println("예매할 날짜와 시간을 입력해주세요.");
			System.out.print("날짜(ex)2002-06-15 : ");
			date = sc.nextLine();
			System.out.print("시간(ex)16:30 : ");
			time = sc.nextLine();
			screen = cc.findScreenExist(date, time, movieTitle);
			if(screen != null) {
				break;
			} else {
				System.out.println("날짜 또는 시간을 잘못 입력하셨습니다. 입력하신 날짜 또는 시간의 영화가 없습니다.");
			}
		}
		System.out.println(screen);
		System.out.println("예약하실 좌석수를 입력해주세요.");
		System.out.print("예약 좌석수 : ");
		int number = sc.nextInt();
		int result1 = cc.reverseSeat(number, screen, user);
		if(result1 == 0) {
			System.out.println("좌석이 부족합니다. 다른 날짜 또는 시간을 이용해주세요.");
			userMenu(user);
		}
		ReservationVo reserved = cc.findReservation(screen, user);
		int views = user.getUser_views();
		double discount = 0.0;
		String level = "";
		if(views>=50) {
			level = "VVIP";
			discount = 0.9;
		} else if(views>=20) {
			level = "VIP";
			discount = 0.95;
		} else {
			level = "일반";
			discount = 1.0;
		}
		
		double total_amount = (movie.getMovie_price()*number)*discount;
		int totalAmount = (int)total_amount;
		int result3 = cc.paymentMovie(totalAmount, reserved);
		if(result3 > 0) {
			PaymentVo payment = cc.findPayment(reserved);
			System.out.println("예약이 완료되었습니다.");
			System.out.println("예약번호 : "+payment.getRes_no());
			System.out.println("결제) 총 금액 : "+movie.getMovie_price()*number);
			System.out.println("회원등급 : "+level);
			System.out.println("회원으로 할인된 가격 : "+payment.getTotal_pay());
			userMenu(user);
		} else {
			System.out.println("오류가 발생하였습니다.");
			userMenu(user);
		}
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
	
	// 결과 확인하는 메소드 
	public void printResult(int result, String menuName) {
		if(result > 0) {
			System.out.println(menuName+ "이(가) 정상적으로 완료되었습니다.");
		}else {
			System.out.println(menuName+" 중 오류가 발생되었습니다.");
		}
		
	}
	
}