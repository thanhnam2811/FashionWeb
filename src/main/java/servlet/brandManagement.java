package servlet;

import beans.ThuongHieu;
import beans.Users;
import conn.ConnectionUtils;
import utils.DBUtils;
import utils.MyUtils;

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

        List<ThuongHieu> listTH =null;

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
                    listTH = DBUtils.getAllThuongHieu(conn);
                    request.setAttribute("listTH", listTH);
                    // Page name
                    request.setAttribute("pageName", "Brand");
                    request.getRequestDispatcher("/WEB-INF/admin/table-brand.jsp").forward(request, response);
                } catch (SQLException e1) {
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
        doGet(request, response);
    }
}
