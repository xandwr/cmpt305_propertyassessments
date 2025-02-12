import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PropertyAssessments {
    private final List<PropertyAssessment> assessments;
    private final Statistics globalStats;

    public PropertyAssessments(String assessmentsCSV) {
        this.assessments = Collections.unmodifiableList(populateAssessments(assessmentsCSV));
        this.globalStats = new Statistics(this);
    }

    public PropertyAssessments(List<PropertyAssessment> assessments) {
        this.assessments = List.copyOf(assessments);
        this.globalStats = new Statistics(this);
    }

    private List<PropertyAssessment> populateAssessments(String assessmentsCSV) {
        List<PropertyAssessment> assessments = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(assessmentsCSV))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                assessments.add(new PropertyAssessment(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + assessmentsCSV, e);
        }
        return assessments;
    }

    public int getSize() {
        return assessments.size();
    }

    public BigDecimal getMin() {
        return getAssessedValues().stream().min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getMax() {
        return getAssessedValues().stream().max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getRange() {
        return getMax().subtract(getMin());
    }

    public BigDecimal getMean() {
        BigDecimal sum = getAssessedValues().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(new BigDecimal(assessments.size()), RoundingMode.HALF_UP);
    }

    public BigDecimal getMedian() {
        List<BigDecimal> sortedValues = getAssessedValues();
        Collections.sort(sortedValues);
        int size = sortedValues.size();
        if (size % 2 == 1) {
            return sortedValues.get(size / 2);
        } else {
            return sortedValues.get(size / 2 - 1).add(sortedValues.get(size / 2)).divide(new BigDecimal(2), RoundingMode.HALF_UP);
        }
    }

    private List<BigDecimal> getAssessedValues() {
        List<BigDecimal> values = new ArrayList<>();
        for (PropertyAssessment assessment : assessments) {
            values.add(assessment.getAssessedValue());
        }
        return values;
    }

    public Statistics getStatisticsForNeighbourhood(String neighbourhood) {
        if (neighbourhood == null || neighbourhood.trim().isEmpty()) {
            throw new IllegalArgumentException("Neighbourhood name cannot be null or empty");
        }

        List<PropertyAssessment> matchingAssessments = assessments.stream()
                .filter(assessment -> assessment.getNeighbourhood().name.equalsIgnoreCase(neighbourhood))
                .toList();

        if (matchingAssessments.isEmpty()) {
            throw new IllegalArgumentException("No assessments found for neighbourhood: " + neighbourhood);
        }

        return new Statistics(new PropertyAssessments(matchingAssessments));
    }

    public Statistics getGlobalStats() {
        return globalStats;
    }

    public PropertyAssessment getPropertyByAccountNumber(int accountNumber) {
        return assessments.stream()
                .filter(assessment -> assessment.getAccountNumber() == accountNumber)
                .findFirst()
                .orElse(null);
    }
}