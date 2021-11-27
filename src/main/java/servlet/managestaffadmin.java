package servlet;

import beans.Role;
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
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "manage-staff-admin", value = "/manage-staff-admin")
public class managestaffadmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Users> listUser;
        List<Role> listRole;
        HttpSession session = request.getSession();
        Users u = MyUtils.getLoginedUser(session);
        if (u != null && u.getRoleID() == 3) {
            try {
                Connection conn = MyUtils.getStoredConnection(request);
                listUser = DBUtils.getAllUser(conn);
                listRole = DBUtils.getAllRold(conn);
                request.setAttribute("listUser", listUser);
                request.setAttribute("listRole", listRole);
                // Page name
                request.setAttribute("pageName", "Account/Staff & Admin");
                request.getRequestDispatcher("/WEB-INF/admin/table-staff-admin.jsp").forward(request, response);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/signIn");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users u = MyUtils.getLoginedUser(session);
        if (u != null && u.getRoleID() == 3) {
            try {
                String hoten = request.getParameter("_hoTen");
                String sdt = request.getParameter("_sdt");
                Date ngaySinh = Date.valueOf(request.getParameter("_ngaySinh"));
                String diaChi = request.getParameter("_diaChi");
                String username = request.getParameter("_username");
                String password = request.getParameter("_password");
                int roleID = Integer.parseInt(request.getParameter("_roleID"));

                // Tạo đối tượng Connection kết nối database.
                Connection conn = ConnectionUtils.getConnection("sa", "123");
                if (DBUtils.findUser(conn, username, password) == null)
                    DBUtils.insertAdmin(conn, hoten, sdt, ngaySinh, diaChi, username, password, roleID);
                else
                    DBUtils.updateAdmin(conn, username, hoten, sdt, ngaySinh, diaChi, roleID);
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
}
