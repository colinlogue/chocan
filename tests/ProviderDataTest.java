import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.sql.SQLException;

import static junit.framework.TestCase.*;

public class ProviderDataTest
{
    @Before
    public void setup() {
        TestConfig.copy_db();
    }

    @After
    public void cleanup() {
        TestConfig.reset_db();
    }

    int valid_provider_id = 900001;
    int v_id_1 = 900003;
    int invalid_provider_id = 999;
    int i_id_1 = 555;
    int i_id_2 = 444;
    ProviderData p_data = new ProviderData("Dr Bill", "333 Evergreen Terrace", "Springfield", "IL", "91234");


    @Test
    public void test_retrieve_failure() throws SQLException
    {
        ProviderData providerData = new ProviderData();
        try{
            assertNull(providerData.retrieve(invalid_provider_id));
        }catch (SQLException e){
            return;
        }
    }

    @Test
    public void test_retrieve_success() throws SQLException
    {
        ProviderData providerData = new ProviderData();
        try{
            assertNotNull(providerData.retrieve(valid_provider_id));
        }catch (SQLException e){
            return;
        }
    }

    @Test
    public void test_write() throws SQLException
    {
        assertTrue(p_data.write());
    }

    @Test
    public void test_delete() throws SQLException
    {
        assertTrue(ProviderData.delete(valid_provider_id));
        assertTrue(ProviderData.delete(v_id_1));
        assertFalse(ProviderData.delete(invalid_provider_id));
        assertFalse(ProviderData.delete(i_id_1));
    }

}
