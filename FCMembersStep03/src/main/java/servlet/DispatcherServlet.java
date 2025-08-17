package servlet;

import java.io.IOException;
import java.util.HashMap;

import bind.DataBinding;
import bind.ServletRequestDataBinder;
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
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,Object> model = new HashMap<String,Object>();
		String servletPath = request.getServletPath();		//요청들어온 곳 찾기
		System.out.println("servletPath"+servletPath); //member/list.do
		try {
			ServletContext sc = this.getServletContext();
			model.put("session", request.getSession());//로그인 세션
			Controller pageController =(Controller) sc.getAttribute(servletPath); //PageController생성
			if(pageController instanceof DataBinding) {
				prepareRequestData(request, model,(DataBinding)pageController);
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
	private void prepareRequestData(HttpServletRequest request,HashMap<String, Object> model, DataBinding dataBinding)throws Exception {
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null;
		Class<?> dataType = null;
		Object dataObj = null;
		for (int i = 0; i < dataBinders.length; i+=2) {
			dataName = (String)dataBinders[i];
			dataType = (Class<?>) dataBinders[i+1];
			dataObj = ServletRequestDataBinder.bind(request, dataType, dataName);
			model.put(dataName, dataObj);
		}
	}
}