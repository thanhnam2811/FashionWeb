package servlet;

import beans.LoaiSP;
import beans.SanPham;
import beans.ThuongHieu;
import conn.ConnectionUtils;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "editProduct", value = "/editProduct")
public class editProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        String maSP = request.getParameter("maSP");
        SanPham sp;
        List<LoaiSP> listLoaiSP;
        List<ThuongHieu> listTH;

        try {
            conn = ConnectionUtils.getConnection();
            sp = DBUtils.getttSanPham(conn, maSP);
            listLoaiSP = DBUtils.getAllLoaiSP(conn);
            listTH = DBUtils.getAllThuongHieu(conn);

            request.setAttribute("listLoaiSP", listLoaiSP);
            request.setAttribute("listTH", listTH);
            request.setAttribute("SP", sp);
            request.getRequestDispatcher("/WEB-INF/views/editProduct.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // SanPham(int maSP, String tenSP, int maTH, int maLoaiSP, String hinhSP, float giaSP, int soLuongSP, Date ngayDangBan)
        int maSP, maTH, maLoaiSP, soLuongSP;
        float giaSP;
        Date ngayDangBan;
        String tenSP = request.getParameter("tenSP");
        String hinhSP = request.getParameter("hinhSP");
        try {
            maSP = Integer.valueOf(request.getParameter("maSP"));
            maTH = Integer.valueOf(request.getParameter("maTH"));
            maLoaiSP = Integer.valueOf(request.getParameter("maLoaiSP"));
            soLuongSP = Integer.valueOf(request.getParameter("soLuongSP"));
            giaSP = Float.valueOf(request.getParameter("giaSP"));
            ngayDangBan = Date.valueOf(request.getParameter("ngayDangBan"));
            Connection conn = MyUtils.getStoredConnection(request);
            DBUtils.updateSanPham(conn, maSP, tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("maSP", request.getParameter("maSP"));
            request.setAttribute("errorString", e.getMessage());
            doGet(request, response);
        }
        new productManagement().doGet(request, response);
    }
}
