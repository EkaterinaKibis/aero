package loopTest;


import org.junit.Test;
import ru.kibis.dataTypes.loop.Factorial;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;




public class FactorialTest {
    @Test
    public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
        Factorial one = new Factorial();
        int result = one.calc(5);
        int expected = 120;
        assertThat(result, is(expected));
    }
    @Test
    public void testZeroToOne() {
        Factorial DOG = new Factorial();
        int result = DOG.calc(0);
        int expected = 1;
        assertThat(result, is(expected));
    }

}
