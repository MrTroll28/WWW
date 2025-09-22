package iuh.fit.kn.util;

import javax.sql.DataSource;
import java.sql.Connection;

public class DBUtil {
    private DataSource dataSource;
    public DBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws Exception {
        Connection con;
        try {
            con = dataSource.getConnection();
        } catch (Exception e) {
            throw new Exception("Error getting database connection: " + e.getMessage());
        }
        return con;
    }
}
