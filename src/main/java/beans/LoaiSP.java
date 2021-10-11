package beans;

public class LoaiSP {
    private int maLoaiSP;

    @Override
    public String toString() {
        return "LoaiSP{" +
                "maLoaiSP=" + maLoaiSP +
                ", tenLoaiSP='" + tenLoaiSP + '\'' +
                '}';
    }

    private String tenLoaiSP;

    public LoaiSP(int maLoaiSP, String tenLoaiSP) {
        this.maLoaiSP = maLoaiSP;
        this.tenLoaiSP = tenLoaiSP;
    }

    public int getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(int maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }
}
