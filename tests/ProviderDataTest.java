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

