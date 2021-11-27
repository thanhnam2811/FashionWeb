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

@WebServlet(urlPatterns = "/AdminDeleteBL", name = "AdminDeleteBL")
public class AdminDeleteBL extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maCMT = request.getParameter("maCMT");
        try {
            Connection conn = MyUtils.getStoredConnection(request);
            HttpSession session= request.getSession();
            Users a = (Users) session.getAttribute("loginedUser");

                DBUtils.AdminDeletereview(conn,maCMT);
                request.setAttribute("pageName", "Comment");
                new managecomment().doGet(request,response);

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
