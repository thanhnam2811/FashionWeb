package beans;

public class VanChuyen {
    private int maDV;
    private String tenDV;
    private String email;
    private String SDT;
    private String diaChi;

    public VanChuyen() {
    }

    public VanChuyen(int maDV, String tenDV, String email, String SDT, String diaChi) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.email = email;
        this.SDT = SDT;
        this.diaChi = diaChi;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "VanChuyen{" +
                "maDV=" + maDV +
                ", tenDV='" + tenDV + '\'' +
                ", email='" + email + '\'' +
                ", SDT='" + SDT + '\'' +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }
}
