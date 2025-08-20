package servlet;

import java.io.IOException;
import java.util.HashMap;

import bind.DataBinding;
import bind.ServletRequestDataBinder;
import controls.Controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet { //수정없이 재활용 가능 
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,Object> model = new HashMap<String,Object>();//맵타입 자료구조 페이지컨트롤 모델객체
		String servletPath = request.getServletPath();		//요청들어온 곳 찾기
		System.out.println("servletPath"+servletPath); //member/list.do
		try {
			ServletContext sc = this.getServletContext();
			model.put("session", request.getSession());//로그인 세션
			Controller pageController =(Controller) sc.getAttribute(servletPath); //PageController생성
			if(pageController instanceof DataBinding) { //객체가 필요한지 찾음
				prepareRequestData(request, model,(DataBinding)pageController);//준비
			}
			String viewUrl = pageController.excute(model);//실행
			System.out.println("viewUrl"+viewUrl);//경로 받고
			for(String key : model.keySet()) { //자료들을 모델정보 읽어서 req에 올림
				request.setAttribute(key, model.get(key));
			}
			if(viewUrl.startsWith("redirect:")) {//화면 위임
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
	//바인더 호출 정보를 준비
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