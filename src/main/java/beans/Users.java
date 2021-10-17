package beans;

import java.util.Date;

public class Users {
	
	private int maKH;
	private String hoTen;
	private String sdt;
	private Date ngaySinh;
	private String diaChi;
	private String username;
	private String password;
	private String roleID;
	
	public Users() {}
	public Users(int maKH, String hoTen, String sdt, Date ngaySinh, String diaChi, String username, String password,
			String roleID) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.sdt = sdt;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.username = username;
		this.password = password;
		this.roleID = roleID;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	@Override
	public String toString() {
		return "Users [maKH=" + maKH + ", hoTen=" + hoTen + ", sdt=" + sdt + ", ngaySinh=" + ngaySinh + ", diaChi="
				+ diaChi + ", username=" + username + ", password=" + password + ", roleID=" + roleID + "]";
	}
	
	
	

}
