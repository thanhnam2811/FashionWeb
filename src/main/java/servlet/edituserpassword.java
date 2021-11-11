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

@WebServlet(urlPatterns = "/edituserpassword", name = "edituserpassword")
public class edituserpassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public edituserpassword() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean hasError = false;
        String messageError = "Unkown Error!";

        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        Users user = MyUtils.getLoginedUser(session);
        if (user == null) {
            response.sendRedirect("signIn");
            return;
        }
        try {
            String oldpassword = request.getParameter("oldpassword");
            String newpassword = request.getParameter("newpassword");

            hasError = !DBUtils.EditUserInfo_password(conn, user.getUserName(), newpassword, oldpassword);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            if (hasError) {
                String mess = "Mật khẩu cũ không đúng";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("/WEB-INF/views/userinfo.jsp").forward(request, response);
            } else {
                MyUtils.storeLoginedUser(session, null);
                MyUtils.deleteUserCookie(response);
                response.sendRedirect(request.getContextPath() + "/signIn");
            }
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
