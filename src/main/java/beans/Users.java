package beans;

import java.sql.Date;
import java.util.List;

public class Users {
    private int maKH;
    private String hoTen;
    private String sdt;
    private Date ngaySinh;
    private String diaChi;
    private String userName;
    private String password;
    private int roleID = 0;

    public Users(String hoTen , String userName){
		this.hoTen = hoTen;
		this.userName = userName;
	}public Users() {
    }

    public Users(int maKH, String hoTen, String sdt, Date ngaySinh, String diaChi, String userName, String password,
                 int roleID) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.userName = userName;
        this.password = password;
        this.roleID = roleID;
    }

    public String getRoleName(List<Role> listRole) {
        for (Role r : listRole)
            if (r.getRoleID() == roleID)
                return  r.getName();
        return null;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @Override
    public String toString() {
        return "Users [maKH=" + maKH + ", hoTen=" + hoTen + ", sdt=" + sdt + ", ngaySinh=" + ngaySinh + ", diaChi="
                + diaChi + ", userName=" + userName + ", password=" + password + ", roleID=" + roleID + "]";
    }



}