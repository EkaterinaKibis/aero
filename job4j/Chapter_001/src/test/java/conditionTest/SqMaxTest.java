package conditionTest;

import org.junit.Test;

import ru.kibis.dataTypes.condition.SqMax;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SqMaxTest {
    @Test
    public void maxFirst (){
        int result;
        result = SqMax.max(10, 2,5,9);
        assertThat(result, is(10));
    }
    @Test
    public void maxSecond (){
        int result;
        result = SqMax.max(6, 10,5,9);
        assertThat(result, is(10));
    }
    @Test
    public void maxThird (){
        int result;
        result = SqMax.max(6, 3,10,9);
        assertThat(result, is(10));
    }
    @Test
    public void maxForth (){
        int result;
        result = SqMax.max(6, 3,9,10);
        assertThat(result, is(10));
    }
}

