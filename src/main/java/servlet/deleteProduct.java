package servlet;

import conn.ConnectionUtils;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "deleteProduct", value = "/deleteProduct")
public class deleteProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String maSP = request.getParameter("maSP");
        try {
            Connection conn = MyUtils.getStoredConnection(request);
            DBUtils.deleteSanPham(conn, maSP);

            // Quay về
            response.sendRedirect(request.getContextPath() + "/manage-product");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
