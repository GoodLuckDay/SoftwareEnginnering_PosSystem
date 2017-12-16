package PosDAOClass;

import DBCP.DBConnectionPoolMgr;

import java.sql.*;
import java.util.Vector;

public class ItemDAO {
    private String itemName;
    private int itemPrice;
    private int itemStock;

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

    public int getItemStock() {
        return itemStock;
    }

    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }

    public static void createItem(String item, int price, int stock) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();

            StringBuffer query = new StringBuffer();
            StringBuffer cQuery = new StringBuffer();
            query.append("SELECT IFNULL(MAX('Y'), 'N') AS item_exist_yn FROM item WHERE item_name = '"+item+"'");

            statement = connection.prepareStatement(query.toString());

            ResultSet rs = statement.executeQuery(query.toString());
            rs.next();
            String itemExistYn = rs.getString("item_exist_yn");

            if(itemExistYn.equals("Y")){
                return ;
            }
            else {
                cQuery.append("INSERT INTO item(item_name, item_price, item_stock) values(?,?,?)");
                statement = connection.prepareStatement(cQuery.toString());
                statement.setString(1, item);
                statement.setInt(2, price);
                statement.setInt(3, stock);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    public static void main(String[] args) {
//        createItem("돈", 134, 4);
    }
}