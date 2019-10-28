package conditionTest;

import org.junit.Test;
import ru.kibis.dataTypes.condition.MultiMax;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

public class MultiMaxTest {
    @Test
    public void whenSecondMax() {
        MultiMax check = new MultiMax();
        int result = check.max(1, 4, 2);
        assertThat(result, is(4));
    }

    @Test
    public void whenThirdMax() {
        MultiMax check = new MultiMax();
        int result = check.max(1, 4, 10);
        assertThat(result, is(10));
    }
}
