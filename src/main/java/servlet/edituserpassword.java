package servlet;

import java.io.IOException;
import java.sql.Connection;

import java.sql.SQLException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Users;
import conn.ConnectionUtils;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = "/edituserpassword", name = "edituserpassword")
public class edituserpassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public edituserpassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Users user = null;
		try {
			Connection conn = MyUtils.getStoredConnection(request);
			HttpSession session= request.getSession();
			Users a = (Users) session.getAttribute("loginedUser");
			int maKH=a.getMaKH();
			user = DBUtils.getInfoUser(conn, maKH);

			String oldpassword = request.getParameter("oldpassword");
			String newpassword = request.getParameter("newpassword");
			String cpassword = request.getParameter("cpassword");
			if (!user.getPassword().equals(oldpassword)) {
				String mess = "Mật khẩu cũ không đúng";
				request.setAttribute("mess", mess);
				request.setAttribute("maKH", maKH);
				response.sendRedirect("userinfo");
			} else if (!cpassword.equals(newpassword)) {
				String mess = "Confirm mật khẩu mới không đúng";
				request.setAttribute("mess", mess);
				request.setAttribute("maKH", maKH);
				response.sendRedirect("userinfo");
			} else {
				DBUtils.EditUserInfo_password(conn, maKH, newpassword);
				response.sendRedirect("userinfo");
			}

		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
