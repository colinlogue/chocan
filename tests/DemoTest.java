
import org.junit.Test;

//Comment line below then perss Alt-Enter on assertFalse in testing method below
//Select "TestCase.asserFalse(blahblahblah)" to automatically add it up here
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

//NOTE: Unit test can only be done for public methods. I looked into how to test private methods, but
//it requires external plugins and seems very complicated. If there is an easier way to do it please let me know.
//Either don't test private methods or make them public to test them.

public class DemoTest {

    //Put this keyword before every test method
    //There are other flags that use @, but I don't know what they do
    //You can type "@" anywhere in this class with an IDE to see them.
    @Test
    public void test_isStringAlphabet(){
        //Create  new objects or data fields to test
        String str1 = "Abcde";
        String str2 = "Brian P. O'Dell";
        String str3 = "12345";
        String str4 = "Andre3000";

        //Make sure to assert your methods to test them.
        //Since isStingAlphabet returns a boolean I'm using assertFalse/True
        //str1 and str2 should return true so I assert true.
        //If they do not work as intended the tests should fail.
        //There are other assert Methods such as assertEquals, assertNull, etc.
        assertTrue(ManagerTools.isStringAlphabet(str1));
        assertTrue(ManagerTools.isStringAlphabet(str2));
        assertFalse(ManagerTools.isStringAlphabet(str3));
        assertFalse(ManagerTools.isStringAlphabet(str4));

        //If you type "assert" in an IDE inside this method other assert methods should show up
    }
}


