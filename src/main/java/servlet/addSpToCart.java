package servlet;

import beans.ChiTietGioHang;
import beans.DonHang;
import beans.Users;
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

@WebServlet(name = "addSpToCart", value = "/addSpToCart")
public class addSpToCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        response.setContentType("text/html;charset=UTF-8");

        //

        List<ChiTietGioHang> listChiTietGioHang = null;
        DonHang dh = new DonHang();

        HttpSession session = request.getSession();
        Users u = MyUtils.getLoginedUser(session);
        if (u != null) {
            int maKH = u.getMaKH();
            int num = Integer.parseInt(request.getParameter("numSP"));
            int maSP = Integer.parseInt(request.getParameter("maSP"));
            try {
                Connection conn = MyUtils.getStoredConnection(request);
                DBUtils.addSpToCart(conn, maKH, maSP, num);
                // Chuyển qua trang home
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/home");
            } catch (SQLException throwables) {
                String error = "Sản phẩm không đủ đáp ứng hoặc đã được chọn trong giỏ hàng!!";
                request.setAttribute("errorString", error);
                request.setAttribute("maSP", maSP);
                new detail().doGet(request, response);
                throwables.printStackTrace();
            }

        } else {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/signIn");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
