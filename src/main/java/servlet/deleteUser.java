package servlet;

import beans.Users;
import conn.ConnectionUtils;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "deleteUser", value = "/deleteUser")
public class deleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        int maKH = Integer.parseInt(request.getParameter("maKH"));
//        try {
////            Connection conn = MyUtils.getStoredConnection(request);
//            Connection conn = ConnectionUtils.getConnection("sa", "123");
//            DBUtils.deleteUser(conn, maKH);
//
//            // Quay về trang product management
//            new UserManagement().doGet(request, response);
//        } catch (SQLException | ClassNotFoundException throwables) {
//            throwables.printStackTrace();
//        }

        HttpSession session = request.getSession();
        Users u = MyUtils.getLoginedUser(session);
        if (u != null && u.getRoleID() == 3) {
            try {
                String username = request.getParameter("username");
                // Tạo đối tượng Connection kết nối database.
                Connection conn = ConnectionUtils.getConnection("sa", "123");
                DBUtils.deleteAdmin(conn, username);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                response.sendRedirect(request.getContextPath()+"/manage-staff-admin");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/signIn");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
