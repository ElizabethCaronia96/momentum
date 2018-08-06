import com.momentum.algo.AlgoBollingerBands;
import com.momentum.algo.AlgoTwoMovingAverages;
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
        test.setInitialPrice(1000.0);

        assertTrue(!test.exitCondition(1.0));
    }

    @Test
    public void test2() {

        AlgoBollingerBands test = new AlgoBollingerBands();

        test.setProfit((100.0));
        test.setInitialPrice(10.0);

        assertTrue(test.exitCondition(1.0));
    }
}
