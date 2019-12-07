import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import java.sql.SQLException;

public class ManagerReportTest
{
    ProviderData providerData = new ProviderData("","Orange Ave.", "Portland", "OR", "91234");
    int valid_provider_add_id = 5;

    @Test
    public void test_retrieve_member(){
        int valid_member_add_id = 12;
        MemberData memberData = new MemberData("Colin Logue","304 Avenue Q", "Portland", "OR", "90809");
        MemberData other_member;

        try{
            other_member = MemberData.retrieve(valid_member_add_id);
        } catch(SQLException e){
            return;
        }

    }
}
