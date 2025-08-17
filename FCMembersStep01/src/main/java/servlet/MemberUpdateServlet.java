package servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.MemberDao;
import dto.Member;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("member/update(get)");
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberdao = (MemberDao)sc.getAttribute("memberdao");
			int mno = (Integer) request.getAttribute("mno");
			request.setAttribute("member", memberdao.selectOne(mno)); //request 보관
			request.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletContext sc= this.getServletContext();//xml로부터 db연동을 위한 코드를 모듈화 하기위함 sc.getInitParameter("xml에 저장된 변수명")으로 파라미터 접근
			MemberDao memberdao = (MemberDao)sc.getAttribute("memberdao");
			Member member = (Member) request.getAttribute("member");
			memberdao.update(member);
			request.setAttribute("viewUrl", "redirect:list.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}