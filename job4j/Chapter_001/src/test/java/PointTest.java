import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.kibis.dataTypes.condition.Point;

public class PointTest {

    @Test
    public void Pointtest() {
        int expected = 2;
        int out = (int) Point.distance(1,2,3,4);
        Assert.assertEquals(expected, out);
    }
}
