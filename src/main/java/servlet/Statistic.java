package servlet;

import beans.ChiTietDonHang;
import beans.SanPham;
import conn.ConnectionUtils;
import utils.DBUtils;

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
        Connection conn;
        List<ChiTietDonHang> listSPDaMua;
        List<SanPham> listSP;
        try {
            conn = ConnectionUtils.getConnection();
            listSPDaMua = DBUtils.getSoLuongSPDaMua(conn);
            listSP = DBUtils.getAllSanPham(conn);
            request.setAttribute("listSP", listSP);
            request.setAttribute("listSPDaMua",listSPDaMua);
            request.getRequestDispatcher("/WEB-INF/views/statistics.jsp").forward(request, response);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
