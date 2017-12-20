package PosDAOClass;

import DBCP.DBConnectionPoolMgr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalesInfoDAO {
    public static void createSaleInfo(String currentTime, int totalCost, SaledInfoDTO[] items) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();
            StringBuffer cQuery = new StringBuffer();
            cQuery = new StringBuffer();
            cQuery.append("INSERT INTO salesInfo(paytime, totalPrice) values(?,?)");
            preparedStatement = connection.prepareStatement(cQuery.toString());
            preparedStatement.setString(1,currentTime);
            preparedStatement.setInt(2, totalCost);
            preparedStatement.executeUpdate();

            cQuery = new StringBuffer();
            cQuery.append("select last_insert_id() as ID from salesInfo");
            preparedStatement = connection.prepareStatement(cQuery.toString());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            String id = rs.getString("ID");

            cQuery = new StringBuffer();
            cQuery.append("INSERT INTO salesItems(salesNo, pname, count, perprice) values(?, ?, ?, ?)");
            preparedStatement = connection.prepareStatement(cQuery.toString());

            for(int i=0; i<items.length; i++){
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, items[i].getItemName());
                preparedStatement.setInt(3, items[i].getItemCount());
                preparedStatement.setInt(4, items[i].getItemPrice());
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

    public static void getSalesItems(String salesNo){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();
            StringBuffer cQuery = new StringBuffer();

            cQuery.append("SELECT * FROM salesItems WHERE salesNo = ?");
            preparedStatement = connection.prepareStatement(cQuery.toString());
            preparedStatement.setString(1, salesNo);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                String name = rs.getString("pname");
                int count = rs.getInt("count");
                int price = rs.getInt("perprice");
                System.out.println(name + " "+ count + " "+ price);
            }

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

    public static void getSalesInfo(String itemNo){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();
            StringBuffer cQuery = new StringBuffer();

            cQuery.append("SELECT paytime, totalPrice FROM salesInfo WHERE salesNo = ?");
            preparedStatement = connection.prepareStatement(cQuery.toString());
            preparedStatement.setString(1, itemNo);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            String dayTime = rs.getString("paytime");
            int cost = rs.getInt("totalPrice");
            System.out.println(dayTime +" " +cost);
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
    
    public static ArrayList<SaledItemDTO> getAllItem(){
    	int salesNo;
    	String paytime;
    	int totalPrice;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<SaledItemDTO> SalesList = new ArrayList<SaledItemDTO>();
        
        try {
            connection = DBConnectionPoolMgr.geteDataSource().getConnection();
            StringBuffer cQuery = new StringBuffer();

            cQuery.append("SELECT salesNo, paytime, totalPrice FROM salesInfo");
            preparedStatement = connection.prepareStatement(cQuery.toString());
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                salesNo = rs.getInt("salesNo");
                paytime = rs.getString("paytime");
                totalPrice = rs.getInt("totalPrice");
                SalesList.add(new SaledItemDTO(salesNo, paytime, totalPrice));
            }
            
         
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
		return SalesList;
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
//        SaledInfoDTO[] items = new SaledInfoDTO[10];
//        for(int i=0; i<items.length; i++){
//            items[i] = new SaledInfoDTO("오레오"+i, 1200 + i, i);
//        }
//        createSaleInfo("2017/11/11 12:37", 3500, items);
//        getSaledItemList("2017/11/11 12:37");
//        getSalesInfo("1");
//        getSalesItems("1");
        ArrayList<SaledItemDTO> saledItemDTOS = getAllItem();
        for(int i=0; i<saledItemDTOS.size(); i++){
            System.out.println(saledItemDTOS.get(i).getPayTime());
            System.out.println(saledItemDTOS.get(i).getSaleNum());
            System.out.println(saledItemDTOS.get(i).getTotalPrice());
        }
    }
}

//ALTER TABLE salesInfo AUTO_INCREMENT=1;
//SET @CNT = 0;
//UPDATE salesInfo SET salesNo = @CNT:=@CNT+1;