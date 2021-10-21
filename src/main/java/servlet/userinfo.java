package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.crypto.spec.DHGenParameterSpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ChiTietDonHang;
import beans.DonHang;
import beans.SanPham;
import beans.Users;
import conn.ConnectionUtils;
import utils.DBUtils;


@WebServlet(urlPatterns = "/userinfo", name = "userinfo")
public class userinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
		Users user = null;
		List<DonHang> listDH= null;
		List<ChiTietDonHang> listCTDH=null;
		List<SanPham> listSP = null;
		try {

			conn = ConnectionUtils.getConnection();
			HttpSession session= request.getSession();
			Users a = (Users) session.getAttribute("loginedUser");
			int maKH=a.getMaKH();
			user = DBUtils.getInfoUser(conn, maKH);
			listDH = DBUtils.getlistDonHang_bymaKH(conn, maKH);
			listCTDH= DBUtils.getChiTietDonHang_bymaKH(conn, maKH);
			listSP = DBUtils.getAllSanPham(conn);
			
			request.setAttribute("listDH", listDH);
			request.setAttribute("listCTDH", listCTDH);	
			request.setAttribute("listSP", listSP);
			request.setAttribute("info", user);
			request.getRequestDispatcher("/WEB-INF/views/userinfo.jsp").forward(request, response);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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