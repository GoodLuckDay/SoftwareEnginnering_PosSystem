package DBCP;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBConnectionPoolMgr {
    private static BasicDataSource dataSource;

    public static BasicDataSource geteDataSource(){
        if(dataSource == null){
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost/jdbcTestServer");
            ds.setUsername("jdbcTest");
            ds.setPassword("1234");

            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(180);
            dataSource = ds;
        }
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
