package fish.focus.uvms.commonsystemsupport.jdbc;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class PooledConnectionTest {

    @BeforeClass
    public static void beforeClass(){
        String DRIVER = "";
        String CONNECT_URL = "";
        String UID = "";
        String PWD = "";
        PooledConnection.setup(DRIVER,CONNECT_URL,UID,PWD);
    }

    @AfterClass
    public static void afterClass(){
        PooledConnection.shutDown();
    }

    
    @Test
    public void getAConnectionTest(){

        Connection conn = null;
        try{
            conn = PooledConnection.open();
            Assert.assertTrue(conn != null);
        }
        catch(SQLException e){

        }
        finally{
            PooledConnection.close(conn);
        }
    }

}
