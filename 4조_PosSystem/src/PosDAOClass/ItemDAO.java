package PosDAOClass;


import DBCP.DBConnectionPoolMgr;

import java.sql.*;

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

    //상품 등록
    public  void createItem(String itemName, int price, int stock) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();

            StringBuffer cQuery = new StringBuffer();
            if(isItemExist(connection, preparedStatement, itemName)){
                return;
            } else {
                cQuery.append("INSERT INTO item(item_name, item_price, item_stock) values(?,?,?)");
                preparedStatement = connection.prepareStatement(cQuery.toString());
                preparedStatement.setString(1, itemName);
                preparedStatement.setInt(2, price);
                preparedStatement.setInt(3, stock);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnectionAndStmt(connection, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //상품 내역 조회
    public  void getItemList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();
            StringBuffer query = new StringBuffer();
            query.append("SELECT * FROM item");
            preparedStatement = connection.prepareStatement(query.toString());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("item_name");
                int price = rs.getInt("item_price");
                int stock = rs.getInt("item_stock");
                System.out.println(name + " " + price + " " + stock);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnectionAndStmt(connection, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //상품 상세 조회
    public  void getDetatilItemInfo(String itemName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();
            StringBuffer query = new StringBuffer();
            query.append("SELECT * FROM item WHERE item_name = ?");

            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1, itemName);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                String name = rs.getString("item_name");
                int price = rs.getInt("item_price");
                int stock = rs.getInt("item_stock");
                System.out.println(name +" "+price+" "+stock);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnectionAndStmt(connection, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public  void deleteItemInfo(String itemName){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();
            StringBuffer cQuery = new StringBuffer();
            if(isItemExist(connection, preparedStatement, itemName)){
                cQuery.append("DELETE FROM item WHERE item_name = ?");
                preparedStatement = connection.prepareStatement(cQuery.toString());
                preparedStatement.setString(1, itemName);
                preparedStatement.executeUpdate();
            }
            else{
                return ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnectionAndStmt(connection, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public  void updateItemInfo(String itemName, String newItemName, int itemPrice, int itemStock){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();
            StringBuffer cQuery = new StringBuffer();
            if(isItemExist(connection, preparedStatement, itemName)){
                cQuery.append("UPDATE item SET item_name = ?, item_price = ?, item_stock = ? WHERE item_name = ?");
                preparedStatement = connection.prepareStatement(cQuery.toString());
                preparedStatement.setString(1, newItemName);
                preparedStatement.setInt(2, itemPrice);
                preparedStatement.setInt(3, itemStock);
                preparedStatement.setString(4, itemName);
                preparedStatement.executeUpdate();
            }
            else{
                return ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnectionAndStmt(connection, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isItemExist(Connection connection, PreparedStatement preparedStatement, String itemName) throws Exception{
        StringBuffer query = new StringBuffer();
        query.append("SELECT IFNULL(MAX('Y'), 'N') AS item_exist_yn FROM item WHERE item_name = '" + itemName + "'");
        preparedStatement = connection.prepareStatement(query.toString());
        ResultSet rs = preparedStatement.executeQuery(query.toString());
        rs.next();
        String itemExistYn = rs.getString("item_exist_yn");
        return itemExistYn.equals("Y") ? true : false;
    }

    private void closeConnectionAndStmt(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

//    public static void main(String[] args) {
//        for(int i=0; i<10; i++){
//            createItem("오레오"+i, 1200, 5);
//        }
//        deleteItemInfo("오레오5");
//        updateItemInfo("오레오5", "GOD", 444, 444);
//        getItemList();
//    }
}