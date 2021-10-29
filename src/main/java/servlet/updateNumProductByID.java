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


import beans.SanPhamInCart;
import beans.Users;
import conn.ConnectionUtils;
import utils.DBUtils;
import utils.MyUtils;

/**
 * Servlet implementation class updateNumProductByID  
 */
@WebServlet(urlPatterns = "/updateNumProductByID", name = "updateNumProductByID")
public class updateNumProductByID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateNumProductByID() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Connection conn;
		List<SanPhamInCart> listSPinCart = null;
		try {
			conn = ConnectionUtils.getConnection();
			//
			String cartID = request.getParameter("cartID");
			String numProduct = request.getParameter("numProduct");
			//num-product is null
			DBUtils.update_NumProductInGioHangByID(conn, cartID, numProduct);
			//
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
			request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
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
