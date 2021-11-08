package servlet;

import beans.ChiTietGioHang;
import beans.LoaiSP;
import beans.SanPham;
import beans.Users;
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
import java.util.*;

@WebServlet(name = "SapXep", value = "/SapXep")
public class SapXep extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn;
        List<SanPham> listSP = null;
        List<LoaiSP> listLoaiSP;
        List<ChiTietGioHang> listChiTietGioHang = new  ArrayList<ChiTietGioHang>();
        int numP_display = 12;
        int page = 1;

        String sort = "ASC";
        String minvalue = null;
        String maxvalue = null;
        String maxPrice =null;
        try{
            conn = ConnectionUtils.getConnection();
            sort = request.getParameter("sort") != null ? request.getParameter("sort"): "ASC";
            minvalue = request.getParameter("minvalue") != null ? request.getParameter("minvalue") : null;
            maxvalue = request.getParameter("maxvalue") != null ? request.getParameter("maxvalue") : null;

            if(request.getParameter("page") == null)
                page =1;
            else{
                page = Integer.valueOf(request.getParameter("page"));
            }

            listLoaiSP = DBUtils.getAllLoaiSP(conn);
            listSP = SortSanPham.sortSP(conn, sort, minvalue, maxvalue);


            int totalpage = listSP.size()%numP_display == 0 ? listSP.size()/numP_display : listSP.size()/numP_display+1;
            if(page < 0 || page > totalpage)
                page = 1;
            request.setAttribute("listSP", listSP);
            request.setAttribute("listLoaiSP", listLoaiSP);
            request.setAttribute("sort", sort);
            request.setAttribute("minvalue", minvalue);
            request.setAttribute("maxvalue", maxvalue);
            request.setAttribute("page", page);
            request.setAttribute("numP_display",numP_display);
            request.setAttribute("totalpage", totalpage);

            HttpSession session = request.getSession();
            Users u = MyUtils.getLoginedUser(session);
            if(u != null){
                int id = u.getMaKH();
                listChiTietGioHang = DBUtils.getChiTietGioHangByMaKH(conn, id);
                request.setAttribute("listChiTietGioHang", listChiTietGioHang);
            }

            request.getRequestDispatcher("/WEB-INF/views/viewsort.jsp").forward(request, response);

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
