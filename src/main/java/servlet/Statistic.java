package servlet;

import beans.ChiTietDonHang;
import beans.DonHang;
import beans.SanPham;
import beans.Users;
import conn.ConnectionUtils;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Statistic", value = "/Statistic")
public class Statistic extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<ChiTietDonHang> listSPDaMua;
        List<SanPham> listSP;
        List<DonHang> listDH;

        HttpSession session = request.getSession();
        Users u = MyUtils.getLoginedUser(session);
        if(u == null) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/signIn");
        }
        else {
            if (u.getRoleID() == 2 || u.getRoleID() == 3) {
                try {
                    Connection conn = MyUtils.getStoredConnection(request);
                    listSPDaMua = DBUtils.getSoLuongSPDaMua(conn);
                    listSP = DBUtils.getAllSanPham(conn);
                    listDH = DBUtils.getDanhThuTheoNgay(conn);
                    request.setAttribute("listSP", listSP);
                    request.setAttribute("listSPDaMua", listSPDaMua);
                    request.setAttribute("listDT", listDH);
                    request.getRequestDispatcher("/WEB-INF/views/statistics.jsp").forward(request, response);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                request.getRequestDispatcher("/WEB-INF/views/errorAccess.jsp").forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
