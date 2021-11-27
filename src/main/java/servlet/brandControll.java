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
            conn = ConnectionUtils.getConnection();
            String action = null;
            ThuongHieu th = null;
            String _maTH = request.getParameter("_maTH") ;
            if(  _maTH == null || _maTH == "" || AdminManagerBrand.load_TH_byMaTH(conn, _maTH) == null )
            {
                //int maTH = Integer.valueOf(request.getParameter("maTH"));
                String tenTH = request.getParameter("_tenTH");
                String emailTH = request.getParameter("_emailTH");
                String hinhTH = request.getParameter("_logoTH");
                th = new ThuongHieu(tenTH, emailTH, hinhTH);
                action = "insert";
            }
            else
            {
                int maTH = Integer.valueOf(request.getParameter("_maTH"));
                String tenTH = request.getParameter("_tenTH");
                String emailTH = request.getParameter("_emailTH");
                String hinhTH = request.getParameter("_logoTH");
                th =new ThuongHieu(maTH, tenTH, emailTH, hinhTH);
                action = "update";
            }
            AdminManagerBrand.ControllBrand(conn, th, action);
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
