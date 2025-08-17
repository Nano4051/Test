package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.apache.coyote.Request;

import dao.MemberDao;
import dto.Member;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("member/add(get)");
		request.setAttribute("viewUrl","/member/MemberAddForm.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("member/add(post)");
		try {
			ServletContext sc= this.getServletContext();//xml로부터 db연동을 위한 코드를 모듈화 하기위함 sc.getInitParameter("xml에 저장된 변수명")으로 파라미터 접근
			MemberDao memberdao = (MemberDao)sc.getAttribute("memberdao");
			Member member= (Member) request.getAttribute("member");
			memberdao.insert(member);
			request.setAttribute("viewUrl", "redirect:list.do");
			
//			if(memberdao.insert(member) >=0) {
//				
//				response.sendRedirect("list");//리스트페이지로 넘어감
//			}else {
//				throw new Exception();
//			}
		} catch (Exception e) {
			throw new ServletException();
//			e.printStackTrace();
//			request.setAttribute("error",e);
//			RequestDispatcher rd =request.getRequestDispatcher("/Error.jsp");
//			rd.forward(request, response);
		}
	}
}