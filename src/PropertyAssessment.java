import java.math.BigDecimal;
import java.text.DecimalFormat;

/// Stores all data for a CSV entry, representing an entire property assessment.
public class PropertyAssessment implements Comparable<PropertyAssessment> {
    private final Integer accountNumber;
    private final Address address;
    private final Neighbourhood neighbourhood;
    private final Location location;
    private final BigDecimal assessedValue;
    private AssessmentClassInfo assessmentClassInfo;

    public PropertyAssessment(String line) {
        String[] fields = line.split(",");

        this.accountNumber = Integer.parseInt(fields[0]);
        this.address = new Address(fields[2], fields[3]);
        this.neighbourhood = new Neighbourhood(fields[6], fields[7]);
        this.location = new Location(fields[10], fields[9]);
        this.assessedValue = new BigDecimal(fields[8]);

        try {
            this.assessmentClassInfo = new AssessmentClassInfo(
                    fields[12], fields[13], fields[14],
                    fields[15], fields[16], fields[17]
            );
        } catch (Exception _) {}
    }

    public void printAssessmentInfo() {
        String info = String.format(
                """
                Account number = %d
                Address = %s
                Assessed value = %s
                Assessment class = %s
                Neighbourhood = %s
                Location = %s
                
                """,
                accountNumber,
                address,
                DecimalFormat.getCurrencyInstance().format(assessedValue),
                assessmentClassInfo,
                neighbourhood,
                location
        );

        System.out.print(info);
    }

    @Override
    public int compareTo(PropertyAssessment o) {
        return this.accountNumber.compareTo(o.accountNumber);
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAssessedValue() {
        return assessedValue;
    }

    public Neighbourhood getNeighbourhood() {
        return neighbourhood;
    }
}
