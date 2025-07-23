package StudentManagement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//필요 함수
//run 실행
//mainmenu 메뉴
//studentMenu 학생메뉴
//registerStudentMenu 학생등록
//inquireStudentMenu 학생조회
//changeStudentMenu 학생수정
//deleteStudentMenu 학생삭제
//scoreMenu 성적
//registerScoreMenu 성적등록
//inquireScoreMenu 성적조회
//changeScoreMenu 성적수정
//isDigit(String) 
//inputSelect(String number)
public class Management {
	Scanner sc = new Scanner(System.in);
	//시작
	public void run() {
		mainMenu();
	}
	private void mainMenu() {
		boolean flag = true;//반복문을 사용할때 flag를 사용함
		//루프 시작
		while(flag) {//flag가 true인 동안 반복진행
			System.out.println("메뉴화면이 나올 공간");//따로 스크린클래스를 만들어 양식을
			String input = sc.nextLine(); //입력받을 값 input
			
			switch(inputSelect(input)) {
			case 1 -> studentMenu();
			}	
		}
	}
	private void studentMenu() {
		boolean flag = true;
		while(flag) {
			//메뉴화면출력
			System.out.println("학생 메인 메뉴가 나올 공간");
			String input = sc.nextLine();
			switch (inputSelect(input)) {
//			case 1 ->  registerStudentMenu(); //수강생 등록
				
				}
			}
		}
	private boolean isDigit(String number) {
		// String 타입의 number변수를 받고 있는데 저장할 변수의 타입은 char타입이므로 변환을 해줘야하는데 배열로 바꿔준다
		if(number == null || number.isEmpty()) return false;//nullPointException 방지
		char[] chars = number.toCharArray();
		//반복을 통해 입력받은 문자배열을 아스키코드 값이랑 비교하여 문자가 맞는지를 확인
		for (char ch : chars) {//char는 문자배열임 |ㅁ|ㄴ|ㅇ|ㄹ|ㅎ|ㅂ| 배열안 문자를 꺼내서 ch에 넣어서 반복을 돌림
			if(ch < '0' || ch > '9') return false; //0~9사이의 아스키코드 값만 존재해야 하므로 그 범위를 벗어난 코드값은 사용불가
		}
		return true; //반복문을 거쳐서 검증이 되면 사용가능
	}
	private int inputSelect(String number) { //입력받은 문자열을 정수형을 변환해줌(메뉴번호)
		if(isDigit(number))
			return Integer.parseInt(number);
		else
			return 0;
	}
	private void registerStudentMenu() { //수강생 등록
		String studentSubject;
		//List=인터페이스 <String>=제네릭(List가 String 타입으로만 저장할수 있음) ArrayList<>()= 중복허용 배열클래스
		List<String>SSL = new ArrayList<>();//필수,선택 과목을 입력받을 리스트 배열=> 리스트
		System.out.println("수강등록 메뉴");
		System.out.println("수강생의 이름을 입력하세요.");
		String studentName =  sc.nextLine();
		System.out.println("수강생의 상태를 입력하세요.");
		String studentStatus =  sc.nextLine();
		do {//필수과목입력
			System.out.println("수강생의 필수과목 목록을 입력해주세요.(2개 과목 이상) [Java 객체지향 Spring JPA MySQL]");
			studentSubject =  sc.nextLine();
		} while (studentSubject.split(" ").length < 2); //ex) 자바 객체지향를 필수과목으로 지정하고 최소 2개는 받기 위함
		SSL.addAll(Arrays.asList(studentSubject.split(" "))); //입력된 과목들을 배열에서 리스트로 리스트에서 전부를 추가함
		do {//선택과목입력
	        System.out.println("수강생의 선택과목 목록을 입력해주세요. [디자인_패턴 Spring_Security Redis MongoDB]");
	        studentSubject = sc.nextLine();
	    } while (studentSubject.split(" ").length < 1);
		SSL.addAll(Arrays.asList(studentSubject.split(" ")));
		//-5s 왼쪽정렬로 5칸 확보 ex)java=[java_]공백추가, String.join()은 입력받은 과목들을 ()안에 하나의 문자열로 결합할때 사용
		System.out.printf("이름 : %-5s | 과목 : %-40s | 상태 : %-5s\n", studentName, String.join(", ",SSL),studentStatus);
		System.out.println("수강생을 등록 하시겠습니까?"); //등록확인폼 출력
	    System.out.println("1. 네");
	    System.out.println("2. 아니오");
	    String input = sc.nextLine();
	    if(inputSelect(input)==1) { //1번을 선택할시 DB에 이름 상태 과목을 저장
	    	//dataBase.createStudent(studentName,studentStatus,SSL);
	    }
	}
}