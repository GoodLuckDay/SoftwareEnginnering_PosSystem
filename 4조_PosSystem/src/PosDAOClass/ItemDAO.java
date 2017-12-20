package PosDAOClass;


import DBCP.DBConnectionPoolMgr;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class ItemDAO {

    //상품 등록
    public  boolean createItem(String itemName, int price, int stock) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();

            StringBuffer cQuery = new StringBuffer();
            if(isItemExist(connection, preparedStatement, itemName)){
                return false;
            } else {
                cQuery.append("INSERT INTO item(item_name, item_price, item_stock) values(?,?,?)");
                preparedStatement = connection.prepareStatement(cQuery.toString());
                preparedStatement.setString(1, itemName);
                preparedStatement.setInt(2, price);
                preparedStatement.setInt(3, stock);
                result = preparedStatement.executeUpdate();
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
        return result > 0 ? true : false;
    }

    //상품 내역 조회
    public ArrayList<ItemDTO> getItemList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<ItemDTO> data = null;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();
            StringBuffer query = new StringBuffer();
            query.append("SELECT * FROM item");
            preparedStatement = connection.prepareStatement(query.toString());
            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            data = new ArrayList<>();
            while (rs.next()) {

                String name = rs.getString("item_name");
                int price = rs.getInt("item_price");
                int stock = rs.getInt("item_stock");
                data.add(new ItemDTO(name, price, stock));
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
        return data;
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

    //상품 정보 삭제
    public boolean deleteItemInfo(String itemName){
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
                return false;
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
            return true;
        }
    }

    //상품 정보 갱신
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

    public boolean buyItem(String itemName, int itemCount){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();
            StringBuffer cQuery = new StringBuffer();
            if(isItemExist(connection, preparedStatement, itemName)){
                cQuery.append("update item set item_stock = item_stock - ? where item_name = ?");
                preparedStatement = connection.prepareStatement(cQuery.toString());
                preparedStatement.setInt(1, itemCount);
                preparedStatement.setString(2, itemName);
                preparedStatement.executeUpdate();
                return true;
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
        return false;
    }

    //상품 확인
    private boolean isItemExist(Connection connection, PreparedStatement preparedStatement, String itemName) throws Exception{
        StringBuffer query = new StringBuffer();
        query.append("SELECT IFNULL(MAX('Y'), 'N') AS item_exist_yn FROM item WHERE item_name = '" + itemName + "'");
        preparedStatement = connection.prepareStatement(query.toString());
        ResultSet rs = preparedStatement.executeQuery(query.toString());
        rs.next();
        String itemExistYn = rs.getString("item_exist_yn");
        return itemExistYn.equals("Y") ? true : false;
    }

    //연결 제거
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