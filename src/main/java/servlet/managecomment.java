package servlet;

import beans.*;
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

@WebServlet(name = "manage-comment", value = "/manage-comment")
public class managecomment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BinhLuan> listBL = null;
        HttpSession session = request.getSession();
        Users u = MyUtils.getLoginedUser(session);
        if(u != null && (u.getRoleID() == 2 || u.getRoleID() == 3)) {
            try {
                Connection conn = MyUtils.getStoredConnection(request);

                listBL = DBUtils.LoadlistBinhLuan(conn);

                // Xếp bình luận theo ngày đăng
                Collections.sort(listBL);

                request.setAttribute("listBL", listBL);
                request.setAttribute("pageName", "Comment");
                request.getRequestDispatcher("/WEB-INF/admin/table-comment.jsp").forward(request, response);
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
