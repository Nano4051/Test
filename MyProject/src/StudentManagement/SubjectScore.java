package StudentManagement;
//과목의 회차,점수,등급을 담고있는 클래스
public class SubjectScore {
	private int round;//회차
	private int score;//점수
	private char grade;//등급
	public SubjectScore(int round, int score, char grade) { //생성자
		this.round = round;
		this.score = score;
		this.grade = grade;
	}
	public int getRound() {
		return round;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}	
}