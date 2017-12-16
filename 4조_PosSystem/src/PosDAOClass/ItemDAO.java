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
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();

            StringBuffer query = new StringBuffer();
            StringBuffer cQuery = new StringBuffer();
            query.append("SELECT IFNULL(MAX('Y'), 'N') AS item_exist_yn FROM item WHERE item_name = '" + item + "'");

            preparedStatement = connection.prepareStatement(query.toString());

            ResultSet rs = preparedStatement.executeQuery(query.toString());
            rs.next();
            String itemExistYn = rs.getString("item_exist_yn");

            if (itemExistYn.equals("Y")) {
                return;
            } else {
                cQuery.append("INSERT INTO item(item_name, item_price, item_stock) values(?,?,?)");
                preparedStatement = connection.prepareStatement(cQuery.toString());
                preparedStatement.setString(1, item);
                preparedStatement.setInt(2, price);
                preparedStatement.setInt(3, stock);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void getItemList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();
            StringBuffer query = new StringBuffer();
            query.append("SELECT * FROM item");
            preparedStatement = connection.prepareStatement(query.toString());

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String name = rs.getString("item_name");
                int price = rs.getInt("item_price");
                int stock = rs.getInt("item_stock");
                System.out.println(name + " "+ price + " "+ stock);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        for(int i=1; i<=20; i++){
         createItem("Test"+i, i, i+10);
        }
        getItemList();
    }
}