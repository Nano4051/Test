package controls;

import java.util.Map;

import dao.MemberDao;
import dto.Member;
import jakarta.servlet.http.HttpSession;

public class MemberLoginController implements Controller {
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		if(model.get("member")==null) {
			return "/auth/LoginForm.jsp";
		}else {
			MemberDao memberdao = (MemberDao) model.get("memberdao");
			Member member = (Member)model.get("member");
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