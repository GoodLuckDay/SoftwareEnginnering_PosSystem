package PosDAOClass;

public class SaledInfoDTO {
    private String itemName;
    private int itemPrice;
    private int itemCount;

    public SaledInfoDTO(String itemName, int itemCount, int itemPrice){
    	this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}
