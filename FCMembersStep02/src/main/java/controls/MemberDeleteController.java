package controls;

import java.util.Map;

import dao.MemberDao;

public class MemberDeleteController implements Controller {

	@Override
	public String excute(Map<String, Object> model) throws Exception {
		MemberDao memberdao = (MemberDao) model.get("memberdao");
		if(model.get("member") == null) {
			int mno = (int) model.get("mno");
			model.put("member", memberdao.delete(mno));			
		}
		return "redirect:list.do";
	}
}