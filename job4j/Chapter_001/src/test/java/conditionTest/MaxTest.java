package conditionTest;

import org.junit.Test;
import ru.kibis.dataTypes.condition.Max;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
    @Test
    public void whenMax1To2Then2() {
        int result;
        result = Max.max(1, 2);
        assertThat(result, is(2));
    }
}

