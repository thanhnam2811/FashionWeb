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


import beans.ChiTietGioHang;
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
		

		List<ChiTietGioHang> listChiTietGioHang = null;
		try {
			Connection conn = MyUtils.getStoredConnection(request);
			//
			String cartID = request.getParameter("cartID");
			String numProduct = request.getParameter("numProduct");
			if(Integer.valueOf(numProduct )== 0)
				DBUtils.deleteCartByID(conn, cartID);
			else
				DBUtils.update_NumProductInGioHangByID(conn, cartID, numProduct);

			response.sendRedirect(request.getContextPath() + "/cart");
		}  catch (SQLException e1) {
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
