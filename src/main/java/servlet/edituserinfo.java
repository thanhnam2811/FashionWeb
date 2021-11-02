package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import beans.LoaiSP;
import beans.SanPham;
import beans.Users;
import conn.ConnectionUtils;
import utils.DBUtils;
import utils.MyUtils;

/**
 * Servlet implementation class edituserinfo
 */
@WebServlet(urlPatterns = "/edituserinfo", name = "edituserinfo")
public class edituserinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public edituserinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Users user = null;
		try {
			Connection conn = MyUtils.getStoredConnection(request);
			HttpSession session= request.getSession();
			Users a = (Users) session.getAttribute("loginedUser");
			int maKH=a.getMaKH();
			user = DBUtils.getInfoUser(conn, maKH);

			String hoTen=request.getParameter("hoten");
			String sdt=request.getParameter("sdt");
			Date ngaySinh=Date.valueOf(request.getParameter("namsinh"));
			String diaChi=request.getParameter("diachi");
			DBUtils.EditUserInfo(conn, maKH, hoTen, sdt, ngaySinh, diaChi);
			response.sendRedirect("userinfo");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
