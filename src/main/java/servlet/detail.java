package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BinhLuan;
import beans.LoaiSP;
import beans.SanPham;
import beans.ThuongHieu;
import conn.ConnectionUtils;
import utils.DBUtils;
import utils.MyUtils;

/**
 * Servlet implementation class productdetail
 */
@WebServlet(urlPatterns = "/detail", name = "detail")
public class detail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public detail() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        SanPham SP;
        List<SanPham> listSP = null;
        List<BinhLuan> listBL = null;
        LoaiSP loaiSP = null;
        String maSP = request.getParameter("maSP");
        ThuongHieu TH = null;
        try {
            Connection conn = MyUtils.getStoredConnection(request);;
            SP = DBUtils.getttSanPham(conn, maSP);
            loaiSP = DBUtils.getLoaiSP_bymaSP(conn, maSP);
            String maLoaiSP = String.valueOf(SP.getMaLoaiSP());
            listSP = DBUtils.getSanPhambymaLoaiSP(conn, maLoaiSP);
            listBL = DBUtils.getBinhLuan_bymaSP(conn, maSP);
            TH = DBUtils.getThuongHieu_bymaSP(conn, maSP);

            // Xếp bình luận theo ngày đăng
            Collections.sort(listBL);

            request.setAttribute("listBL", listBL);
            request.setAttribute("SP", SP);
            request.setAttribute("loaiSP", loaiSP);
            request.setAttribute("ListSPbymaLoaiSP", listSP);
            request.setAttribute("TH", TH);

            new cart().doPost(request, response);
            request.getRequestDispatcher("/WEB-INF/views/detail.jsp").forward(request, response);
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
