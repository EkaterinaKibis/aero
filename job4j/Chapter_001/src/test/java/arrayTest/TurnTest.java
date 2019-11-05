package arrayTest;

import org.junit.Test;
import ru.kibis.dataTypes.array.Turn;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TurnTest {
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        Turn turner = new Turn();
        int[] input = new int[] {4, 1, 6, 2};
        int[] result = turner.back(input);
        int[] expect = new int[] {2, 6, 1, 4};
        assertThat(result, is(expect));
    }
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray1() {
        Turn turner = new Turn();
        int[] input = new int[] {4, 1, 6};
        int[] result = turner.back(input);
        int[] expect = new int[] {6, 1, 4};
        assertThat(result, is(expect));
    }
}
