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
            sanPham = new SanPham(maSP, tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan);
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
    public static void insertUser(Connection conn,String hoten, String sdt, Date ngaySinh,
                                  String diaChi,String username,String password)throws SQLException{
        String sql ="insert into Users(hoTen, sdt, ngaySinh, diaChi, username, password) "
                + "values(?,?, ?,?, ?, ?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, hoten);
        pstm.setString(2, sdt);
        pstm.setDate(3, ngaySinh);
        pstm.setString(4, diaChi);
        pstm.setString(5, username);
        pstm.setString(6, password);

        pstm.executeUpdate();
    }

    public static Users findUser(Connection conn, String username, String password) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call find_User(?,?)}");
        cstm.setString(1, username);
        cstm.setString(2, password);
        ResultSet rs = cstm.executeQuery();
        while (rs.next()) {
            return new Users(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getInt(8));
        }
        return null;
    }

    public static void updateSanPham(Connection conn, int maSP, String tenSP, int maTH, int maLoaiSP, String hinhSP, float giaSP, int soLuongSP, Date ngayDangBan) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call update_SanPham(?,?,?,?,?,?,?,?)}");
        // SanPham(int maSP, String tenSP, int maTH, int maLoaiSP, String hinhSP, float giaSP, int soLuongSP, Date ngayDangBan)
        cstm.setInt(1, maSP);
        cstm.setString(2, tenSP);
        cstm.setInt(3, maTH);
        cstm.setInt(4, maLoaiSP);
        cstm.setString(5, hinhSP);
        cstm.setFloat(6, giaSP);
        cstm.setInt(7, soLuongSP);
        cstm.setDate(8, ngayDangBan);
        cstm.execute();
    }

    public static void deleteSanPham(Connection conn, String maSP) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call delete_SanPham(?)}");
        cstm.setString(1, maSP);
        cstm.execute();
    }
    public static void EditUserInfo_password(Connection conn,int maKH,String npassword) throws SQLException {
        String sql =" Update Users"
                +" set password=?"
                +" where maKH=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, npassword);
        pstm.setInt(2, maKH);
        pstm.executeUpdate();
    }
    public static Users getInfoUser(Connection conn, int idmaKH) throws SQLException {
        String sql = "select * from users"
                + " where maKH=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idmaKH);
        ResultSet rs = pstm.executeQuery();
        Users user = new Users();

        while (rs.next()) {
            int maKH = rs.getInt("maKH");
            String hoTen = rs.getString("hoTen");
            String sdt = rs.getString("sdt");
            Date ngaySinh = rs.getDate("ngaySinh");
            String diaChi = rs.getString("diaChi");
            String userName = rs.getString("userName");
            String password = rs.getString("password");
            int roleID = rs.getInt("roleID");
            user = new Users(maKH, hoTen, sdt, ngaySinh, diaChi, userName,password,roleID);
        }
        return user;
    }
    public static void EditUserInfo(Connection conn,int maKH,String hoTen,String sdt,Date ngaySinh,String diaChi) throws SQLException {
        String sql =" Update Users"
                +" set hoTen =?,"
                +" sdt=?,"
                +" ngaySinh=?,"
                +" diaChi=?"
                +" where maKH=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, hoTen);
        pstm.setString(2, sdt);
        pstm.setDate(3, ngaySinh);
        pstm.setString(4, diaChi);
        pstm.setInt(5, maKH);
        pstm.executeUpdate();
    }
    public static List<ChiTietDonHang> getChiTietDonHang_bymaKH(Connection conn, int idmaKH) throws SQLException {
        String sql = "	select ChiTietDonHang.ID,ChiTietDonHang.maDH,ChiTietDonHang.maSP,ChiTietDonHang.soLuongSP,ChiTietDonHang.thanhTien"
                + "	from ChiTietDonHang join DonHang on ChiTietDonHang.maDH=DonHang.maDH"
                + "	where  DonHang.maKH=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idmaKH);
        ResultSet rs = pstm.executeQuery();
        List<ChiTietDonHang> list = new ArrayList<ChiTietDonHang>();


        while (rs.next()) {
            int ID = rs.getInt("ID");
            int maDH = rs.getInt("maDH");
            int maSP = rs.getInt("maSP");
            int soLuongSP = rs.getInt("soLuongSP");
            float thanhTien = rs.getFloat("thanhTien");
            ChiTietDonHang chiTietDonHang = new ChiTietDonHang(ID, maDH, maSP, soLuongSP, thanhTien);
            list.add(chiTietDonHang);
        }
        return list;
    }


    public static List<DonHang> getlistDonHang_bymaKH(Connection conn, int idmaKH) throws SQLException {
        String sql = " select DonHang.maDH,DonHang.maKH,DonHang.ngayMua,DonHang.tongTien,DonHang.maDV"
                + " from DonHang join Users on DonHang.maKH=Users.maKH"
                + " where  DonHang.maKH=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idmaKH);
        ResultSet rs = pstm.executeQuery();
        List<DonHang> list = new ArrayList<DonHang>();


        while (rs.next()) {
            int maDH = rs.getInt("maDH");
            int maKH = rs.getInt("maKH");
            Date ngayMua = rs.getDate("ngayMua");
            float tongTien = rs.getFloat("tongTien");
            int maDV = rs.getInt("maDV");
            DonHang donHang = new DonHang(maDH, maKH, ngayMua, tongTien, maDV);
            list.add(donHang);
        }
        return list;
    }
    public static void Addreview(Connection conn,int maKH,String maSP,String noiDung) throws SQLException {
        String sql =" insert into BinhLuan(maKH, maSP, noiDung) " +
                "   values(?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, maKH);
        pstm.setString(2, maSP);
        pstm.setString(3, noiDung);
        pstm.executeUpdate();
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionUtils.getConnection();


        List<LoaiSP> listLSP = DBUtils.getAllLoaiSP(conn);
        for (LoaiSP l : listLSP) {
            System.out.println(l);
        }

        System.out.println(getttSanPham(conn, "34"));
    }
}