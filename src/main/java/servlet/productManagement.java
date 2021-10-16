package servlet;

import beans.LoaiSP;
import beans.SanPham;
import beans.ThuongHieu;
import conn.ConnectionUtils;
import utils.DBUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "productManagement", value = "/productManagement")
public class productManagement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Connection conn;
        List<SanPham> listSP;
        List<LoaiSP> listLoaiSP;
        List<ThuongHieu> listTH;

        try {
            conn = ConnectionUtils.getConnection();
            listSP = DBUtils.getAllSanPham(conn);
            listLoaiSP = DBUtils.getAllLoaiSP(conn);
            listTH = DBUtils.getAllThuongHieu(conn);
            request.setAttribute("listSP", listSP);
            request.setAttribute("listLoaiSP", listLoaiSP);
            request.setAttribute("listTH", listTH);
            request.getRequestDispatcher("/WEB-INF/views/product-management.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Connection conn;
            int maSP = 0; // auto tăng nên không cần thêm
            String tenSP = request.getParameter("tenSPnew");
            int maTH = Integer.valueOf(request.getParameter("maTHnew"));
            int maLoaiSP = Integer.valueOf(request.getParameter("maLoaiSPnew"));
            String hinhSP = request.getParameter("hinhSPnew");
            float giaSP = Float.valueOf(request.getParameter("giaSPnew"));
            int soLuongSP = Integer.valueOf(request.getParameter("soLuongSPnew"));
            Date ngayDangBan = Date.valueOf(request.getParameter("ngayDangBannew"));
            SanPham sp = new SanPham(maSP, tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan);

            conn = ConnectionUtils.getConnection();
            DBUtils.insertSanPham(conn, sp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            doGet(request, response);
        }

    }
}