package beans;

public class SanPhamInCart {
    private int ID;
    private int maSP;
    private String tenSP;
    private String hinhSP;
    private float giaSP;
    private int soLuongSP;
    private float thanhTien;

    public SanPhamInCart() {
    }

    public SanPhamInCart(int ID, int maSP, String tenSP, String hinhSP, float giaSP, int soLuongSP, float thanhTien) {
        super();
        this.ID = ID;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.hinhSP = hinhSP;
        this.giaSP = giaSP;
        this.soLuongSP = soLuongSP;
        this.thanhTien = thanhTien;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
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

    @Override
    public String toString() {
        return "SanPhamInCart [ID=" + ID + ", maSP=" + maSP + ", tenSP=" + tenSP + ", hinhSP=" + hinhSP + ", giaSP="
                + giaSP + ", soLuongSP=" + soLuongSP + ", thanhTien=" + thanhTien + "]";
    }


}
