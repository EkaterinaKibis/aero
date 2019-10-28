package loopTest;



import org.junit.Test;
import ru.kibis.dataTypes.loop.Fitness;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FitnessTest {
    @Test
    public void whenIvanGreatNik() {
        Fitness fit = new Fitness();
        int days = fit.calc(95, 90);
        assertThat(days, is(0));
    }

    @Test
    public void whenIvanLessByOneNik() {
        Fitness fit = new Fitness();
        int days = fit.calc(90, 95);
        assertThat(days, is(1));
    }

    @Test
    public void whenIvanLessByFewNik() {
        Fitness fit = new Fitness();
        int days = fit.calc(50, 90);
        assertThat(days, is(2));
    }
}
