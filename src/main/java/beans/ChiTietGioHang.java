package beans;

public class ChiTietGioHang {

    private int id;
    private int maKH;
    private SanPham sanPham;
    private float thanhTien;

    public ChiTietGioHang(){}
    public ChiTietGioHang(int id, int maKH, SanPham sanPham, float thanhTien) {
        this.id = id;
        this.maKH = maKH;
        this.sanPham = sanPham;
        this.thanhTien = thanhTien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }
}
