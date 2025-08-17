package servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.MemberDao;
import dto.Member;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletContext sc= this.getServletContext();//xml로부터 db연동을 위한 코드를 모듈화 하기위함 sc.getInitParameter("xml에 저장된 변수명")으로 파라미터 접근
			MemberDao memberdao = (MemberDao)sc.getAttribute("memberdao");
			request.setAttribute("members", memberdao.selectList());
			request.setAttribute("viewUrl","/member/MemberList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}