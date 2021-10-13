package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.*;
import conn.ConnectionUtils;

public class DBUtils {
    // Load all sanpham
    public static List<SanPham> getAllSanPham(Connection conn) throws SQLException {
		/*
		String sql = "Select * from SanPham";
		PreparedStatement pstm = conn.prepareStatement(sql);
		*/
        CallableStatement cstm = conn.prepareCall("{call load_SanPham()}");
        ResultSet rs = cstm.executeQuery();
        List<SanPham> list = new ArrayList<SanPham>();
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

    // Load all LoaiSP
    public static List<LoaiSP> getAllLoaiSP(Connection conn) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call load_LoaiSP()}");
        ResultSet rs = cstm.executeQuery();
        List<LoaiSP> list = new ArrayList<LoaiSP>();
        while (rs.next()) {
            int maLoaiSP = rs.getInt("maLoaiSP");
            String tenLoaiSP = rs.getString("tenLoaiSP");
            LoaiSP lsp = new LoaiSP(maLoaiSP, tenLoaiSP);
            list.add(lsp);
        }
        return list;
    }

    public static SanPham getttSanPham(Connection conn, String idSP) throws SQLException {
//		String sql = "select * from sanpham"
//				+ " where maSP=?";
//		// -- MySQL
//		PreparedStatement pstm = conn.prepareStatement(sql);
//		pstm.setString(1, idSP);
//		ResultSet rs = pstm.executeQuery();
//		SanPham sanPham = new SanPham();
//
        // -- SQLSERVER
        CallableStatement cstm = conn.prepareCall("{call load_SanPham_by_maSP(?)}");
        cstm.setString(1, idSP);
        cstm.execute();
        ResultSet rs = cstm.getResultSet();
        SanPham sanPham = new SanPham();
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
        }
        return sanPham;
    }

    public static List<SanPham> getSanPhambymaLoaiSP(Connection conn, String idmaLoaiSP) throws SQLException {
//		String sql = "select * from sanpham"
//				+ " where maLoaiSP=?";
//		PreparedStatement pstm = conn.prepareStatement(sql);
//		pstm.setString(1, idmaLoaiSP);
//		ResultSet rs = pstm.executeQuery();
//		List<SanPham> list = new ArrayList<SanPham>();

        CallableStatement cstm = conn.prepareCall("{call load_ListSanPhambymaLoaiSP(?)}");
        cstm.setString(1, idmaLoaiSP);
        cstm.execute();
        ResultSet rs = cstm.getResultSet();
        List<SanPham> list = new ArrayList<SanPham>();
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

    public static LoaiSP getLoaiSP_bymaSP(Connection conn, String idmaSP) throws SQLException {
//		String sql = "select SanPham.maLoaiSP,tenLoaiSP "
//				+ " from SanPham  join LoaiSP on SanPham.maLoaiSP=LoaiSP.maLoaiSP"
//				+" where  SanPham.maSP=?";
//		PreparedStatement pstm = conn.prepareStatement(sql);
//		pstm.setString(1, idmaSP);
//		ResultSet rs = pstm.executeQuery();
//		LoaiSP loaisanPham = new LoaiSP();

        CallableStatement cstm = conn.prepareCall("{call load_LoaiSanPhambymaSP(?)}");
        cstm.setString(1, idmaSP);
        cstm.execute();
        ResultSet rs = cstm.getResultSet();
        LoaiSP loaisanPham = null;

        while (rs.next()) {
            int maLoaiSP = rs.getInt("maLoaiSP");
            String tenLoaiSP = rs.getString("tenLoaiSP");

            loaisanPham = new LoaiSP(maLoaiSP, tenLoaiSP);
        }
        return loaisanPham;
    }

    public static List<BinhLuan> getBinhLuan_bymaSP(Connection conn, String idmaSP) throws SQLException {
//		String sql = "select maCMT,users.hoTen,maSP,noiDung,ngayDang"
//				+ " from users,binhluan "
//				+ " where users.maKH = binhluan.maKH and binhluan.maSP=?";
//		PreparedStatement pstm = conn.prepareStatement(sql);
//		pstm.setString(1, idmaSP);
//		ResultSet rs = pstm.executeQuery();
//		List<BinhLuan> list = new ArrayList<BinhLuan>();

        CallableStatement cstm = conn.prepareCall("{call load_ListBinhLuanPhambymaSP(?)}");
        cstm.setString(1, idmaSP);
        cstm.execute();
        ResultSet rs = cstm.getResultSet();
        List<BinhLuan> list = new ArrayList<BinhLuan>();
        while (rs.next()) {
            int maCMT = rs.getInt("maCMT");
            String hoTen = rs.getString("hoTen");
            int maSP = rs.getInt("maSP");
            String noiDung = rs.getString("noiDung");
            Date ngayDang = rs.getDate("ngayDang");
            BinhLuan bl = new BinhLuan(maCMT, hoTen, maSP, noiDung, ngayDang);
            list.add(bl);
        }
        return list;
    }

    public static ThuongHieu getThuongHieu_bymaSP(Connection conn, String idmaSP) throws SQLException {
//		String sql = "select SanPham.maLoaiSP,tenLoaiSP "
//				+ " from SanPham  join LoaiSP on SanPham.maLoaiSP=LoaiSP.maLoaiSP"
//				+" where  SanPham.maSP=?";
//		PreparedStatement pstm = conn.prepareStatement(sql);
//		pstm.setString(1, idmaSP);
//		ResultSet rs = pstm.executeQuery();
//		LoaiSP loaisanPham = new LoaiSP();

        CallableStatement cstm = conn.prepareCall("{call load_ThuongHieuPhambymaSP(?)}");
        cstm.setString(1, idmaSP);
        cstm.execute();
        ResultSet rs = cstm.getResultSet();
        ThuongHieu thuongHieu = new ThuongHieu();

        while (rs.next()) {
            int maTH = rs.getInt("maTH");
            String tenTH = rs.getString("tenTH");
            String emailTH = rs.getString("emailTH");
            String hinhTH = rs.getString("hinhTH");

            thuongHieu = new ThuongHieu(maTH, tenTH, emailTH, hinhTH);
        }
        return thuongHieu;
    }

    public static List<SanPhamInCart> getSanPhamInCart(Connection conn, String maKH) throws SQLException {
        String sql = "Select ChiTietGioHang.ID, ChiTietGioHang.maSP,ChiTietGioHang.soLuongSP, tenSP,giaSP,hinhSP,ChiTietGioHang.thanhTien\n"
                + "From ChiTietGioHang, SanPham, Users\n"
                + "Where ChiTietGioHang.maKH = Users.maKH  and ChiTietGioHang.maSP = SanPham.maSP\n"
                + "	and Users.maKH = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, maKH);
        ResultSet rs = pstm.executeQuery();
        List<SanPhamInCart> list = new ArrayList<SanPhamInCart>();
        while (rs.next()) {
            int ID = rs.getInt("ID");
            int maSP = rs.getInt("maSP");
            String tenSP = rs.getString("tenSP");
            String hinhSP = rs.getString("hinhSP");
            float giaSP = rs.getFloat("giaSP");
            int soLuongSP = rs.getInt("soLuongSP");
            float thanhTien = rs.getFloat("thanhTien");
            SanPhamInCart sp = new SanPhamInCart(ID, maSP, tenSP, hinhSP, giaSP, soLuongSP, thanhTien);
            list.add(sp);
        }
        return list;
    }

    //
    public static void deleteCartByID(Connection conn, String id) throws SQLException {
        String sql = "{call delete_chiTietGioHang(?)}";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
        pstm.executeUpdate();
    }

    //
    public static void update_NumProductInGioHangByID(Connection conn, String id, String numProduct) throws SQLException {
        String sql = "{call update_NumProductInGioHang(?,?)}";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
        pstm.setString(2, numProduct);
        pstm.executeUpdate();
    }

    public static float tongTienInCart(Connection conn, String id) throws SQLException {
        String sql = "{? = call funcTongTienInCart(?)}";

        CallableStatement cstmt = conn.prepareCall(sql);
        cstmt.setString(2, id);
        cstmt.registerOutParameter(1, Types.FLOAT);
        cstmt.execute();
        return cstmt.getFloat(1);

    }

    public static List<ThuongHieu> getAllThuongHieu(Connection conn) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call load_ThuongHieu()}");
        ResultSet rs = cstm.executeQuery();
        List<ThuongHieu> list = new ArrayList<ThuongHieu>();
        while (rs.next()) {
            int maTH = rs.getInt("maTH");
            String tenTH = rs.getString("tenTH");
            String emailTH = rs.getString("emailTH");
            String hinhTH = rs.getString("hinhTH");
            ThuongHieu th = new ThuongHieu(maTH, tenTH, emailTH, hinhTH);
            list.add(th);
        }
        return list;
    }

    public static void insertSanPham(Connection conn, SanPham sp) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call insert_SanPham(?, ?, ?, ?, ?, ?, ?)}");
        cstm.setString(1, sp.getTenSP());
        cstm.setInt(2, sp.getMaTH());
        cstm.setInt(3, sp.getMaLoaiSP());
        cstm.setString(4, sp.getHinhSP());
        cstm.setFloat(5, sp.getGiaSP());
        cstm.setInt(6, sp.getSoLuongSP());
        cstm.setDate(7, sp.getNgayDangBan());
        cstm.execute();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionUtils.getConnection();
//		List<SanPham> ListSP = DBUtils.getAllSanPham(conn);
//		for (SanPham sanPham : ListSP) {
//			System.out.println(sanPham);
//		}

        List<LoaiSP> listLSP = DBUtils.getAllLoaiSP(conn);
        for (LoaiSP l : listLSP) {
            System.out.println(l);
        }
    }
}