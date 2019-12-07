import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.*;

//Test both StaffManagement and ManagerTools public methods here.
public class StaffManagementTest {

    //ManagerTools class parameters and tests *************************************************************************
    private String str1 = "AbcdE";
    private String str2 = "Brian P. O'Dell";
    private String str3 = "12345";
    private String str4 = "Andre3000";
    private String str5 = "Is this valid?";
    private String str6= "";

    //ManagerTools method tests
    @Test
    public void test_is_string_alphabet(){
        assertTrue(ManagerTools.is_string_name(str1));
        assertFalse(ManagerTools.is_string_alphabet(str2));
        assertFalse(ManagerTools.is_string_alphabet(str3));
        assertFalse(ManagerTools.is_string_alphabet(str4));
        assertFalse(ManagerTools.is_string_alphabet(str5));
        assertFalse(ManagerTools.is_string_alphabet(str6));
    }

    @Test
    public void test_is_string_name(){
        assertTrue(ManagerTools.is_string_name(str1));
        assertTrue(ManagerTools.is_string_name(str2));
        assertFalse(ManagerTools.is_string_name(str3));
        assertFalse(ManagerTools.is_string_name(str4));
        assertFalse(ManagerTools.is_string_name(str5));
        assertFalse(ManagerTools.is_string_name(str6));
    }

    @Test
    public void test_is_string_alphanumeric(){
        assertTrue(ManagerTools.is_string_alphanumeric(str1));
        assertTrue(ManagerTools.is_string_alphanumeric(str2));
        assertTrue(ManagerTools.is_string_alphanumeric(str3));
        assertTrue(ManagerTools.is_string_alphanumeric(str4));
        assertFalse(ManagerTools.is_string_alphanumeric(str5));
        assertFalse(ManagerTools.is_string_alphanumeric(str6));
    }

    //StaffManagement class parameters and tests **********************************************************************
    int valid_provider_id = 900002;
    int valid_member_id = 100001;
    int invalid_id = -1;


    @Test
    public void test_add_staff(){
        PersonData personData = new PersonData("Kevin Smith", "Apple St.", "Portland", "OR", "91234");
        MemberData memberData = new MemberData("Kevin Smith", "Apple St.", "Portland", "OR", "91234");
        ProviderData providerData = new ProviderData("Kevin Smith", "Apple St.", "Portland", "OR", "91234");
        StaffManagement staffManagement = new StaffManagement();

        assertEquals(-1, staffManagement.add_staff(personData));
        assertEquals(0, staffManagement.add_staff(memberData));
        assertEquals(0, staffManagement.add_staff(providerData));
    }

    @Test
    public void test_remove_staff(){
        StaffManagement staffManagement = new StaffManagement();
        assertEquals(0, staffManagement.remove_staff(StaffManagement.provider, valid_provider_id));
        assertEquals(0, staffManagement.remove_staff(StaffManagement.member, valid_member_id));
        assertEquals(-1, staffManagement.remove_staff(StaffManagement.provider, invalid_id));
        assertEquals(-1, staffManagement.remove_staff(StaffManagement.member, invalid_id));
    }

    @Test
    public void test_retrieve_staff(){

        StaffManagement staffManagement = new StaffManagement();
        try{
            assertNull(staffManagement.retrieve_staff("Invalid", invalid_id));
        }catch (SQLException e)
        {
            return;
        }
    }
}
