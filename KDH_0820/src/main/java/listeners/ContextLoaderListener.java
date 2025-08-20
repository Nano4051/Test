package listeners;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import controls.BookAddController;
import controls.BookDeleteController;
import controls.BookListController;
import controls.BookSearchController;
import controls.BookUpdateController;
import dao.BookDao;
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
			DataSource ds= (DataSource)ic.lookup("java:comp/env/jdbc/web");//dao한테 전달
			BookDao bookdao = new BookDao();
			bookdao.setDataSource(ds);
			sc.setAttribute("/book/list.do", new BookListController().setBookdao(bookdao));
			sc.setAttribute("/book/add.do", new BookAddController().setBookdao(bookdao));
			sc.setAttribute("/book/update.do", new BookUpdateController().setBookdao(bookdao));
			sc.setAttribute("/book/delete.do", new BookDeleteController().setBookdao(bookdao));
			sc.setAttribute("/book/search.do", new BookSearchController().setBookdao(bookdao));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
   public void contextDestroyed(ServletContextEvent sce)  { //프로그램 종료시

   }	
}