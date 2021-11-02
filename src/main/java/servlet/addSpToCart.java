package servlet;

import beans.DonHang;
import beans.SanPhamInCart;
import beans.Users;
import conn.ConnectionUtils;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
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

        List<SanPhamInCart> listSPinCart = null;
        DonHang dh = new DonHang();

        HttpSession session = request.getSession();
        Users u = MyUtils.getLoginedUser(session);
        if(u != null) {
            int maKH = u.getMaKH();
            int num = Integer.parseInt(request.getParameter("numSP"));
            int maSP = Integer.parseInt(request.getParameter("maSP"));
            Float giaSP = Float.parseFloat(request.getParameter("giaSP"));

            Float thanhTien = giaSP * num;
            try {
                Connection conn = MyUtils.getStoredConnection(request);
                DBUtils.addSpToCart(conn, maKH, maSP, num, thanhTien);
                // Chuyển qua trang home
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/home");
            } catch (SQLException throwables) {

                String error = "Sản phẩm không đủ đáp ứng hoặc đã được chọn trong giỏ hàng!!";
                request.setAttribute("errorString", error);
                request.setAttribute("maSP",maSP);
                new detail().doGet(request,response);

                throwables.printStackTrace();

            }

        }
        else
        {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/signIn");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
