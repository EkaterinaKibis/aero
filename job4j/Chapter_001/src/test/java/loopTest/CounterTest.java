package loopTest;

import org.junit.Test;
import ru.kibis.dataTypes.loop.Counter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;




public class CounterTest {
    @Test
    public void test(){
        Counter one = new Counter();
        int result = one.add(1, 10);
        int expected = 30;
        assertThat(result, is(expected));
    }
}
