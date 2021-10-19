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
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/userinfo");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Connection conn;
        List<SanPhamInCart> listSPinCart = null;
        DonHang dh = new DonHang();
        try {
            conn = ConnectionUtils.getConnection();

            HttpSession session = request.getSession();
            Users u = MyUtils.getLoginedUser(session);
            if(u != null) {
                int id = u.getMaKH();
                listSPinCart = DBUtils.getSanPhamInCart(conn, id);//

                //insert don hang
                float sum = DBUtils.tongTienInCart(conn, id);
                dh.setMaKH(id);
                dh.setTongTien(sum);
                DBUtils.insertDonHang(conn,dh);
                //insert ChiTietDonHang
                int maDH = DBUtils.getMaDHMaxOfMaKH(conn,id);
                for(SanPhamInCart list : listSPinCart)
                {
                    DBUtils.insertChiTietDonHang(conn,maDH,list.getMaSP(),list.getSoLuongSP(),list.getThanhTien());
                }
                //Xoa Gio Hang cua User
                DBUtils.deleteGioHangBymaKH(conn,id);

            }
            //
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
