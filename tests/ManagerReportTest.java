import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import java.sql.SQLException;
import static junit.framework.TestCase.*;

public class ManagerReportTest
{

    @Test(expected = SQLException.class)
    public void test_provider_retrieve_failure() throws SQLException{
        int invalid_id = -1;
        ProviderData.retrieve(invalid_id);
    }

    @Test(expected = SQLException.class)
    public void test_member_retrieve_failure() throws SQLException{
        int invalid_id = -1;
        MemberData.retrieve(invalid_id);
    }

    @Test
    public void test_retrieve_provider(){
        int valid_provider_add_id = 5;
        ProviderData providerData = new ProviderData("","Orange Ave.", "Portland", "OR", "91234");
        ProviderData other_provider;

        try{
            other_provider = ProviderData.retrieve(valid_provider_add_id);
        } catch(SQLException e){
            return;
        }

        assertEquals(other_provider.name, providerData.name);
        assertEquals(other_provider.address.street, providerData.address.street);
        assertEquals(other_provider.address.city, providerData.address.city);
        assertEquals(other_provider.address.state, providerData.address.state);
        assertEquals(other_provider.address.ZIP, providerData.address.ZIP);
    }

    @Test
    public void test_retrieve_member(){
        int valid_member_add_id = 12;
        MemberData memberData = new MemberData("Colin Logue","304 Avenue Q", "Portland", "TX", "90809");
        MemberData other_member;

        try{
            other_member = MemberData.retrieve(valid_member_add_id);
        } catch(SQLException e){
            return;
        }

        assertEquals(other_member.name, memberData.name);
        assertEquals(other_member.address.street, memberData.address.street);
        assertEquals(other_member.address.city, memberData.address.city);
        assertEquals(other_member.address.state, memberData.address.state);
        assertEquals(other_member.address.ZIP, memberData.address.ZIP);
    }
}
