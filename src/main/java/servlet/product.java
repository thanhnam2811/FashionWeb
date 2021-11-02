package servlet;

import beans.LoaiSP;
import beans.SanPham;
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

@WebServlet(name = "product", value = "/product")
public class product extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        List<SanPham> listSP;
        List<LoaiSP> listLoaiSP;

        try {
            Connection conn = MyUtils.getStoredConnection(request);
            listSP = DBUtils.getAllSanPham(conn);
            listLoaiSP = DBUtils.getAllLoaiSP(conn);
            request.setAttribute("listSP", listSP);
            request.setAttribute("listLoaiSP", listLoaiSP);

            new cart().doPost(request, response);
            request.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(request, response);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
