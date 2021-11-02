package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.LoaiSP;
import beans.SanPham;
import beans.SanPhamInCart;
import conn.ConnectionUtils;
import utils.DBUtils;
import utils.MyUtils;

/**
 * Servlet implementation class home
 */
@WebServlet(urlPatterns = "/home", name = "home")
public class home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<SanPham> listSP;
		List<LoaiSP> listLoaiSP;

		try {
			Connection conn = MyUtils.getStoredConnection(request);
			listSP = DBUtils.getAllSanPham(conn);
			listLoaiSP = DBUtils.getAllLoaiSP(conn);
			request.setAttribute("listSP", listSP);
			request.setAttribute("listLoaiSP", listLoaiSP);

			new cart().doPost(request, response);
			request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
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
