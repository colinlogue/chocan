import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.sql.SQLException;

import static junit.framework.TestCase.*;
public class AddressDataTest
{
    @Before
    public void setup() {
        TestConfig.copy_db();
    }

    @After
    public void cleanup() {
        TestConfig.reset_db();
    }

    int valid_provider_add_id = 5;
    int valid_member_id = 4;
    int invalid_id = -1;


    AddressData providerData = new AddressData("221B Baker St.", "London", "WA", "98301");
    AddressData memberData = new AddressData("123 Fake St.", "Portland", "OR", "97214");

    @Test(expected = SQLException.class)
    public void test_retrieve_failure() throws SQLException
    {
        AddressData.retrieve(invalid_id);
    }

    @Test(expected = SQLException.class)
    public void test_retrieve_success() throws SQLException
    {
        AddressData.retrieve(invalid_id);
    }

    @Test
    public void test_write() throws SQLException
    {
        AddressData addressData = new AddressData("Test St.", "Test Town", "WA", "11111");
        assertTrue(addressData.write());
    }
}
