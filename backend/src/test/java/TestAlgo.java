import com.momentum.algo.AlgoBollingerBands;
import com.momentum.algo.AlgoTwoMovingAverages;
import com.momentum.algo.SMA;
import com.momentum.algo.SMAWithSD;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestAlgo {

    @Test
    public void test1() {

        AlgoTwoMovingAverages test = new AlgoTwoMovingAverages();

        test.setProfit(100.0);
        test.setInitialPrice(10.0);

        assertTrue(test.exitCondition(1.0));
    }

    @Test
    public void test2() {

        AlgoBollingerBands test = new AlgoBollingerBands();

        test.setProfit((100.0));
        test.setInitialPrice(10.0);

        assertTrue(test.exitCondition(1.0));
    }

    @Test
    public void test3() {

        AlgoTwoMovingAverages test = new AlgoTwoMovingAverages();

        test.setProfit(10.0);
        test.setInitialPrice(100.0);

        assertFalse(test.exitCondition(1.0));
    }

    @Test
    public void test4() {

        AlgoBollingerBands test = new AlgoBollingerBands();

        test.setProfit((10.0));
        test.setInitialPrice(100.0);

        assertFalse(test.exitCondition(1.0));
    }

    /*
    @Test
    public void test5() {

        AlgoTwoMovingAverages test = new AlgoTwoMovingAverages();
        SMA short = mock(SMA.class);

        test.setShortSMAIsLower(true);

        assertTrue(test.hasCrossed("Buy"));
    }
    */
}
