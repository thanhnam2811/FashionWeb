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
import java.sql.SQLException;

@WebServlet(urlPatterns = "/addReview", name = "addReview")
public class addReview extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String maSP  = request.getParameter("maSP");
        try {
            Connection conn = MyUtils.getStoredConnection(request);
            HttpSession session= request.getSession();
            Users a = (Users) session.getAttribute("loginedUser");

            if (a!=null)
            {
                int maKH=a.getMaKH();

                String noiDung = request.getParameter("textReview");
                DBUtils.Addreview(conn, maKH,maSP,noiDung);
                new detail().doGet(request,response);

            }
            else {
                request.getRequestDispatcher("signIn").forward(request,response);
            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
