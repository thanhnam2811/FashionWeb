package servlet;

import beans.ThuongHieu;
import conn.ConnectionUtils;
import utils.AdminManagerBrand;
import utils.DBUtils;

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
        Connection conn;
        List<ThuongHieu> listTH;
        try {
            conn = ConnectionUtils.getConnection();
            listTH = DBUtils.getAllThuongHieu(conn);
            request.setAttribute("listTH", listTH);
            request.getRequestDispatcher("/WEB-INF/views/brand_management.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
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
                int maTH = Integer.valueOf(request.getParameter("maTH"));
                String tenTH = request.getParameter("tenTH");
                String emailTH = request.getParameter("emailTH");
                String hinhTH = request.getParameter("hinhTH");
                th =new ThuongHieu(maTH, tenTH, emailTH, hinhTH);
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
            doGet(request, response);
        }
    }
}
