package controls;

import java.util.Map;

import jakarta.servlet.http.HttpSession;

public class MemberLogoutController implements Controller {
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		HttpSession session = (HttpSession) model.get("session");
		session.invalidate(); //세션 삭제
		return "redirect:login.do";
	}
}