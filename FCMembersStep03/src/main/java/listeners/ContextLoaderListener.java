package listeners;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import controls.MemberAddController;
import controls.MemberDeleteController;
import controls.MemberListController;
import controls.MemberLoginController;
import controls.MemberLogoutController;
import controls.MemberUpdateController;
import dao.MemberDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
@WebListener //리스너로 작동하려면 애노테이션을써야한다
public class ContextLoaderListener implements ServletContextListener {
	Connection con; //con 전역변수로 선언해서 destroy에서 변수 사용가능
    public void contextInitialized(ServletContextEvent sce)  { //로딩감지 시작시에
    	System.out.println("contextInitialized 시작");
    	try {
			ServletContext sc = sce.getServletContext();
			InitialContext ic = new InitialContext();
			DataSource ds= (DataSource)ic.lookup("java:comp/env/jdbc/web");
			MemberDao memberdao = new MemberDao();
			memberdao.setDataSource(ds);
			sc.setAttribute("/member/list.do", new MemberListController().setMemberdao(memberdao));
			sc.setAttribute("/member/add.do", new MemberAddController().setMemberdao(memberdao));
			sc.setAttribute("/member/update.do", new MemberUpdateController().setMemberdao(memberdao));
			sc.setAttribute("/member/delete.do", new MemberDeleteController().setMemberdao(memberdao));
			sc.setAttribute("/auth/login.do", new MemberLoginController().setMemberdao(memberdao));
			sc.setAttribute("/auth/logout.do", new MemberLogoutController());
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