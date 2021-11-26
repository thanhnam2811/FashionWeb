package utils;

import beans.*;
import conn.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminHome {

    //Lấy số lượng sản phẩm >0
    public static int getSoSanPhamCon(Connection conn) throws SQLException {
        String sql = "Select Count(maSP)as soLuong\n" +
                "From SanPham\n" +
                "Where soLuongSP >0";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        int soLuong = 0;
        while(rs.next())
        {
            soLuong = rs.getInt("soLuong");
        }
        return soLuong;
    }
    //Lấy số lượng thương hiệu
    public static int getSoLuongThuongHieu(Connection conn) throws SQLException {
        String sql = "Select Count(maTH) as soLuongTH\n" +
                "From ThuongHieu";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        int soLuong = 0;
        while(rs.next())
        {
            soLuong = rs.getInt("soLuongTH");
        }
        return soLuong;
    }
    //Số lượng user
    public static int getSoLuongUser(Connection conn) throws SQLException {
        String sql = "Select Count(maKH) as soLuongUser\n" +
                "From Users";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        int soLuong = 0;
        while(rs.next())
        {
            soLuong = rs.getInt("soLuongUser");
        }
        return soLuong;
    }
    public static float getDonHangMaxCost(Connection conn) throws SQLException {
        String sql = "Select Max(tongTien) as donHangMaxCost\n" +
                "From DonHang";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        float soLuong = 0;
        while(rs.next())
        {
            soLuong = rs.getFloat("donHangMaxCost");
        }
        return soLuong;
    }
    public static List<Users> getListNhanVien(Connection conn) throws SQLException {
        String sql = "Select hoTen, userName\n" +
                "From Users\n" +
                "Where roleID = 2\n";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Users> list = new ArrayList<Users>();

        while (rs.next()) {
            String hoten = rs.getString("hoTen");
            String username = rs.getString("userName");
            Users user = new Users(hoten,username);
            list.add(user);
        }
        return list;
    }
    //Doanh thu theo tháng
    public static List<DonHang> getDoanhThuTheoThang(Connection conn) throws SQLException {
        String sql = "Select MONTH(ngayMua) as thang , SUM(tongTien) as doanhThu\n" +
                "From DonHang\n" +
                "Group by MONTH(ngayMua)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<DonHang> list = new ArrayList<DonHang>();

        while (rs.next()) {
            int thang = rs.getInt("thang");
            float doanhThu = rs.getFloat("doanhThu");
            DonHang dh = new DonHang(thang,doanhThu);
            list.add(dh);
        }
        return list;
    }

    //Số lượng SP theo loại SP
    public static List<SanPham> getSoLuongSPByLoaiSP(Connection conn) throws SQLException {
        String sql = "Select maLoaiSP , COUNT(maSP) as soLuong  \n" +
                "From SanPham\n" +
                "Group by maLoaiSP";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<SanPham> list = new ArrayList<SanPham>();

        while (rs.next()) {
            int maLoaiSP = rs.getInt("maLoaiSP");
            int soLuong = rs.getInt("soLuong");
            SanPham sp = new SanPham(maLoaiSP,soLuong);
            list.add(sp);
        }
        return list;
    }
    //Tong SP
    public static int getTongSoLuongSP(Connection conn) throws SQLException {
        String sql = "Select Count(maSP) as tong From SanPham";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        int tong = 0;

        while (rs.next()) {
            tong = rs.getInt("tong");
        }
        return tong;
    }

    //lấy các bình luận đăng trong ngày hôm nay
    public static List<BinhLuan> getBinhLuanHomNay(Connection conn) throws SQLException {
        String sql = "Select maKH,maSP,noiDung\n" +
                "From BinhLuan\n" +
                "Where  CAST(ngayDang as date) = CAST(GETDATE() as date)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<BinhLuan> list = new ArrayList<BinhLuan>();

        while (rs.next()) {
            int maSP = rs.getInt("maSP");
            int maKH = rs.getInt("maKH");
            String noiDung = rs.getString("noiDung");
            BinhLuan cmt = new BinhLuan(maKH,noiDung,maSP);
            list.add(cmt);
        }
        return list;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionUtils.getConnection("sa", "123");

        System.out.println(getSoSanPhamCon(conn));
//        List<ChiTietDonHang> list = DBUtils.getChiTietDonHang_bymaKH(conn, 1);
//        for (ChiTietDonHang l : list) {
//            System.out.println(l);
//        }

    }
}
