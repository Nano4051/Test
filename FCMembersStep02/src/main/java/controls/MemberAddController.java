package controls;

import java.util.Map;

import dao.MemberDao;
import dto.Member;

public class MemberAddController implements Controller {
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		if(model.get("member")==null) {
			return"/member/MemberAddForm.jsp";
		}else {
			MemberDao memberdao = (MemberDao) model.get("memberdao");
			Member member = (Member) model.get("member");
			memberdao.insert(member);
			return "redirect:list.do";			
		}
	}
}