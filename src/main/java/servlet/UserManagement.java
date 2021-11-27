package servlet;

import beans.*;
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
import java.util.List;

@WebServlet(name = "UserManagement", value = "/UserManagement")
public class UserManagement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Users> listUser;
        List<Role> listRole;
        HttpSession session = request.getSession();
        Users u = MyUtils.getLoginedUser(session);
        if(u == null) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/signIn");
        }
        else {
            if (u.getRoleID() == 3) {
                try {
                    Connection conn = MyUtils.getStoredConnection(request);
                    listUser = DBUtils.getAllUser(conn);
                    listRole = DBUtils.getAllRold(conn);
                    request.setAttribute("listUser", listUser);
                    request.setAttribute("listRole", listRole);
                    request.getRequestDispatcher("/WEB-INF/views/user_management.jsp").forward(request, response);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            else
            {
                request.getRequestDispatcher("/WEB-INF/views/errorAccess.jsp").forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String hoten = request.getParameter("newUserName");
            String sdt = request.getParameter("numberPhone");
            Date ngaySinh =  Date.valueOf(request.getParameter("dateOfBirth"));
            String diaChi = request.getParameter("address");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            int roleID = Integer.parseInt(request.getParameter("roleID"));

            // Tạo đối tượng Connection kết nối database.
            Connection conn = ConnectionUtils.getConnection("sa", "123");

            DBUtils.insertAdmin(conn,hoten,sdt,ngaySinh,diaChi,username,password,roleID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            doGet(request, response);
        }
    }
}
