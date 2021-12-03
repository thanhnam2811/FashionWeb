package servlet;

import beans.LoaiSP;
import beans.SanPham;
import beans.ThuongHieu;
import beans.Users;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "manage-product", value = "/manage-product")
public class manageproduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SanPham> listSP;
        List<LoaiSP> listLoaiSP;
        List<ThuongHieu> listTH;

        HttpSession session = request.getSession();
        Users u = MyUtils.getLoginedUser(session);
        if(u != null && (u.getRoleID() == 2 || u.getRoleID() == 3)){
            try {
                Connection conn = MyUtils.getStoredConnection(request);
                listSP = DBUtils.getAllSanPham(conn);
                listLoaiSP = DBUtils.getAllLoaiSP(conn);
                listTH = DBUtils.getAllThuongHieu(conn);
                request.setAttribute("listSP", listSP);
                request.setAttribute("listLoaiSP", listLoaiSP);
                request.setAttribute("listTH", listTH);

                // Page name
                request.setAttribute("pageName", "Product");
                request.getRequestDispatcher("/WEB-INF/admin/table-product.jsp").forward(request, response);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        else {
            response.sendRedirect(request.getContextPath() + "/signIn");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users u = MyUtils.getLoginedUser(session);
        if(u != null && (u.getRoleID() == 3 || u.getRoleID() == 2)){
            try {
                Connection conn = MyUtils.getStoredConnection(request);
                String maSP = request.getParameter("_maSP");
                String tenSP = request.getParameter("_tenSP");
                int maTH = Integer.valueOf(request.getParameter("_maTH"));
                int maLoaiSP = Integer.valueOf(request.getParameter("_maLoaiSP"));
                String hinhSP = request.getParameter("_hinhSP");
                float giaSP = Float.valueOf(request.getParameter("_giaSP"));
                int soLuongSP = Integer.valueOf(request.getParameter("_soLuongSP"));
                Date ngayDangBan = Date.valueOf(request.getParameter("_ngayBan"));

                // Không có id hoặc id không tồn tại thì thêm mới
                if (maSP == null || DBUtils.getttSanPham(conn, maSP) == null) {
                    // id tự tăng nên không cần thêm
                    SanPham sp = new SanPham(0, tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan);
                    DBUtils.insertSanPham(conn, sp);
                    System.out.println("Thêm sản phẩm: " + sp );
                } else {
                    DBUtils.updateSanPham(conn, Integer.valueOf(maSP), tenSP, maLoaiSP, maTH, hinhSP, giaSP, soLuongSP, ngayDangBan);
                    System.out.println("Sửa sản phẩm: " + maSP );
                }
                // Page name
                request.setAttribute("pageName", "Product");
                response.sendRedirect(request.getContextPath() + "/manage-product");
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        else {
            response.sendRedirect(request.getContextPath() + "/signIn");
        }
    }
}
