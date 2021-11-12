package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.ConnectionUtils;
import utils.DBUtils;
import utils.MyUtils;

/**
 * Servlet implementation class signUp
 */
@WebServlet(urlPatterns = "/signUp", name = "signUp")
public class signUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		request.getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String hoten = request.getParameter("hoten");
		String sdt = request.getParameter("sdt");
		String ngaySinh =  request.getParameter("ngaySinh");
		String diaChi = request.getParameter("diaChi");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Date date = Date.valueOf(ngaySinh);
		try {
			// Tạo đối tượng Connection kết nối database.
			Connection conn = ConnectionUtils.getConnection("sa", "123");
//			Connection conn = MyUtils.getStoredConnection(request);
			DBUtils.insertUser(conn, hoten,sdt,date,diaChi,username,password);
			request.getRequestDispatcher("/signIn").forward(request, response);
		} catch ( SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
