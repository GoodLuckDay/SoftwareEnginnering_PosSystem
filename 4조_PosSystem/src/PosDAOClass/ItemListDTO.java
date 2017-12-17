package PosDAOClass;

import java.util.ArrayList;
import java.util.Vector;

public class ItemListDTO {
    private ItemDAO itemDAO = null;
    private ArrayList<ItemDTO> items = null;
    private int itemNo;

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
            tempVector.add(tempItemDTO.getItemPrice());
            tempVector.add(tempItemDTO.getItemStock());
            data.add(tempVector);
        }
        return data;
    }

    public boolean addItem(String itemName, int itemPrice, int itemStock){
        return itemDAO.createItem(itemName, itemPrice, itemStock);
    }

    public ItemDTO getItemInfo(int itemNo){
        return items.get(itemNo);
    }
}
