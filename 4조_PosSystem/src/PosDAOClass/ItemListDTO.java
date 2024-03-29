package PosDAOClass;

import java.util.ArrayList;
import java.util.Vector;

public class ItemListDTO {
    private ItemDAO itemDAO = null;
    private int itemNo;
    public ArrayList<ItemDTO> items = null;

    public ItemListDTO(){
        itemDAO = new ItemDAO();
        items = itemDAO.getItemList();
        itemNo = 0;
    }

    public Vector getItemListData(){
        Vector data = new Vector();
        items = itemDAO.getItemList();
        for(int i=0; i<items.size(); i++){
            Vector tempVector = new Vector();
            ItemDTO tempItemDTO = items.get(i);
            tempVector.add(i+1);
            tempVector.add(tempItemDTO.getItemName());
            tempVector.add(tempItemDTO.getItemStock());
            tempVector.add(tempItemDTO.getItemPrice());
            data.add(tempVector);
        }
        return data;
    }
    public void updateItemInfo(String oldItemName, String updatedItemName, int updatedItemPrice, int updatedItemStock){
        itemDAO.updateItemInfo(oldItemName, updatedItemName, updatedItemPrice, updatedItemStock);
    }

    public void purChaseItem(String itemName, int itemCount){
        itemDAO.buyItem(itemName, itemCount);
    }
    public boolean deleteItemInfo(String itemName){
        return itemDAO.deleteItemInfo(itemName);
    }

    public boolean addItem(String itemName, int itemPrice, int itemStock){
        return itemDAO.createItem(itemName, itemPrice, itemStock);
    }

    public ItemDTO getItemInfo(int itemNo){
        return items.get(itemNo);
    }
}
