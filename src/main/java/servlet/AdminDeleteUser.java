package servlet;

import beans.Users;
import conn.ConnectionUtils;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/AdminDeleteUser", name = "AdminDeleteUser")
public class AdminDeleteUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("userName");
        HttpSession session = request.getSession();
        Users u = MyUtils.getLoginedUser(session);
        if (u != null && u.getRoleID() == 3) {
            try {

                // Tạo đối tượng Connection kết nối database.
                Connection conn = ConnectionUtils.getConnection("sa", "123");
                DBUtils.AdminDeleteUser(conn, username);
                new manageuser().doGet(request,response);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/signIn");
        }
    }
}
