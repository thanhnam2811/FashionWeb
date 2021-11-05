package beans;

public class ThuongHieu {
	private int maTH;
	private String tenTH;
	private String emailTH;
	private String hinhTH;
	public ThuongHieu() {
	}
	public ThuongHieu(int maTH, String tenTH, String emailTH, String hinhTH) {
		this.maTH = maTH;
		this.tenTH = tenTH;
		this.emailTH = emailTH;
		this.hinhTH = hinhTH;
	}
	public ThuongHieu( String tenTH, String emailTH, String hinhTH) {
		this.maTH = maTH;
		this.tenTH = tenTH;
		this.emailTH = emailTH;
		this.hinhTH = hinhTH;
	}

	public int getMaTH() {
		return maTH;
	}
	public void setMaTH(int maTH) {
		this.maTH = maTH;
	}
	public String getTenTH() {
		return tenTH;
	}
	public void setTenTH(String tenTH) {
		this.tenTH = tenTH;
	}
	public String getEmailTH() {
		return emailTH;
	}
	public void setEmailTH(String emailTH) {
		this.emailTH = emailTH;
	}
	public String getHinhTH() {
		return hinhTH;
	}
	public void setHinhTH(String hinhTH) {
		this.hinhTH = hinhTH;
	}
	@Override
	public String toString() {
		return "ThuongHieu [maTH=" + maTH + ", tenTH=" + tenTH + ", emailTH=" + emailTH + ", hinhTH=" + hinhTH + "]";
	}
	
}
