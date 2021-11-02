package servlet;

import beans.Role;
import beans.SanPham;
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
import java.util.List;

@WebServlet(name = "editUser", value = "/editUser")
public class editUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int maKH = Integer.parseInt(request.getParameter("maKH"));
        Users user;

        List<Users> listUser;
        List<Role> listRole;

        try {
            Connection conn = MyUtils.getStoredConnection(request);
            listUser = DBUtils.getAllUser(conn);
            listRole = DBUtils.getAllRold(conn);
            user = DBUtils.getInfoUser(conn,maKH);
            request.setAttribute("listUser", listUser);
            request.setAttribute("listRole", listRole);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/editUser.jsp").forward(request, response);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection conn;

            int maKH = Integer.parseInt(request.getParameter("maKH"));
            String hoten = request.getParameter("editName");
            String sdt = request.getParameter("editNumberPhone");
            Date ngaySinh =  Date.valueOf(request.getParameter("editDateOfBirth"));
            String diaChi = request.getParameter("editAddress");
            String username = request.getParameter("editUsername");
            int roleID = Integer.parseInt(request.getParameter("editRoleID"));


            conn = ConnectionUtils.getConnection();
            DBUtils.EditUser(conn,maKH,hoten,sdt,ngaySinh,diaChi,username,roleID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("maKH", request.getParameter("maKH"));
            request.setAttribute("errorString", e.getMessage());
            doGet(request, response);
        }
        new UserManagement().doGet(request, response);
    }
}
