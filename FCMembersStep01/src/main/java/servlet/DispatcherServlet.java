package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dto.Member;
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청들어온 곳 찾기
		String servletPath = request.getServletPath();
		System.out.println("servletPath"+servletPath); //member/list.do
		
		try {
			String pageController =null;
			if("/member/list.do".equals(servletPath)) {
				pageController = "/member/list";
			}else if("/member/add.do".equals(servletPath)) {
				pageController = "/member/add";
				if(request.getParameter("email") !=null) {
					Member member = new Member();
					member.setEmail(request.getParameter("email"));
					member.setPwd(request.getParameter("pwd"));
					member.setMname(request.getParameter("mname"));
					request.setAttribute("member", member);
				}
			}else if("/member/update.do".equals(servletPath)) {
				pageController = "/member/update";
				if(request.getParameter("email")==null) {
					request.setAttribute("mno",Integer.parseInt(request.getParameter("mno")));
				}else {
					Member member = new Member();
					member.setMno(Integer.parseInt(request.getParameter("mno")));
					member.setEmail(request.getParameter("email"));
					member.setMname(request.getParameter("mname"));
					request.setAttribute("member", member);
				}
			}else if("/member/delete.do".equals(servletPath)) {
				pageController = "/member/delete";
				request.setAttribute("mno",Integer.parseInt(request.getParameter("mno")));
			}else if("/auth/login.do".equals(servletPath)) {
				pageController = "/auth/login";
				if(request.getParameter("email")!= null) {
					Member member = new Member();
					member.setEmail(request.getParameter("email"));
					member.setPwd(request.getParameter("pwd"));
					request.setAttribute("member", member);
				}
			}else if("/auth/logout.do".equals(servletPath)) {
				pageController = "/auth/logout";
			}
			RequestDispatcher rd =request.getRequestDispatcher(pageController);
			rd.include(request, response);
			String viewUrl = (String)request.getAttribute("viewUrl");
			System.out.println("viewUrl"+viewUrl);
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			}else {
				rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//error페이지로 위임
		}
		
	}
}