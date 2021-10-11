package beans;

import java.sql.Date;

public class BinhLuan implements Comparable<BinhLuan> {
	private int maCMT;
	private String hoTen;
	private int maSP;
	private String noiDung;
	private Date ngayDang;
	
	public BinhLuan() {
	}
	

	public BinhLuan(int maCMT, String hoTen, int maSP, String noiDung, Date ngayDang) {
		this.maCMT = maCMT;
		this.hoTen = hoTen;
		this.maSP = maSP;
		this.noiDung = noiDung;
		this.ngayDang = ngayDang;
	}
	
	


	public int getMaCMT() {
		return maCMT;
	}


	public void setMaCMT(int maCMT) {
		this.maCMT = maCMT;
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
