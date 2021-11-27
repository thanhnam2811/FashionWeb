package beans;

import java.sql.Date;
import java.util.List;

public class SanPham {
    private int maSP;
    private String tenSP;
    private int maTH;
    private int maLoaiSP;
    private String hinhSP;
    private float giaSP;
    private int soLuongSP;
    private Date ngayDangBan;


    public SanPham(int maLoaiSP , int soLuongSP){
        this.maLoaiSP = maLoaiSP;
        this.soLuongSP = soLuongSP;
    }
    public SanPham(int maSP, String tenSP, int maTH, int maLoaiSP, String hinhSP, float giaSP, Date ngayDangBan) {
    }
    public SanPham(){

    }
    public SanPham(int maSP, String tenSP, String hinhSP, float giaSP, int soLuongSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.hinhSP = hinhSP;
        this.giaSP = giaSP;
        this.soLuongSP = soLuongSP;
    }
    public SanPham(int maSP, String tenSP, int maTH, int maLoaiSP, String hinhSP, float giaSP, int soLuongSP, Date ngayDangBan) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maTH = maTH;
        this.maLoaiSP = maLoaiSP;
        this.hinhSP = hinhSP;
        this.giaSP = giaSP;
        this.soLuongSP = soLuongSP;
        this.ngayDangBan = ngayDangBan;
    }

    public String getTenLoaiSP(List<LoaiSP> list){
        for (LoaiSP lsp: list) {
            if (lsp.getMaLoaiSP() == this.maLoaiSP)
                return lsp.getTenLoaiSP();
        }
        return null;
    }

    public String getTenThuongHieu(List<ThuongHieu> list){
        for (ThuongHieu th: list) {
            if (th.getMaTH() == this.maTH)
                return th.getTenTH();
        }
        return null;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getMaTH() {
        return maTH;
    }

    public void setMaTH(int maTH) {
        this.maTH = maTH;
    }

    public int getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(int maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public String getHinhSP() {
        return hinhSP;
    }

    public void setHinhSP(String hinhSP) {
        this.hinhSP = hinhSP;
    }

    public float getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(float giaSP) {
        this.giaSP = giaSP;
    }

    public Date getNgayDangBan() {
        return ngayDangBan;
    }

    public void setNgayDangBan(Date ngayDangBan) {
        this.ngayDangBan = ngayDangBan;
    }

    @Override
    public String toString() {
        return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", maTH=" + maTH + ", maLoaiSP=" + maLoaiSP + ", hinhSP="
                + hinhSP + ", giaSP=" + giaSP + ", ngayDangBan=" + ngayDangBan + "]";
    }

}
