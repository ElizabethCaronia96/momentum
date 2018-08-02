import com.momentum.algo.AlgoTwoMovingAverages;
import com.momentum.rest.entities.TwoMA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestTwoMA{
    @Parameterized.Parameter
    public int fInput;
    @Parameterized.Parameter(1)
    public int fExpected;

    public TestTwoMA() {


    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{1, 2}, {}};
        return Arrays.asList(data);
    }

    @Test
    public void canRun(){
        assertEquals(fExpected, fInput);


    }
}