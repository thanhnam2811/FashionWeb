package servlet;

import beans.ChiTietDonHang;
import beans.DonHang;
import beans.SanPham;
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


@WebServlet(urlPatterns = "/userinfo", name = "userinfo")
public class userinfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public userinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users user = null;
        List<DonHang> listDH = null;
        List<ChiTietDonHang> listCTDH = null;
        List<SanPham> listSP = null;
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        Users a = MyUtils.getLoginedUser(session);
        if (a == null) {
            response.sendRedirect("signIn");
            return;
        }
        try {
            int maKH = a.getMaKH();
            user = DBUtils.getInfoUser(conn, maKH);
            listDH = DBUtils.getlistDonHang_bymaKH(conn, maKH);
            listCTDH = DBUtils.getChiTietDonHang_bymaKH(conn, maKH);
            listSP = DBUtils.getAllSanPham(conn);

            request.setAttribute("listDH", listDH);
            request.setAttribute("listCTDH", listCTDH);
            request.setAttribute("listSP", listSP);
            request.setAttribute("info", user);
            request.getRequestDispatcher("/WEB-INF/views/userinfo.jsp").forward(request, response);
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
