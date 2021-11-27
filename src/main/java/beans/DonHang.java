package beans;
import java.sql.Date;
import java.util.ArrayList;

public class DonHang {
	private int maDH;
	private int maKH;
	private  String tenNguoiNhan;
	private String diaChi;
	private  String sdt;
	private Date ngayMua;
	private float tongTien;
	private int maDV;

	//để tính doanh thu theo tháng
	private int thang;

	public DonHang() {
	}
	public DonHang(int thang, float tongTien)
	{
		this.thang = thang;
		this.tongTien = tongTien;
	}
	public DonHang(Date ngayMua, float tongTien)
	{
		this.ngayMua = ngayMua;
		this.tongTien = tongTien;
	}
	public DonHang(int maDH, int maKH, String tenNguoiNhan, String diaChi, String sdt, Date ngayMua, float tongTien, int maDV) {
		this.maDH = maDH;
		this.maKH = maKH;
		this.tenNguoiNhan = tenNguoiNhan;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.ngayMua = ngayMua;
		this.tongTien = tongTien;
		this.maDV = maDV;
	}

	public int getThang() {
		return thang;
	}

	public void setThang(int thang) {
		this.thang = thang;
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

	public String getTenNguoiNhan() {
		return tenNguoiNhan;
	}

	public void setTenNguoiNhan(String tenNguoiNhan) {
		this.tenNguoiNhan = tenNguoiNhan;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
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
		return "DonHang{" +
				"maDH=" + maDH +
				", maKH=" + maKH +
				", tenNguoiNhan='" + tenNguoiNhan + '\'' +
				", diaChi='" + diaChi + '\'' +
				", sdt='" + sdt + '\'' +
				", ngayMua=" + ngayMua +
				", tongTien=" + tongTien +
				", maDV=" + maDV +
				'}';
	}
}
