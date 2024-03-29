import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceTests {

    @Before
    public void setup() {
        TestConfig.copy_db();
    }

    @After
    public void cleanup() {
        TestConfig.reset_db();
    }

    @Test
    public void fees_print_correctly() {
        Service s = new Service();
        s.fee = 1325;
        assertEquals(s.format_fee(), "$13.25");
    }


}
