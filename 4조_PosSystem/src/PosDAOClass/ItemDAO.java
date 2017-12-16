package PosDAOClass;

/**
 * @title 상품
 * @author Goodluckday
 */

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
    public static void getItemList() {
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
    public static void getDetatilItemInfo(String itemName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();
            StringBuffer query = new StringBuffer();
            query.append("SELECT * FROM item WHERE item_name = ?");

            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1, itemName);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            String name = rs.getString("item_name");
            int price = rs.getInt("item_price");
            int stock = rs.getInt("item_stock");

            System.out.println(name +" "+price+" "+stock);

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

    private static void closeConnectionAndStmt(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        createItem("오레오", 1200, 5);
        getDetatilItemInfo("오레오");
    }
}