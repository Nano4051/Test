package controls;

import java.util.Map;

import bind.DataBinding;
import dao.MemberDao;

public class MemberDeleteController implements Controller,DataBinding {
	MemberDao memberdao;
	public MemberDeleteController setMemberdao(MemberDao memberdao) {//injection
		this.memberdao = memberdao;
		return this;
	}
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"mno",Integer.class};
	}

	@Override
	public String excute(Map<String, Object> model) throws Exception {
		if(model.get("member") == null) {
			int mno = (int) model.get("mno");
			model.put("member", memberdao.delete(mno));			
		}
		return "redirect:list.do";
	}
}