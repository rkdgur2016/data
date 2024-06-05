package servlet;

import java.io.IOException;

import java.lang.management.PlatformLoggingMXBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import cmn.PLog;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/join.do")
public class RegisterServlet extends HttpServlet implements PLog {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("doGet");
		doWork(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("doPost");
		doWork(request, response);

	}
	
	public void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		request.setCharacterEncoding("UTF-8");
		
		String user_name = request.getParameter("user_name");
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_phone = request.getParameter("user_phone");
		String user_email = request.getParameter("user_email");
		
		request.setAttribute("user_name", user_name);
		request.setAttribute("user_id", user_id);
		request.setAttribute("user_pw", user_pw);
		request.setAttribute("user_phone", user_phone);
		request.setAttribute("user_email", user_email);
		
		System.out.println(request.getRequestURL());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/welcome.jsp");
		dispatcher.forward(request, response);
	}

}
