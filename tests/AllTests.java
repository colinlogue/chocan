import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        AddressData.class,
        ManagerReportsTest.class,
        ProviderDataTest.class,
        ProviderDirectoryTests.class,
        ServiceTests.class,
        StaffManagementTest.class,
})

public class AllTests
{

}
