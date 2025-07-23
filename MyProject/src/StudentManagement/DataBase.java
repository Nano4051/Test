package StudentManagement;
import java.util.*;
//Management 클래스에서 사용자가 입력받은 값을 처리하고 학생과 과목에 대한 정보를 key값으로 조회할 수 있도록 정보를 Map으로 구현
public class DataBase {
	//순서가 없고 중복을 허용하지 않는(Set<String>) 과목의 집합 자료형
	private final Set<String> subjectSet = new HashSet<>(); //Set인터페이스 구현체 HashSet 빠른 검색,삽입,삭제 가능
	//Map은 문자열을 key로 갖고 그 키로 연결된 여러개의 List를 값으로 저장
	private final Map<String, List<SubjectScore>> subjectScoreMap =new HashMap<>();
	//Map은 키값과 value값을 가지는 자료구조 String(key값 중복불가) List<Student>(value값 중복가능)
	Map<String,List<Student>> studentStatusMap =new HashMap<>();
	//수강생 등록
	public void createStudent(String name, String status, List<String> subjectNameList) {
		Student student = new Student(name,status);
		studentStatusMap.get(status).add(student); //map타입으로 키(status),값(student)받아서 객체추가 생성
		for(String subjectName : subjectNameList) { //과목의 이름들을 반복저장
			student.getSubjectlist().add(subjectName); //student객체가 가지고 있는 과목리스트에 과목명을 추가한다
			String key = student.getStudentId() + subjectName; //key= STU[] + 자바
			subjectSet.add(key);//[STU[],자바]
			subjectScoreMap.put(key,new ArrayList<SubjectScore>());
			
		}
	}
}