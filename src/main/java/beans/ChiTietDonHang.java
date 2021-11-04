package beans;

import java.util.List;
import java.sql.Date;


public class ChiTietDonHang {
	private int ID;
	private int maDH;
	private int maSP;
	private int soLuongSP;
	private float thanhTien;
	public ChiTietDonHang() {
	}

	public ChiTietDonHang(int maSP,int soLuongSP)
	{
		this.maSP = maSP;
		this.soLuongSP = soLuongSP;
	}
	public ChiTietDonHang(int iD, int maDH, int maSP, int soLuongSP, float thanhTien) {
		super();
		ID = iD;
		this.maDH = maDH;
		this.maSP = maSP;
		this.soLuongSP = soLuongSP;
		this.thanhTien = thanhTien;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getMaDH() {
		return maDH;
	}
	public void setMaDH(int maDH) {
		this.maDH = maDH;
	}
	public int getMaSP() {
		return maSP;
	}
	public void setMaSP(int maSP) {
		this.maSP = maSP;
	}
	public int getSoLuongSP() {
		return soLuongSP;
	}
	public void setSoLuongSP(int soLuongSP) {
		this.soLuongSP = soLuongSP;
	}
	public float getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(float thanhTien) {
		this.thanhTien = thanhTien;
	}
	
	public String gettenSP (List<SanPham> listSP ) {
		for(SanPham sp: listSP) {
			if (sp.getMaSP()==this.maSP) return sp.getTenSP();
		}
		return null;
	}
	public float getgiaSP (List<SanPham> listSP ) {
		for(SanPham sp: listSP) {
			if (sp.getMaSP()==this.maSP) return sp.getGiaSP();
		}
		return 0;
		
	}

	@Override
	public String toString() {
		return "ChiTietDonHang [ID=" + ID + ", maDH=" + maDH + ", maSP=" + maSP + ", soLuongSP=" + soLuongSP
				+ ", thanhTien=" + thanhTien + "]";
	}
	
}
