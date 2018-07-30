import static org.junit.Assert.assertEquals;

// import org.junit.Assert.assertEquals;
import com.momentum.strategies.Strategy;
import com.momentum.strategies.TwoMV;

public class TestStrategy {
    public static void main(String[] args) {
        //arrange
        Strategy twoMv = new TwoMV("2mv", "aapl", 1000);

        //act
        String results = twoMv.watch();
        System.out.println(results+" should be filled");
        //assert
        //idk this isnt working Assert.assertEquals(results, "filled");

    }

}
