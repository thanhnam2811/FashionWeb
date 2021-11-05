package servlet;

import beans.ThuongHieu;
import conn.ConnectionUtils;
import utils.AdminManagerBrand;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "brandControll", value = "/brandControll")
public class brandControll extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection conn;
            String action = request.getParameter("action");
            ThuongHieu th = null;
            int delmaTH;
            if(action.equals("insert"))
            {
                //int maTH = Integer.valueOf(request.getParameter("maTH"));
                String tenTH = request.getParameter("tenTH");
                String emailTH = request.getParameter("emailTH");
                String hinhTH = request.getParameter("hinhTH");
                th = new ThuongHieu(tenTH, emailTH, hinhTH);
            }
            if(action.equals("delete") )
            {
                delmaTH =  Integer.valueOf(request.getParameter("delmaTH"));
            }
            else
                delmaTH =0;
            if(action.equals("update"))
            {
                delmaTH = Integer.valueOf(request.getParameter("maTH"));
                String tenTH = request.getParameter("tenTH");
                String emailTH = request.getParameter("emailTH");
                String hinhTH = request.getParameter("hinhTH");
                th =new ThuongHieu(delmaTH, tenTH, emailTH, hinhTH);
            }

            conn = ConnectionUtils.getConnection();
            AdminManagerBrand.ControllBrand(conn, th, action, delmaTH);
        }
        catch (ClassNotFoundException c)
        {
            c.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/brandManagement");
        }
    }
}
