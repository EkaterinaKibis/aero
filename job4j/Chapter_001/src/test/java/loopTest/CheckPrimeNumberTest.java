package loopTest;


import org.junit.Test;
import ru.kibis.dataTypes.loop.CheckPrimeNumber;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CheckPrimeNumberTest {
    @Test
    public void when5() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(17);
        assertThat(rsl, is(true));
    }

    @Test
    public void when4() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(8);
        assertThat(rsl, is(false));
    }
}
