package controls;

import java.util.Map;

import bind.DataBinding;
import dao.MemberDao;
import dto.Member;
import jakarta.servlet.http.HttpSession;

public class MemberLoginController implements Controller,DataBinding {
	MemberDao memberdao;
	public MemberLoginController setMemberdao(MemberDao memberdao) {//injection
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
		if(member.getEmail()==null) {
			return "/auth/LoginForm.jsp";
		}else {
			Member loginMember = memberdao.login(member);
			if(loginMember != null) {
				HttpSession session = (HttpSession) model.get("session");
				session.setAttribute("loginMember",loginMember);
				return "redirect:../member/list.do";
			}else {
				return "/auth/LoginFail.jsp";
			}
		}
	}
}