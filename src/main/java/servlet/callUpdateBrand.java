package servlet;

import beans.ThuongHieu;
import conn.ConnectionUtils;
import utils.AdminManagerBrand;
import utils.DBUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "callUpdateBrand", value = "/callUpdateBrand")
public class callUpdateBrand extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn;
        String id = (String) request.getParameter("id");
        ThuongHieu th = null;
        try{
            conn = conn = ConnectionUtils.getConnection();
            th = AdminManagerBrand.load_TH_byMaTH(conn, id);
            request.setAttribute("brand",th);
            request.getRequestDispatcher("/WEB-INF/views/updateTH.jsp").forward(request, response);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
