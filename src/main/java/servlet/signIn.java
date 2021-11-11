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

/**
 * Servlet implementation class signIn
 */
@WebServlet(urlPatterns = "/signIn", name = "signIn")
public class signIn extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public signIn() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());

        request.getRequestDispatcher("/WEB-INF/views/signIn.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errorString = null;
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);
        Users u = new Users();
        try {
            Connection conn = MyUtils.getStoredConnection(request);
            u = DBUtils.findUser(conn, username, password);

            if (u != null) {
                HttpSession session = request.getSession();
                MyUtils.storeLoginedUser(session, u);

                // Nếu người dùng chọn tính năng "Remember me".
                if (remember) {
                    MyUtils.storeUserCookie(response, u);
                }
                // Ngược lại xóa Cookie
                else {
                    MyUtils.deleteUserCookie(response);
                }

                Users loginedUser = MyUtils.getLoginedUser(session);
                // Tạo đối tượng Connection kết nối database.
                conn = ConnectionUtils.getConnection(loginedUser.getUserName(), loginedUser.getPassword());
				// Lưu connection lại
				MyUtils.storeConnection(request, conn);

                //Kiểm tra quyền truy cập
                if ((u.getRoleID()) == 0) {
                    request.getRequestDispatcher("home").forward(request, response);
                } else {
//					//request.getRequestDispatcher("productManagement").forward(request, response);
//					String contextPath = request.getContextPath();
//					response.sendRedirect(contextPath + "/productManagement");

                    request.getRequestDispatcher("home").forward(request, response);

                }
            } else {
                errorString = "Tên đăng nhập hoặc mật khẩu không chính xác";
                request.setAttribute("errorString", errorString);
                request.getRequestDispatcher("/WEB-INF/views/signIn.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}