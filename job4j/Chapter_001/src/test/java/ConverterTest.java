import org.junit.Assert;
import org.junit.Test;
import ru.kibis.dataTypes.converter.Converter;

public class ConverterTest {

    @Test
    public void rubleToEuro() {
        Converter rubletoevro = new Converter();
        int in = 140;
        int expected = 2;
        int out = Converter.rubletoevro(in);
        Assert.assertEquals(expected, out);
    }


    @Test
    public void rubleToDollar() {
        Converter rubletodollar = new Converter();
        int in = 120;
        int expected = 2;
        int out = Converter.rubletodollar(in);
        Assert.assertEquals(expected, out);
    }


    @Test
    public void DollarToRuble() {
        Converter dollartoruble = new Converter();
        int in = 2;
        int expected = 120;
        int out = Converter.dollartoruble(in);
        Assert.assertEquals(expected, out);
    }



    @Test
    public void EuroToRuble() {
        Converter evrotoruble = new Converter();
        int in = 2;
        int expected = 140;
        int out = Converter.evrotoruble(in);
        Assert.assertEquals(expected, out);
    }

}
