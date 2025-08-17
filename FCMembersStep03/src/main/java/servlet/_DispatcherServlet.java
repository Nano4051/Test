package servlet;

import java.io.IOException;
import java.util.HashMap;

import controls.Controller;
import controls.MemberAddController;
import controls.MemberDeleteController;
import controls.MemberListController;
import controls.MemberLoginController;
import controls.MemberLogoutController;
import controls.MemberUpdateController;
import dto.Member;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//@WebServlet("*.do")
public class _DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,Object> model = new HashMap<String,Object>();
		String servletPath = request.getServletPath();		//요청들어온 곳 찾기
		System.out.println("servletPath"+servletPath); //member/list.do
		try {
			ServletContext sc = this.getServletContext();
			model.put("session", request.getSession());//로그인 세션
			Controller pageController =(Controller) sc.getAttribute(servletPath); //PageController생성
			if("/member/add.do".equals(servletPath)) { //신규등록을 하려면 요청이면
				if(request.getParameter("email") !=null) { //email값이 있으면
					Member member = new Member(); //객체생성후 신규등록시에 받는 파라미터값들을 저장후
					member.setEmail(request.getParameter("email"));
					member.setPwd(request.getParameter("pwd"));
					member.setMname(request.getParameter("mname"));
					model.put("member", member);
				}
			}else if("/member/update.do".equals(servletPath)) {//수정을 하려는 요청을 받을때
				if(request.getParameter("email")==null) { //update하기전 select를 먼저하는데 선택할때 mno값만 받아오기 때문에 받지 않는 값인 email을 널값이냐는 조건을 사용
					model.put("mno", Integer.parseInt(request.getParameter("mno")));
				}else { //email에 값이 있는건 update를 수행하겠다는 것
					Member member = new Member(); 
					member.setMno(Integer.parseInt(request.getParameter("mno")));
					member.setEmail(request.getParameter("email"));
					member.setMname(request.getParameter("mname"));
					model.put("member", member);
				}
			}else if("/member/delete.do".equals(servletPath)) {
				model.put("mno",Integer.parseInt(request.getParameter("mno")));
			}else if("/auth/login.do".equals(servletPath)) {
				if(request.getParameter("email")!= null) {
					Member member = new Member();
					member.setEmail(request.getParameter("email"));
					member.setPwd(request.getParameter("pwd"));
					model.put("member", member);
				}
			}
			String viewUrl = pageController.excute(model);
			System.out.println("viewUrl"+viewUrl);
			for(String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			}else {
				RequestDispatcher rd =request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);			//error페이지로 위임
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
}