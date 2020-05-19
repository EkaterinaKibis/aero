import oop.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class TesTest {
    @org.junit.Test
    public void testmeth() {
        String url = new Test().autorisation("????");
        assertEquals(url, "https://myaccount.google.com/?utm_source=sign_in_no_continue&pli=1");
    }
}
