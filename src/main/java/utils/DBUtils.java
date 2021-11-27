package utils;

import beans.*;
import conn.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    // Load all sanpham
    public static List<SanPham> getAllSanPham(Connection conn) throws SQLException {
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

    // Load all VanChuyen
    public static List<VanChuyen> getAllVanChuyen(Connection conn) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call load_VanChuyen()}");
        ResultSet rs = cstm.executeQuery();
        List<VanChuyen> list = new ArrayList<VanChuyen>();
        while (rs.next()) {
            int maDV = rs.getInt("maDV");
            String tenDV = rs.getString("tenDV");
            String email = rs.getString("email");
            String SDT = rs.getString("SDT");
            String diaChi = rs.getString("diaChi");
            VanChuyen vanChuyen = new VanChuyen(maDV, tenDV, email, SDT, diaChi);
            list.add(vanChuyen);
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

        // -- SQLSERVER
        CallableStatement cstm = conn.prepareCall("{call load_SanPham_by_maSP(?)}");
        cstm.setString(1, idSP);
        cstm.execute();
        ResultSet rs = cstm.getResultSet();
        SanPham sanPham = new SanPham();
        if (rs.next()) {
            int maSP = rs.getInt("maSP");
            String tenSP = rs.getString("tenSP");
            int maTH = rs.getInt("maTH");
            int maLoaiSP = rs.getInt("maLoaiSP");
            String hinhSP = rs.getString("hinhSP");
            float giaSP = rs.getFloat("giaSP");
            int soLuongSP = rs.getInt("soLuongSP");
            Date ngayDangBan = rs.getDate("ngayDangBan");
            sanPham = new SanPham(maSP, tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan);
            return sanPham;
        }
        return null;
    }

    public static List<SanPham> getSanPhambymaLoaiSP(Connection conn, String idmaLoaiSP) throws SQLException {
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

    public static List<BinhLuan> LoadlistBinhLuan(Connection conn) throws SQLException {
		String sql = " select maCMT,maSP,hoTen,noiDung,ngayDang" +
                " from users,binhluan" +
                " where users.maKH = binhluan.maKH ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
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


    public static List<ChiTietGioHang> getChiTietGioHangByMaKH(Connection conn, int maKH) throws SQLException {
//        String sql = "Select ChiTietGioHang.ID, ChiTietGioHang.maSP,ChiTietGioHang.soLuongSP, tenSP,giaSP,hinhSP,ChiTietGioHang.thanhTien\n"
//                + "From ChiTietGioHang, SanPham, Users\n"
//                + "Where ChiTietGioHang.maKH = Users.maKH  and ChiTietGioHang.maSP = SanPham.maSP\n"
//                + "	and Users.maKH = ?";

        String sql = "{call getChiTietGioHangByMaKH(?)}";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, maKH);
        ResultSet rs = pstm.executeQuery();
        List<ChiTietGioHang> list = new ArrayList<ChiTietGioHang>();
        while (rs.next()) {
            int ID = rs.getInt("ID");
            int idKH = maKH;
            SanPham sp = new SanPham();
            sp.setMaSP(rs.getInt("maSP"));
            sp.setTenSP(rs.getString("tenSP"));
            sp.setHinhSP(rs.getString("hinhSP"));
            sp.setSoLuongSP(rs.getInt("soLuongSP"));
            sp.setGiaSP(rs.getFloat("giaSP"));
            float thanhTien = rs.getFloat("thanhTien");
            ChiTietGioHang spInCart = new ChiTietGioHang(ID, idKH, sp, thanhTien);
            list.add(spInCart);
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

    public static float tongTienInCart(Connection conn, int id) throws SQLException {
        String sql = "{? = call funcTongTienInCart(?)}";

        CallableStatement cstmt = conn.prepareCall(sql);
        cstmt.setInt(2, id);
        cstmt.registerOutParameter(1, Types.FLOAT);
        cstmt.execute();
        return cstmt.getFloat(1);

    }

    public static int getMaDHMaxOfMaKH(Connection conn, int maKH) throws SQLException {
        String sql = "{? = call getMaDHMaxOfMaKH(?)}";

        CallableStatement cstmt = conn.prepareCall(sql);
        cstmt.setInt(2, maKH);
        cstmt.registerOutParameter(1, Types.INTEGER);
        cstmt.execute();
        return cstmt.getInt(1);

    }

    public static void insertDonHang(Connection conn, DonHang dh) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call insert_donhang(?, ?, ? ,? ,? ,?)}");
        // DonHang(maKH, tenNguoiNhan, diaChi,sdt,tongTien, maDV)
        cstm.setInt(1, dh.getMaKH());///
        cstm.setString(2, dh.getTenNguoiNhan());
        cstm.setString(3, dh.getDiaChi());
        cstm.setString(4, dh.getSdt());
        cstm.setFloat(5, dh.getTongTien());
        cstm.setInt(6, dh.getMaDV());
        cstm.execute();
    }

    public static void insertChiTietDonHang(Connection conn, int maDH, int maSP, int soLuong, float thanhTien) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call insert_chiTietDonHang(?, ?, ?,?)}");
        cstm.setInt(1, maDH);
        cstm.setInt(2, maSP);
        cstm.setInt(3, soLuong);
        cstm.setFloat(4, thanhTien);
        cstm.execute();
    }

    public static void addSpToCart(Connection conn, int maKH, int maSP, int soLuong) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call insert_chiTietGioHang(?, ?, ?)}");
        cstm.setInt(1, maKH);
        cstm.setInt(2, maSP);
        cstm.setInt(3, soLuong);
        cstm.execute();
    }

    public static void deleteGioHangBymaKH(Connection conn, int maKH) throws SQLException {
//        String sql ="delete ChiTietGioHang\n" +
//                "where maKH = ?";

        String sql = "{call deleteGioHangBymaKH(?)}";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, maKH);
        pstm.executeUpdate();
    }

    // Admin User
// Load all sanpham
    public static List<Users> getAllUser(Connection conn) throws SQLException {

        CallableStatement cstm = conn.prepareCall("{call load_Users()}");
        ResultSet rs = cstm.executeQuery();
        List<Users> list = new ArrayList<Users>();
        while (rs.next()) {
            int maKH = rs.getInt("maKH");
            String hoTen = rs.getString("hoTen");
            String sdt = rs.getString("sdt");
            Date ngaySinh = rs.getDate("ngaySinh");
            String diaChi = rs.getString("diaChi");
            String userName = rs.getString("userName");
            String password = rs.getString("password");
            int roleID = rs.getInt("roleID");
            Users user = new Users(maKH, hoTen, sdt, ngaySinh, diaChi, userName, password, roleID);
            list.add(user);
        }
        return list;
    }

    public static List<Role> getAllRold(Connection conn) throws SQLException {

        CallableStatement cstm = conn.prepareCall("{call load_role()}");
        ResultSet rs = cstm.executeQuery();
        List<Role> list = new ArrayList<Role>();
        while (rs.next()) {
            int roleID = rs.getInt("roleID");
            String name = rs.getString("name");
            Role role = new Role(roleID, name);
            list.add(role);
        }
        return list;
    }

    //
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

    public static void insertUser(Connection conn, String hoten, String sdt, Date ngaySinh,
                                  String diaChi, String username, String password) throws SQLException {
//        String sql ="insert into Users(hoTen, sdt, ngaySinh, diaChi, username, password) "
//                + "values(?,?, ?,?, ?, ?)";

        String sql = "{call insert_Users(?,?,?,?,?,?)} ";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, hoten);
        pstm.setString(2, sdt);
        pstm.setDate(3, ngaySinh);
        pstm.setString(4, diaChi);
        pstm.setString(5, username);
        pstm.setString(6, password);

        pstm.executeUpdate();
    }

    public static void insertAdmin(Connection conn, String hoten, String sdt, Date ngaySinh,
                                   String diaChi, String username, String password, int roleID) throws SQLException {
//        String sql ="insert into Users(hoTen, sdt, ngaySinh, diaChi, username, password,roleID) "
//                + "values(?,?, ?,?, ?, ?,?)";

        String sql = "{call insertUserAdmin(?,?,?,?,?,?,?)} ";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, hoten);
        pstm.setString(2, sdt);
        pstm.setDate(3, ngaySinh);
        pstm.setString(4, diaChi);
        pstm.setString(5, username);
        pstm.setString(6, password);
        pstm.setInt(7, roleID);

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

    public static Users findUserByUsername(Connection conn, String username) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call findUserByUserName(?)}");
        cstm.setString(1, username);
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

    public static void deleteUser(Connection conn, int maKH) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call delete_Users(?)}");
        cstm.setInt(1, maKH);
        cstm.execute();
    }

    public static boolean EditUserInfo_password(Connection conn, String username, String npassword, String opassword) throws SQLException {
        String sql = "{call EditUserInfo_password(?, ?, ?)}";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, npassword);
        pstm.setString(3, opassword);
        if (pstm.executeUpdate() == 0)
            return false;
        return true;
    }

    public static Users getInfoUser(Connection conn, int idmaKH) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call load_UserInfo_bymaKH(?)}");
        cstm.setInt(1, idmaKH);
        ResultSet rs = cstm.executeQuery();
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
            user = new Users(maKH, hoTen, sdt, ngaySinh, diaChi, userName, password, roleID);
        }
        return user;
    }

    public static void EditUserInfo(Connection conn, int maKH, String hoTen, String sdt, Date ngaySinh, String diaChi) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call update_UserInfo_bymaKH(?,?,?,?,?)}");
        cstm.setInt(1, maKH);
        cstm.setString(2, hoTen);
        cstm.setString(3, sdt);
        cstm.setDate(4, ngaySinh);
        cstm.setString(5, diaChi);
        cstm.executeUpdate();
    }

    public static void EditUser(Connection conn, int maKH, String hoTen, String sdt, Date ngaySinh, String diaChi, String username, int roleID) throws SQLException {
        String sql = "{call Admin_editUser(?,?,?,?,?,?,?)}";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, hoTen);
        pstm.setString(2, sdt);
        pstm.setDate(3, ngaySinh);
        pstm.setString(4, diaChi);
        pstm.setString(5, username);
        pstm.setInt(6, roleID);
        pstm.setInt(7, maKH);
        pstm.executeUpdate();
    }

    public static void updateAdmin(Connection conn, String username, String hoTen, String sdt, Date ngaySinh, String diaChi, int roleID) throws SQLException {
        String sql = "update Users " +
                "set " +
                "hoTen = ?, " +
                "sdt = ?, " +
                "ngaySinh = ?, " +
                "diaChi = ?, " +
                "roleID = ? " +
                "where username = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, hoTen);
        pstm.setString(2, sdt);
        pstm.setDate(3, ngaySinh);
        pstm.setString(4, diaChi);
        pstm.setInt(5, roleID);
        pstm.setString(6, username);
        pstm.executeUpdate();
    }

    public static void deleteAdmin(Connection conn, String username) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("delete Users where username = ?");
        pstm.setString(1, username);
        pstm.execute();
    }

    public static List<ChiTietDonHang> getChiTietDonHang_bymaKH(Connection conn, int idmaKH) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call load_listChiTietDonHang_bymaKH(?)}");
        cstm.setInt(1, idmaKH);
        ResultSet rs = cstm.executeQuery();
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
        CallableStatement cstm = conn.prepareCall("{call load_listDonHang_bymaKH(?)}");
        cstm.setInt(1, idmaKH);
        ResultSet rs = cstm.executeQuery();
        List<DonHang> list = new ArrayList<DonHang>();


        while (rs.next()) {
            int maDH = rs.getInt("maDH");
            int maKH = rs.getInt("maKH");
            String tenNguoiNhan = rs.getString("tenNguoiNhan");
            String diaChi = rs.getString("diaChi");
            String sdt = rs.getString("sdt");
            Date ngayMua = rs.getDate("ngayMua");
            float tongTien = rs.getFloat("tongTien");
            int maDV = rs.getInt("maDV");
            DonHang donHang = new DonHang(maDH, maKH, tenNguoiNhan, diaChi, sdt, ngayMua, tongTien, maDV);
            list.add(donHang);
        }
        return list;
    }

    public static void Addreview(Connection conn, int maKH, String maSP, String noiDung) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call insert_BinhLuan(?,?,?)}");

        cstm.setInt(1, maKH);
        cstm.setString(2, maSP);
        cstm.setString(3, noiDung);
        cstm.executeUpdate();
    }

    public static void Deletereview(Connection conn, String maCMT) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call delete_BinhLuan(?)}");

        cstm.setString(1, maCMT);

        cstm.execute();
    }
    public static void AdminDeletereview(Connection conn, String maCMT) throws SQLException {
        String sql = " delete binhluan" +
                " where binhluan.maCMT= ? ";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,maCMT);
        pstm.executeUpdate();
    }

    //ThongKe
    public static List<ChiTietDonHang> getSoLuongSPDaMua(Connection conn) throws SQLException {
        String sql = "Select maSP, Sum(soLuongSP)as SoLuong\n" +
                "From ChiTietDonHang\n" +
                "Group by maSP";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<ChiTietDonHang> list = new ArrayList<ChiTietDonHang>();


        while (rs.next()) {

            int maSP = rs.getInt("maSP");
            int soLuongSP = rs.getInt("soLuong");
            ChiTietDonHang chiTietDonHang = new ChiTietDonHang(maSP, soLuongSP);
            list.add(chiTietDonHang);
        }
        return list;
    }

    public static List<DonHang> getDanhThuTheoNgay(Connection conn) throws SQLException {
//        String sql = "Select ngayMua, Sum(tongTien)as tongTien\n" +
//                "From DonHang\n" +
//                "Group by ngayMua";

        String sql = "{call getDoanhThuTheoNgay()}";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<DonHang> list = new ArrayList<DonHang>();


        while (rs.next()) {

            Date ngayMua = rs.getDate("ngayMua");
            float tongTien = rs.getInt("tongTien");
            DonHang dh = new DonHang(ngayMua, tongTien);
            list.add(dh);
        }
        return list;
    }

    public static List<SanPham> getSPBanNhieu(Connection conn) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call load_SPBanNhieu()}");
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
    public static List<Users> LoadlistUser(Connection conn) throws SQLException {
        String sql = " select *" +
                " from users" +
                " where roleID= 1 ";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Users> list = new ArrayList<Users>();
        while (rs.next()) {
            int maKH = rs.getInt("maKH");
            String hoTen = rs.getString("hoTen");
            String sdt = rs.getString("sdt");
            Date ngaySinh = rs.getDate("ngaySinh");
            String diaChi = rs.getString("diaChi");
            String userName = rs.getString("userName");
            String password = rs.getString("password");
            int roleID = rs.getInt("roleID");
            Users u = new Users(maKH, hoTen, sdt, ngaySinh, diaChi,userName,password,roleID);
            list.add(u);
        }
        return list;
    }
    public static void AdminDeleteUser(Connection conn, String username) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("delete Users where username = ?");
        pstm.setString(1, username);
        pstm.execute();
    }



    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionUtils.getConnection();
        //EditUserInfo_password(conn,1,"nam1");
        //Deletereview(conn,"2");
//        List<LoaiSP> listLSP = DBUtils.getAllLoaiSP(conn);
//        for (LoaiSP l : listLSP) {
//            System.out.println(l);
//        }
//        Users u = DBUtils.getInfoUser(conn,1);
//        System.out.println(u);

//        System.out.println(getttSanPham(conn, "34"));
//        List<ChiTietDonHang> list = DBUtils.getChiTietDonHang_bymaKH(conn, 1);
//        for (ChiTietDonHang l : list) {
//            System.out.println(l);
//        }
//        List<Users> list = DBUtils.LoadlistUser(conn);
//        for (Users l : list) {
//            System.out.println(l);
//        }

    }
}