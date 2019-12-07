import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

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
        Service s = ProviderDirectory.lookup(102010);
        assertEquals(s.label, "Chocolate Detox");
        assertEquals(s.fee, 1425);
        assertEquals(s.service_code, 102010);
    }

    @Test(expected = SQLException.class)
    public void lookup_invalid() throws SQLException {
        ProviderDirectory.lookup(9000);
    }

}
