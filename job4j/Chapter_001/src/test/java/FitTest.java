import org.junit.Assert;
import org.junit.Test;
import ru.kibis.dataTypes.fit.Fit;

public class FitTest {
    @Test
    public void manWeight() {
        Fit man = new Fit();
        double expected = 92;
        double out = Fit.menweight(180);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void manWeightFail() {
        Fit man = new Fit();
        double expected = 192;
        double out = Fit.menweight(180);
        Assert.assertNotEquals(expected, out, 0.01);
    }
    @Test
    public void woumanWeight() {
        Fit wouman = new Fit();
        double expected = 46;
        double out = Fit.woumenweight(150);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void woumanWeightFail() {
        Fit wouman = new Fit();
        double expected = 49;
        double out = Fit.woumenweight(150);
        Assert.assertNotEquals(expected, out, 0.01);
    }

}