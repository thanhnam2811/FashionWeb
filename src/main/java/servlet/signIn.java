package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Users;
import conn.ConnectionUtils;
import utils.*;

/**
 * Servlet implementation class signIn
 */
@WebServlet(urlPatterns = "/signIn", name = "signIn")
public class signIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public signIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		request.getRequestDispatcher("/WEB-INF/views/signIn.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String errorString = null;
		Users u = new Users();
		try {
			Connection conn = MyUtils.getStoredConnection(request);
			u =  DBUtils.findUser(conn, username, password);

			if(u != null)
			{
				if((u.getRoleID()) == 0)
				{
					HttpSession session = request.getSession();
					MyUtils.storeLoginedUser(session, u);
					request.getRequestDispatcher("home").forward(request, response);
				}
				else
				{
//					//request.getRequestDispatcher("productManagement").forward(request, response);
//					String contextPath = request.getContextPath();
//					response.sendRedirect(contextPath + "/productManagement");
					HttpSession session = request.getSession();
					MyUtils.storeLoginedUser(session, u);
					request.getRequestDispatcher("home").forward(request, response);

				}
			}
			else
			{
				errorString = "Tên đăng nhập hoặc mật khẩu không chính xác";
				request.setAttribute("errorString", errorString);
				request.getRequestDispatcher("/WEB-INF/views/signIn.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}