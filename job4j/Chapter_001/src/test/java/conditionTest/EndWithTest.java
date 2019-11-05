package conditionTest;

import org.junit.Test;
import ru.kibis.dataTypes.condition.EndWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EndWithTest {

    @Test
    public void whenStartWithPrefixThenTrue() {
        char[] word = {'H', 'e', 'l', 'l', 'o'};
        char[] post = {'l', 'o'};
        boolean result = EndWith.endsWith(word, post);
        assertThat(result, is(true));
    }

    @Test
    public void whenNotStartWithPrefixThenFalse() {
        char[] word = {'H', 'e', 'l', 'l', 'o'};
        char[] post = {'l', 'a'};
        boolean result = EndWith.endsWith(word, post);
        assertThat(result, is(false));
    }
}