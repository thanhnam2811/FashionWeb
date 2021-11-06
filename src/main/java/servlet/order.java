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
@WebServlet(urlPatterns = "/order", name = "order")
public class order extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public order() {
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
       // request.getRequestDispatcher("home").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        List<ChiTietGioHang> listChiTietGioHang = null;
        DonHang dh = new DonHang();
        try {
            Connection conn = MyUtils.getStoredConnection(request);

            HttpSession session = request.getSession();
            Users u = MyUtils.getLoginedUser(session);
            if(u != null) {
                int id = u.getMaKH();
                listChiTietGioHang = DBUtils.getChiTietGioHangByMaKH(conn, id);//

                if(listChiTietGioHang.size() == 0)
                {
                    String errorString = "Vui lòng chọn sản phẩm để mua!!";
                    request.setAttribute("errorString", errorString);
                    request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
                }
                else {
                    //insert don hang
                    //Loi Trigger khi mua hàng
                    float sum = DBUtils.tongTienInCart(conn, id);
                    dh.setMaKH(id);
                    dh.setTenNguoiNhan(request.getParameter("nameReceiver"));
                    dh.setDiaChi(request.getParameter("province"));
                    dh.setSdt(request.getParameter("phoneNumber"));
                    dh.setMaDV(Integer.parseInt(request.getParameter("service")));
                    dh.setTongTien(sum);
                    DBUtils.insertDonHang(conn, dh);
                    //Xoa Gio Hang cua User
                    DBUtils.deleteGioHangBymaKH(conn, id);
                    //insert ChiTietDonHang
                    int maDH = DBUtils.getMaDHMaxOfMaKH(conn, id);
                    for (ChiTietGioHang list : listChiTietGioHang) {
                        DBUtils.insertChiTietDonHang(conn, maDH, list.getSanPham().getMaSP(), list.getSanPham().getSoLuongSP(), list.getThanhTien());
                    }

                    // Chuyển qua trang user để xem thông tin đơn hàng
                    String contextPath = request.getContextPath();
                    response.sendRedirect(contextPath + "/userinfo");
                }
            }
            else
            {
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/signIn");
            }
            //
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
