package conditionTest;

import org.junit.Test;
import ru.kibis.dataTypes.condition.Triangle;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void whenExist() {
        boolean result = Triangle.exist(2.0, 2.0, 2.0);
        assertThat(result, is(true));
    }
    @Test
    public void whenNotExists(){
        boolean result = Triangle.exist(1,1,10);
        assertThat(result, is(false));
    }
}
