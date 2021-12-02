package servlet;

import beans.BinhLuan;
import beans.DonHang;
import beans.SanPham;
import beans.Users;
import utils.AdminHome;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "admin", value = "/admin")
public class admin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users u = MyUtils.getLoginedUser(session);
        if(u != null && (u.getRoleID() == 2 || u.getRoleID() == 3)){

            Connection conn = MyUtils.getStoredConnection(request);
            int soLuongSP = 0;
            int soLuongTH = 0;
            int soLuongUser = 0;
            float donHangMaxCost = 0;
            List<Users> listUser = null;
            List<DonHang> doanhThu = null;

            List<SanPham> listSoLuongSPByLoaiSP = null;
            int tongSP = 0;

            List<BinhLuan> listBinhLuanHomNay = null;
            List<SanPham> listSanPham = null;
            List<Users> listAllUser = null;
            try {
                soLuongSP = AdminHome.getSoSanPhamCon(conn);
                soLuongTH = AdminHome.getSoLuongThuongHieu(conn);
                soLuongUser = AdminHome.getSoLuongUser(conn);
                donHangMaxCost = AdminHome.getDonHangMaxCost(conn);
                listUser = AdminHome.getListNhanVien(conn);
                doanhThu = AdminHome.getDoanhThuTheoThang(conn);
                listSoLuongSPByLoaiSP = AdminHome.getSoLuongSPByLoaiSP(conn);
                tongSP = AdminHome.getTongSoLuongSP(conn);

                listBinhLuanHomNay = AdminHome.getBinhLuanHomNay(conn);
                listAllUser = DBUtils.getAllUser(conn);
                listSanPham = DBUtils.getAllSanPham(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("soLuongSP", soLuongSP);
            request.setAttribute("soLuongTH", soLuongTH);
            request.setAttribute("soLuongUser", soLuongUser);
            request.setAttribute("donHangMaxCost",donHangMaxCost);
            request.setAttribute("listUser",listUser);
            request.setAttribute("doanhThu",doanhThu);
            request.setAttribute("listSoLuongSPByLoaiSP",listSoLuongSPByLoaiSP);
            request.setAttribute("tongSP",tongSP);

            request.setAttribute("listBinhLuanHomNay",listBinhLuanHomNay);
            request.setAttribute("listAllUser",listAllUser);
            request.setAttribute("listSanPham",listSanPham);
            // Page name
            request.setAttribute("pageName", "Dashboard");
            request.getRequestDispatcher("/WEB-INF/admin/admin-home.jsp").forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/signIn");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
