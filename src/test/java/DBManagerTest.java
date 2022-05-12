import DB.ConnectionPool;
import DB.DBManager;
import DB.entity.Route;
import DB.entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DBManagerTest {
    @Test
    public void testFindUser() throws Exception {

       User user = new User();
       user.setId(0L);
       user.setRole("manager");

        User secondUser = new User();
        secondUser.setId(1L);
        secondUser.setRole("client");

        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).
                thenReturn(true).
                thenReturn(true).
                thenReturn(false);

        when(resultSet.getLong(0))
                .thenReturn(0L)
                .thenReturn(1L);

        when(resultSet.getString(7))
                .thenReturn("manager")
                .thenReturn("client");





        Assert.assertEquals(resultSet.getLong(0),user.getId());
        Assert.assertEquals(resultSet.getString(7),user.getRole());
        Assert.assertEquals(resultSet.getLong(0),secondUser.getId());
        Assert.assertEquals(resultSet.getString(7),secondUser.getRole());

    }

}
