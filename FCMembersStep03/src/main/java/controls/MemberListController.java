package controls;

import java.util.Map;

import dao.MemberDao;

public class MemberListController implements Controller {
	MemberDao memberdao;
	public MemberListController setMemberdao(MemberDao memberdao) {//injection
		this.memberdao = memberdao;
		return this;
	} 
	
	//ds - SC는 this불가 map안에 ds넣을거임
	//MemberListServlet 기능 구현
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		model.put("members", memberdao.selectList());
		return "/member/MemberList.jsp"; //viewUrl
	}
}