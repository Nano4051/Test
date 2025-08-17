package controls;

import java.util.Map;

import bind.DataBinding;
import dao.MemberDao;
import dto.Member;

public class MemberAddController implements Controller,DataBinding {
	MemberDao memberdao;
	public MemberAddController setMemberdao(MemberDao memberdao) {//injection
		this.memberdao = memberdao;
		return this;
	}
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"member",dto.Member.class};
	}
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		Member member = (Member) model.get("member");
		if(member.getEmail() ==null) {
			return"/member/MemberAddForm.jsp";
		}else {
			memberdao.insert(member);
			return "redirect:list.do";			
		}
	}
}