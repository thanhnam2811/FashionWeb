package servlet;

import beans.Users;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "manage-brand", value = "/manage-brand")
public class managebrand extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users u = MyUtils.getLoginedUser(session);
        if(u != null && u.getRoleID() == 3){
            // Page name
            request.setAttribute("pageName", "Brand");
            request.getRequestDispatcher("/WEB-INF/admin/table-brand.jsp").forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/signIn");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
