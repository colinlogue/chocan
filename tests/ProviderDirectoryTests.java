import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class ProviderDirectoryTests {


    @Before
    public void setup() {
        TestConfig.copy_db();
    }

    @After
    public void cleanup() {
        TestConfig.reset_db();
    }

    @Test
    public void lookup_test() throws SQLException {
        ProviderDirectory.lookup(102010);
    }

}
