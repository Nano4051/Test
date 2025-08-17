package listeners;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import dao.MemberDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
@WebListener //리스너로 작동하려면 애노테이션을써야한다
public class ContextLoaderListener implements ServletContextListener {
	Connection con; //con 전역변수로 선언해서 destroy에서 변수 사용가능
    public void contextInitialized(ServletContextEvent sce)  { //로딩감지 시작시에
    	System.out.println("contextInitialized 시작");
    	try {
			ServletContext sc = sce.getServletContext();
//			Class.forName(sc.getInitParameter("driver"));
//			con=DriverManager.getConnection(sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password"));
			InitialContext ic = new InitialContext();
			DataSource ds= (DataSource)ic.lookup("java:comp/env/jdbc/web");
			MemberDao memberdao = new MemberDao();
			memberdao.setDataSource(ds);
//			memberdao.setConnection(con);//인젝션
			sc.setAttribute("memberdao", memberdao); //con대신에 memberdao
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
//    public void contextDestroyed(ServletContextEvent sce)  { //프로그램 종료시
//    	try {
//			if(con!=null) con.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    }	
}