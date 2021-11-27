package utils;

import beans.ThuongHieu;

import java.sql.*;
import java.util.List;

public class AdminManagerBrand {
    public static String update = "update";
    public static String delete = "delete";
    public static String insert = "insert";

    public static void ControllBrand(Connection conn, ThuongHieu th, String action) throws SQLException {
        if (action.equals(update)) {
            CallableStatement cstm = conn.prepareCall("{call update_ThuongHieu(?,?, ?, ?)}");
            cstm.setInt(1, th.getMaTH());
            cstm.setString(2, th.getTenTH());
            cstm.setString(3, th.getEmailTH());
            cstm.setString(4, th.getHinhTH());
            cstm.execute();
        } else {
            if (action.equals(insert)) {
                CallableStatement cstm = conn.prepareCall("{call insert_ThuongHieu(?, ?, ?)}");
                cstm.setString(1, th.getTenTH());
                cstm.setString(2, th.getEmailTH());
                cstm.setString(3, th.getHinhTH());
                cstm.execute();
            }

        }
    }

    public static ThuongHieu load_TH_byMaTH(Connection conn, String idmaTH) throws SQLException {
        CallableStatement cstm = conn.prepareCall("{call load_ThuongHieu_byMaTH(?)}");
        cstm.setString(1, idmaTH);
        cstm.execute();
        ResultSet rs = cstm.getResultSet();
        ThuongHieu thuongHieu = null;

        while (rs.next()) {
            int maTH = rs.getInt("maTH");
            String tenTH = rs.getString("tenTH");
            String emailTH = rs.getString("emailTH");
            String hinhTH = rs.getString("hinhTH");

            thuongHieu = new ThuongHieu(maTH, tenTH, emailTH, hinhTH);
        }
        return thuongHieu;
    }

    public static void deleteTH(Connection conn, String id) throws SQLException {
        String sql = "{call delete_ThuongHieu(?)}";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
        pstm.executeUpdate();
    }

}
