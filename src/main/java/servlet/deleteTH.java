package servlet;

import utils.AdminManagerBrand;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "deleteTH", value = "/deleteTH")
public class deleteTH extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maTH = request.getParameter("maTH");
        try {
            Connection conn = MyUtils.getStoredConnection(request);
            AdminManagerBrand.deleteTH(conn,maTH);

            // Quay về
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/brandManagement");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
