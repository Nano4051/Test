package controls;

import java.util.Map;

public interface Controller {
	//데이터를 일반클래스에서 req를 못쓴다 key value 형태로 클래스에서 사용할수있다 Map컬렉션을 사용하면 쉽다
	String excute(Map<String,Object>model) throws Exception;//model 값 수송 객체(MVC M아님)
	//map에는 매개변수에 제한없이 사용가능
	//memberlistservlet을 클래스로 변환	
}