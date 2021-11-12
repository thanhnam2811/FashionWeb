package servlet;

import beans.ChiTietGioHang;
import beans.LoaiSP;
import beans.SanPham;
import beans.Users;
import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import conn.ConnectionUtils;
import conn.SQLServerConnUtils;
import utils.DBUtils;
import utils.MyUtils;
import utils.SortSanPham;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchControll", value = "/SearchControll")
public class SearchControll extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn;
        List<SanPham> listSP = null;
        List<LoaiSP> listLoaiSP;
        List<ChiTietGioHang> listChiTietGioHang = new ArrayList<ChiTietGioHang>();
        int numP_display = 12;
        int page = 1;
        int brand = 0;
        String search;
        try{

            conn = ConnectionUtils.getConnection();
            if(request.getParameter("key") == null)
                search = request.getParameter("searchInput");
            else
                search = request.getParameter("key");

            listSP = SortSanPham.SearchSP(conn, search);

            if(request.getParameter("brand") == null)
                brand = 0;
            else
                brand =Integer.valueOf(request.getParameter("brand"));

            if(request.getParameter("page") == null)
                page =1;
            else{
                page = Integer.valueOf(request.getParameter("page"));
            }

            listLoaiSP = DBUtils.getAllLoaiSP(conn);

            if (brand != 0) {
                int filterIdBrand = brand;
                try {
                    listSP.removeIf(p -> p.getMaLoaiSP() != filterIdBrand);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            int totalpage = listSP.size()%numP_display == 0 ? listSP.size()/numP_display : listSP.size()/numP_display+1;
            if(page < 0 || page > totalpage)
                page = 1;
            HttpSession session = request.getSession();
            Users u = MyUtils.getLoginedUser(session);
            if(u != null){
                int id = u.getMaKH();
                listChiTietGioHang = DBUtils.getChiTietGioHangByMaKH(conn, id);
                request.setAttribute("listChiTietGioHang", listChiTietGioHang);
            }

            request.setAttribute("listSP", listSP);
            request.setAttribute("listLoaiSP", listLoaiSP);
            request.setAttribute("page", page);
            request.setAttribute("brand", brand);
            request.setAttribute("numP_display",numP_display);
            request.setAttribute("totalpage", totalpage);
            request.setAttribute("key", search);
            request.getRequestDispatcher("/WEB-INF/views/viewTimKiem.jsp").forward(request, response);

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
