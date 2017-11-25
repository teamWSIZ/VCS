import org.apache.commons.math3.distribution.BetaDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.WeibullDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class TestDistributionsStatistics {
    public static void main(String[] args) throws Exception {

        //Rozkład gaussa
        NormalDistribution normalDistribution = new NormalDistribution(0, 1);
        for (int i = 0; i < 10; i++) {
            double randomValue = normalDistribution.sample();
            System.out.println(randomValue);
        }

        //Rozkład Weibulla
        //alpha == k
        //beta == lambda
        //https://en.wikipedia.org/wiki/Weibull_distribution
        //http://mathworld.wolfram.com/WeibullDistribution.html
        WeibullDistribution weibullDistribution = new WeibullDistribution(2, 1);    //k=5, beta=1
        for (int i = 0; i < 100; i++) {
            double val = weibullDistribution.sample();
            System.out.println(val);
        }


        BetaDistribution betaDistribution = new BetaDistribution(2, 1);    //k=5, beta=1
        for (int i = 0; i < 100; i++) {
            double val = weibullDistribution.sample();
            System.out.println(val);
        }



    }
}
