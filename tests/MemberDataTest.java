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

    int valid_member_id = 100001;
    int v_m_id = 100002;
    int invalid_member_id = 999;
    int i_m_id = 333;

    MemberData m_data = new MemberData("Marge Simpson", "345 Evergreen Terrace", "Springfield", "IL");

 

    @Test
    public void test_retrieve_failure() throws SQLException
    {
        MemberData memberData = new MemberData();
        try{
            assertNull(memberData.retrieve(invalid_member_id));
        }catch (SQLException e){
            return;
        }
    }

    @Test
    public void test_retrieve_success() throws SQLException
    {
        MemberData memberData = new MemberData();
        try{
            assertNotNull(memberData.retrieve(valid_member_id));
        }catch (SQLException e){
            return;
        }
    }

    @Test
    public void test_write() throws SQLException
    {
        assertTrue(m_data.write());
    }

    @Test
    public void test_delete() throws SQLException
    {
        assertTrue(MemberData.delete(valid_member_id));
        assertTrue(MemberData.delete(v_m_id));
        assertFalse(MemberData.delete(invalid_member_id));
        assertFalse(MemberData.delete(i_m_id));
    }
}