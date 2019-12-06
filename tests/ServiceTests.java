import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceTests {

    @Test
    public void fees_print_correctly() {
        Service s = new Service();
        s.fee = 1325;
        assertEquals(s.format_fee(), "$13.25");
    }


}
