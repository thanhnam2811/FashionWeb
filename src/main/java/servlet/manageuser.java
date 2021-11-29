package servlet;

import beans.BinhLuan;
import beans.Users;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "manage-user", value = "/manage-user")
public class manageuser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users u = MyUtils.getLoginedUser(session);
        List<Users> listU = null;
        if (u != null && u.getRoleID() == 3) {
            try {
                Connection conn = MyUtils.getStoredConnection(request);
                ;

                listU = DBUtils.LoadlistUser(conn);


                request.setAttribute("listU", listU);
                request.setAttribute("pageName", "User");
                request.getRequestDispatcher("/WEB-INF/admin/table-user.jsp").forward(request, response);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        else {
            response.sendRedirect(request.getContextPath() + "/signIn");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
