package arrayTest;

import org.junit.Test;
import ru.kibis.dataTypes.array.Check;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CheckTest {
    @Test
    public void whenDataMonoByTrueThenTrue() {
        Check check = new Check();
        boolean[] input = new boolean[]{true, true, true};
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    @Test
    public void whenDataNotMonoByTrueThenFalse() {
        Check check = new Check();
        boolean[] input = new boolean[]{false, false, true};
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }

    @Test
    public void whenDataNotMonoByTrueThenFalseFalse() {
        Check check = new Check();
        boolean[] input = new boolean[]{false, false, true, false};
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }
}
