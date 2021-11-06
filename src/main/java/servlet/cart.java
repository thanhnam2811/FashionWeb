package servlet;

import beans.ChiTietGioHang;
import beans.SanPham;
import beans.Users;
import beans.VanChuyen;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
        if (u != null) {
            request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
        } else {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/signIn");
        }


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        List<ChiTietGioHang> listChiTietGioHang = null;
        List<VanChuyen> listVanChuyen = null;
        List<SanPham> listSP = null;

        try {
            Connection conn = MyUtils.getStoredConnection(request);

            HttpSession session = request.getSession();
            Users u = MyUtils.getLoginedUser(session);
            if (u != null) {
                listVanChuyen = DBUtils.getAllVanChuyen(conn);
                int id = u.getMaKH();
                listChiTietGioHang = DBUtils.getChiTietGioHangByMaKH(conn, id);
                request.setAttribute("listChiTietGioHang", listChiTietGioHang);
                request.setAttribute("listSP", listSP);
                //
                double sum = DBUtils.tongTienInCart(conn, id);
                request.setAttribute("sumAll", sum);
                request.setAttribute("listVanChuyen", listVanChuyen);
            }
            //
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
