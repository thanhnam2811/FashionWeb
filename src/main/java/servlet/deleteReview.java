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

@WebServlet(urlPatterns = "/deleteReview", name = "deleteReview")
public class deleteReview extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String maSP = request.getParameter("maSP");
            try {
                Connection conn = MyUtils.getStoredConnection(request);
                HttpSession session= request.getSession();
                Users a = (Users) session.getAttribute("loginedUser");

                if (a!=null)
                {
                    String maCMT = request.getParameter("maCMT");
                    DBUtils.Deletereview(conn,maCMT);
                    new detail().doPost(request, response);
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
    }
}
