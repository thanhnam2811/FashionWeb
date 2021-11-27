package beans;

import java.sql.Date;
import java.util.List;

public class BinhLuan implements Comparable<BinhLuan> {
	private int maCMT;
	private String hoTen;
	private int maSP;
	private String noiDung;
	private Date ngayDang;
	private int maKH;


	public BinhLuan(int maKH, String noiDung, int maSP){
		this.maKH = maKH;
		this.noiDung = noiDung;
		this.maSP = maSP;
	}
	public BinhLuan() {
	}
	

	public BinhLuan(int maCMT, String hoTen, int maSP, String noiDung, Date ngayDang) {
		this.maCMT = maCMT;
		this.hoTen = hoTen;
		this.maSP = maSP;
		this.noiDung = noiDung;
		this.ngayDang = ngayDang;
	}

	public String getHoTenKH(List<Users> list){
		for (Users user: list) {
			if (user.getMaKH() == this.maKH)
				return user.getHoTen();
		}
		return null;
	}
	public String getAnhSP(List<SanPham> list){
		for (SanPham sp: list) {
			if (sp.getMaSP() == this.maSP)
				return sp.getHinhSP();
		}
		return null;
	}


	public int getMaCMT() {
		return maCMT;
	}


	public void setMaCMT(int maCMT) {
		this.maCMT = maCMT;
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


	public int getMaSP() {
		return maSP;
	}


	public void setMaSP(int maSP) {
		this.maSP = maSP;
	}


	public String getNoiDung() {
		return noiDung;
	}


	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}


	public Date getNgayDang() {
		return ngayDang;
	}


	public void setNgayDang(Date ngayDang) {
		this.ngayDang = ngayDang;
	}
	@Override
    public int compareTo(BinhLuan binhLuan){
        return ngayDang.compareTo(binhLuan.ngayDang);
	
    }

	@Override
	public String toString() {
		return "BinhLuan [maCMT=" + maCMT + ", hoTen=" + hoTen + ", maSP=" + maSP + ", noiDung=" + noiDung + ", ngayDang="
				+ ngayDang + "]";
	}

}
