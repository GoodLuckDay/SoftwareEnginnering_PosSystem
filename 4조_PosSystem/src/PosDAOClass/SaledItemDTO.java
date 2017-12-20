package PosDAOClass;

public class SaledItemDTO {
    private int saleNum;

    private String payTime;
    private int totalPrice;

    public SaledItemDTO(int saleNum, String payTime, int totalPrice) {
        this.saleNum = saleNum;
        this.totalPrice = totalPrice;
        this.payTime = payTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }
}
