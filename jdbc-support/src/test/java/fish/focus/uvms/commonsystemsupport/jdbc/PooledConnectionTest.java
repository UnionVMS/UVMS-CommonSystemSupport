package fish.focus.uvms.commonsystemsupport.jdbc;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class PooledConnectionTest {

    private static final Logger LOG = LoggerFactory.getLogger(PooledConnectionTest.class);

    @BeforeClass
    public static void beforeClass(){
        String DRIVER = "org.h2.Driver";
        String CONNECT_URL = "jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1";
        String UID = "sa";
        String PWD = "sa";
        PooledConnection.setup(DRIVER,CONNECT_URL,UID,PWD);
    }

    @AfterClass
    public static void afterClass(){
        PooledConnection.shutDown();
    }

    
    @Test
    public void createAConnectionTest(){

        Connection conn = null;
        try{
            conn = PooledConnection.open();
            Assert.assertTrue(conn != null);
        }
        catch(SQLException e){
            LOG.error(e.toString());
        }
        finally{
            PooledConnection.close(conn);
        }
    }

    @Test
    public void createSchemaTest(){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = PooledConnection.open();
            Assert.assertTrue(conn != null);


            stmt = conn.createStatement();
            int ok = stmt.executeUpdate("create schema  myschema;");
            Assert.assertTrue(ok == 0);


            DatabaseMetaData metaData = conn.getMetaData();
            rs = metaData.getSchemas();
            boolean foundSchema = false;
            while(rs.next()){
                String schemaName = rs.getString(1);
                if(schemaName.equalsIgnoreCase("MYSCHEMA")){
                    foundSchema = true;
                }
            }
            Assert.assertTrue(foundSchema);

            if(foundSchema){
                stmt.executeUpdate("drop schema  myschema;");
                foundSchema = false;
            }
            rs = metaData.getSchemas();
            while(rs.next()){
                String schemaName = rs.getString(1);
                if(schemaName.equalsIgnoreCase("MYSCHEMA")){
                    foundSchema = true;
                }
            }
            Assert.assertFalse(foundSchema);
        }
        catch(SQLException e){
            LOG.error(e.toString());
        }
        finally{
            PooledConnection.close(rs);
            PooledConnection.close(stmt);
            PooledConnection.close(conn);
        }
    }






}
