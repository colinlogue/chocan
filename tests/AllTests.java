import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        AddressData.class,
        ProviderDataTest.class,
        ProviderDirectoryTests.class,
        ServiceTests.class,
        StaffManagementTest.class,
        ManagerReportTest.class
})

public class AllTests
{

}
