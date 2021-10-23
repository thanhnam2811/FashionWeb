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
import javax.servlet.http.HttpSession;

import beans.*;
import conn.ConnectionUtils;
import utils.*;

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
		HttpSession session = request.getSession();
		Users u = MyUtils.getLoginedUser(session);
		if(u != null) {
			request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
		}
		else
		{
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/signIn");
		}


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

			HttpSession session = request.getSession();
			Users u = MyUtils.getLoginedUser(session);
			if(u != null) {
				int id = u.getMaKH();
				listSPinCart = DBUtils.getSanPhamInCart(conn, id);//
				request.setAttribute("listSPinCart", listSPinCart);
				//
				double sum = DBUtils.tongTienInCart(conn, id);
				request.setAttribute("sumAll", sum);

			}
			//
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
