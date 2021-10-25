package servlet;

import beans.*;
import conn.ConnectionUtils;
import utils.DBUtils;

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

        Connection conn;
        List<Users> listUser;
        List<Role> listRole;

        try {
            conn = ConnectionUtils.getConnection();
            listUser = DBUtils.getAllUser(conn);
            listRole = DBUtils.getAllRold(conn);
            request.setAttribute("listUser", listUser);
            request.setAttribute("listRole", listRole);
            request.getRequestDispatcher("/WEB-INF/views/user_management.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Connection conn;

            String hoten = request.getParameter("newUserName");
            String sdt = request.getParameter("numberPhone");
            Date ngaySinh =  Date.valueOf(request.getParameter("dateOfBirth"));
            String diaChi = request.getParameter("address");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            int roleID = Integer.parseInt(request.getParameter("roleID"));

            conn = ConnectionUtils.getConnection();
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
