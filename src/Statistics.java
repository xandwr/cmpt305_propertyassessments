import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/// Tracks and manages individual statistics for given entries.
public class Statistics {
    private final int n;
    private final BigDecimal min;
    private final BigDecimal max;
    private final BigDecimal range;
    private final BigDecimal mean;
    private final BigDecimal median;

    public Statistics(PropertyAssessments assessments) {
        this.n = assessments.getSize();
        this.min = assessments.getMin();
        this.max = assessments.getMax();
        this.range = assessments.getRange();
        this.mean = assessments.getMean();
        this.median = assessments.getMedian();
    }

    public void printStatistics(String displayMessage) {
        NumberFormat df = DecimalFormat.getCurrencyInstance();
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMaximumFractionDigits(0);
        System.out.println(displayMessage);
        System.out.println("n = " + n);
        System.out.println("min = " + df.format(min));
        System.out.println("max = " + df.format(max));
        System.out.println("range = " + df.format(range));
        System.out.println("mean = " + df.format(mean));
        System.out.println("median = " + df.format(median));
        System.out.println();
    }

    public BigDecimal getMax() {
        return max;
    }
}