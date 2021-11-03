package utils;

import beans.LoaiSP;
import beans.SanPham;
import conn.ConnectionUtils;

import javax.ws.rs.HttpMethod;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SortSanPham {
    public static String ASC ="ASC";
    public static String DESC = "DESC";

    public static List<SanPham> sortSP(Connection conn, String sortType, String minValue, String maxValue)
            throws SQLException {
        List<SanPham> list = new ArrayList<SanPham>();
        CallableStatement cstm = null;

        if(sortType.equals("Newness"))
        {
            cstm = conn.prepareCall("{call sortSP_byngayDangBan(?,?)}");
            cstm.setString(1, minValue);
            cstm.setString(2, maxValue);
        }
        else{
            cstm = conn.prepareCall("{call sortSP_bygiaSP(?,?,?)}");
            cstm.setString(1, sortType);
            cstm.setString(2, minValue);
            cstm.setString(3, maxValue);
        }

        ResultSet rs = cstm.executeQuery();

        while (rs.next()) {
            int maSP = rs.getInt("maSP");
            String tenSP = rs.getString("tenSP");
            int maTH = rs.getInt("maTH");
            int maLoaiSP = rs.getInt("maLoaiSP");
            String hinhSP = rs.getString("hinhSP");
            float giaSP = rs.getFloat("giaSP");
            int soLuongSP = rs.getInt("soLuongSP");
            Date ngayDangBan = rs.getDate("ngayDangBan");
            SanPham sp = new SanPham(maSP, tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan);
            list.add(sp);
        }
        return list;
    }

    public static String getMaxPrice(Connection conn) throws SQLException {
        CallableStatement cstm = null;
        cstm = conn.prepareCall("{call getMaxPrice()}");
        ResultSet rs = cstm.executeQuery();

        String max= null;
        while (rs.next()) {
            max = rs.getString(1);
        }
        return max;
    }

    public static List<SanPham> SearchSP(Connection conn, String search) throws SQLException {
        List<SanPham> list = new ArrayList<SanPham>();
        String searchtxt = "%" +search + "%";
        CallableStatement cstm = null;
        cstm = conn.prepareCall("{call searchSP(?)}");
        cstm.setString(1,searchtxt);
        ResultSet rs = cstm.executeQuery();
        while (rs.next()) {
            int maSP = rs.getInt("maSP");
            String tenSP = rs.getString("tenSP");
            int maTH = rs.getInt("maTH");
            int maLoaiSP = rs.getInt("maLoaiSP");
            String hinhSP = rs.getString("hinhSP");
            float giaSP = rs.getFloat("giaSP");
            int soLuongSP = rs.getInt("soLuongSP");
            Date ngayDangBan = rs.getDate("ngayDangBan");
            SanPham sp = new SanPham(maSP, tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan);
            list.add(sp);
        }
        return list;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionUtils.getConnection();
        System.out.println(getMaxPrice(conn));
    }
}
