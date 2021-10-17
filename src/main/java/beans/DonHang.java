package beans;
import java.sql.Date;
import java.util.ArrayList;

public class DonHang {
	private int maDH;
	private int maKH;
	private Date ngayMua;
	private float tongTien;
	private int maDV;
	public DonHang() {
	
	}
	public DonHang(int maDH, int maKH, Date ngayMua, float tongTien, int maDV) {
		this.maDH = maDH;
		this.maKH = maKH;
		this.ngayMua = ngayMua;
		this.tongTien = tongTien;
		this.maDV = maDV;
	}
	public int getMaDH() {
		return maDH;
	}
	public void setMaDH(int maDH) {
		this.maDH = maDH;
	}
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public Date getNgayMua() {
		return ngayMua;
	}
	public void setNgayMua(Date ngayMua) {
		this.ngayMua = ngayMua;
	}
	public float getTongTien() {
		return tongTien;
	}
	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}
	public int getMaDV() {
		return maDV;
	}
	public void setMaDV(int maDV) {
		this.maDV = maDV;
	}
	
	
	
	@Override
	public String toString() {
		return "DonHang [maDH=" + maDH + ", maKH=" + maKH + ", ngayMua=" + ngayMua + ", tongTien=" + tongTien
				+ ", maDV=" + maDV + "]";
	}
	
}
