package servlet;

import java.io.IOException;
import java.sql.Connection;

import dao.MemberDao;
import dto.Member;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("member/delete");
		try {
			ServletContext sc= this.getServletContext();//xml로부터 db연동을 위한 코드를 모듈화 하기위함 sc.getInitParameter("xml에 저장된 변수명")으로 파라미터 접근
			MemberDao memberdao = (MemberDao)sc.getAttribute("memberdao");
			int mno = (Integer) request.getAttribute("mno");
			memberdao.delete(mno);
			request.setAttribute("viewUrl", "redirect:list.do");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}