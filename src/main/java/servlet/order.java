package servlet;

import beans.DonHang;
import beans.SanPhamInCart;
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
import java.util.List;

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

        List<SanPhamInCart> listSPinCart = null;
        DonHang dh = new DonHang();
        Connection conn = MyUtils.getStoredConnection(request);

        HttpSession session = request.getSession();
        Users u = MyUtils.getLoginedUser(session);

        if (u == null) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/signIn");
        }
        try {
            int id = u.getMaKH();
            listSPinCart = DBUtils.getSanPhamInCart(conn, id);//

            if (listSPinCart.size() == 0) {
                String errorString = "Vui lòng chọn sản phẩm để mua!!";
                throw new Exception(errorString);
            } else {
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
                //insert ChiTietDonHang
                int maDH = DBUtils.getMaDHMaxOfMaKH(conn, id);
                for (SanPhamInCart list : listSPinCart) {
                    DBUtils.insertChiTietDonHang(conn, maDH, list.getMaSP(), list.getSoLuongSP(), list.getThanhTien());
                }
                //Xoa Gio Hang cua User
                DBUtils.deleteGioHangBymaKH(conn, id);
            }
            //
        } catch (Exception e) {
            request.setAttribute("errorString", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
            return;
        }
        // Chuyển qua trang user để xem thông tin đơn hàng
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/userinfo");
    }

}
