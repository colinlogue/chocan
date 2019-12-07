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
    int valid_member_add_id = 12;
    int invalid_id = -1;


    AddressData providerData = new AddressData("Orange Ave.", "Portland", "OR", "91234");
    AddressData memberData = new AddressData("304 Avenue Q", "Portland", "OR", "90809");

    @Test(expected = SQLException.class)
    public void test_retrieve() throws SQLException
    {
        assertEquals(providerData, AddressData.retrieve(valid_provider_add_id));
        assertEquals(memberData, AddressData.retrieve(valid_member_add_id));
        AddressData.retrieve(invalid_id);
    }

    @Test
    public void test_write() throws SQLException
    {
        AddressData addressData = new AddressData("Test St.", "Test Town", "WA", "11111");
        assertTrue(addressData.write());
    }
}
