package StudentManagement;
import java.util.ArrayList;
import java.util.List;

public class Student {
	private static int NO =1;
	private String studentId;
	private String studentName;
	private String status;
	private final List<String>subjectlist = new ArrayList<>();
	
	public Student(String studentName, String status) {
		studentId = "STU" + NO;
		this.studentName = studentName;
		this.status = status;
		++NO; //수강생 객체가 중복되지 않기 위해서 1증가 NO++도 가능 ID기능을 구현할때 중요함
	}
	//private로 선언되어 getter사용
	public String getStudentId() { //수강생번호
		return studentId;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStudentName() {
		return studentName;
	}
	public String getStatus() {
		return status;
	}
	public List<String> getSubjectlist() { //과목 리스트사용할때
		return subjectlist;
	}
}
