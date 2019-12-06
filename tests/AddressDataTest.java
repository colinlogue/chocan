import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.*;
public class AddressDataTest
{
    int valid_provider_id = 900002;
    int valid_member_id = 100001;
    int invalid_id = -1;


    @Test(expected = SQLException.class)
    public void test_retrieve() throws SQLException
    {
        AddressData providerData = new AddressData("221B Baker St.", "London", "WA", "98301");
        AddressData memberData = new AddressData("123 Fake St.", "Portland", "OR", "97214");

        try
        {
            assertEquals(memberData, AddressData.retrieve(valid_member_id));
            assertEquals(providerData, AddressData.retrieve(valid_provider_id));
            AddressData.retrieve(invalid_id);
        }catch (SQLException e)
        {
            throw e;
        }
    }

    @Test
    public void test_write()
    {
        AddressData addressData = new AddressData("Test St.", "Test Town", "WA", "11111");
        try{
            assertTrue(addressData.write());
        }catch(SQLException e) {
           return;
        }
    }
}
