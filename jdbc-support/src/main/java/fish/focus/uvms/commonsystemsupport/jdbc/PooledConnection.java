package fish.focus.uvms.commonsystemsupport.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class PooledConnection {

    private static DataSource dataSource = null;

    /** This method ca only be called once. At server/program startup
     * @param driver
     * @param connectURI
     * @param uid
     * @param pwd
     */
    public static void setup(String driver, String connectURI, String uid, String pwd) {

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUsername(uid);
        ds.setPassword(pwd);
        ds.setUrl(connectURI);
        ds.setDefaultAutoCommit(true);
        dataSource = (DataSource) ds;
    }

    /**  this method can only be called once. At server/program shutdown
     *
     */
    public static void shutDown() {
        if (dataSource != null) {
            BasicDataSource bds = (BasicDataSource) dataSource;
            try {
                bds.close();
                dataSource = null;
            } catch (SQLException e) {
            }
        }

    }

    /**
     * @return
     */
    public static boolean isStarted() {
        return dataSource != null;
    }

    /**
     *
     */
    public static void printDataSourceStats() {
        if (dataSource != null) {
            BasicDataSource bds = (BasicDataSource) dataSource;
            System.out.println("NumActive: " + bds.getNumActive());
            System.out.println("NumIdle: " + bds.getNumIdle());
        }

    }

    /**
     * @return
     * @throws SQLException
     */
    public static Connection open() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * @param conn
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // don't care
            }
        }
    }

    /**
     * @param stmt
     */
    public static void close(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // don't care
            }
        }
    }

    /**
     * @param stmt
     */
    public static void close(CallableStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // don't care
            }
        }
    }

    /**
     * @param rs
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // don't care
            }
        }
    }
}