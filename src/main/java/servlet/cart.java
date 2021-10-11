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

import beans.SanPhamInCart;
import conn.ConnectionUtils;
import utils.DBUtils;

/**
 * Servlet implementation class cart
 */
@WebServlet(urlPatterns = "/cart", name = "cart")
public class cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
		request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn;
		List<SanPhamInCart> listSPinCart = null;
		try {
			conn = ConnectionUtils.getConnection();
			listSPinCart = DBUtils.getSanPhamInCart(conn,"1");//
			request.setAttribute("listSPinCart", listSPinCart);
			//
			double sum = DBUtils.tongTienInCart(conn,"1");
			request.setAttribute("sumAll",sum);
			//
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
