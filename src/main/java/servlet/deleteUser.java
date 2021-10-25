package servlet;

import conn.ConnectionUtils;
import utils.DBUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "deleteUser", value = "/deleteUser")
public class deleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        int maKH = Integer.parseInt(request.getParameter("maKH"));
        try {
            conn = ConnectionUtils.getConnection();
            DBUtils.deleteUser(conn, maKH);

            // Quay về trang product management
            new UserManagement().doGet(request, response);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
