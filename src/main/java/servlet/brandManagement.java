package servlet;

import beans.ThuongHieu;
import conn.ConnectionUtils;
import utils.DBUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "brandManagement", value = "/brandManagement")
public class brandManagement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn;
        List<ThuongHieu> listTH =null;
        try{
            conn = ConnectionUtils.getConnection();
            listTH = DBUtils.getAllThuongHieu(conn);
            request.setAttribute("listTH", listTH);
            request.getRequestDispatcher("/WEB-INF/views/brand_management.jsp").forward(request, response);
        }
        catch( SQLException e1){
            e1.printStackTrace();
        }
        catch(ClassNotFoundException c){
            c.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
