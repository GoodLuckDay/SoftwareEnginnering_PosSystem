package PosDAOClass;

import DBCP.DBConnectionPoolMgr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesInfo {
    public static void createSaleInfo(String currentTime, int totalCost, TestItem[] items) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();
            StringBuffer cQuery = new StringBuffer();

            cQuery.append("INSERT INTO salesInfo(paymentTime, totalCost) values(?,?)");
            preparedStatement = connection.prepareStatement(cQuery.toString());
            preparedStatement.setString(1, currentTime);
            preparedStatement.setInt(2, totalCost);
            preparedStatement.executeUpdate();

            cQuery = new StringBuffer();
            cQuery.append("INSERT INTO saledItem(paymentTime, saledItem_name, saledItem_count, saledItem_price) values(?,?,?,?)");
            preparedStatement = connection.prepareStatement(cQuery.toString());
            for(int i=0; i<items.length; i++){
                preparedStatement.setString(1, currentTime);
                preparedStatement.setString(2, items[i].itemName);
                preparedStatement.setInt(3, items[i].itemCount);
                preparedStatement.setInt(4, items[i].itemPrice);
                preparedStatement.addBatch();
                preparedStatement.clearParameters();
            }
            preparedStatement.executeBatch();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally

        {
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
        TestItem[] items = new TestItem[10];
        for(int i=0; i<items.length; i++){
            items[i] = new TestItem("오레오"+i, 1200 + i, i);
        }
        createSaleInfo("2017/11/11 12:37:44", 3500, items);
    }
}
