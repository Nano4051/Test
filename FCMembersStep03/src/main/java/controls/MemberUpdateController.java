package controls;

import java.util.Map;

import bind.DataBinding;
import dao.MemberDao;
import dto.Member;

public class MemberUpdateController implements Controller,DataBinding {
	MemberDao memberdao;
	public MemberUpdateController setMemberdao(MemberDao memberdao) {//injection
		this.memberdao = memberdao;
		return this;
	}
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"mno",Integer.class,"member",dto.Member.class};
	}
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		Member member = (Member) model.get("member");
		if(member.getEmail()==null) {
			int mno = (int) model.get("mno");
			model.put("member", memberdao.selectOne(mno));			
			return "/member/MemberUpdateForm.jsp";
		}else{
			memberdao.update(member);
			return "redirect:list.do";			
		}
	}
}