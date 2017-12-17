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
            ds.setUsername("root"); // 여기 여기 ID이지만 root임으로 건드리지 않아도 됩니다.
            ds.setPassword("1234"); // 여기 여기 mysql에 접속할 때 입력한 Password를 입력해 주시면 됩니다.

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
