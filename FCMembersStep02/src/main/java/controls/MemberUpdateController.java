package controls;

import java.util.Map;

import dao.MemberDao;
import dto.Member;

public class MemberUpdateController implements Controller {
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		MemberDao memberdao = (MemberDao) model.get("memberdao");
		if(model.get("member")==null) {
			int mno = (int) model.get("mno");
			model.put("member", memberdao.selectOne(mno));			
			return "/member/MemberUpdateForm.jsp";
		}else{
			Member member = (Member) model.get("member");
			memberdao.update(member);
			return "redirect:list.do";			
		}
	}
}