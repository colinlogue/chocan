import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.*;
public class AddressDataTest
{
    int valid_provider_id = 900002;
    int valid_member_id = 100001;
    int invalid_id = -1;

    ProviderData providerData = new ProviderData("Scooby Doo", "221B Baker St.", "London", "WA", "98301");
    MemberData memberData = new MemberData("Aaron A. Aaronson", "123 Fake St.", "Portland", "OR", "97214");

    @Test(expected = SQLException.class)
    public void test_retrieve()
    {
        AddressData providerData = new AddressData("221B Baker St.", "London", "WA", "98301");
        AddressData memberData = new AddressData("123 Fake St.", "Portland", "OR", "97214");
        try
        {
            assertNull(AddressData.retrieve(invalid_id));
            assertEquals(memberData, AddressData.retrieve(valid_member_id));
            assertEquals(providerData, AddressData.retrieve(valid_provider_id));
        }catch (SQLException e)
        {
            return;
        }
    }
}
