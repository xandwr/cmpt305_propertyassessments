import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PropertyAssessments {
    private final List<PropertyAssessment> assessments;
    private static final DecimalFormat formatter = new DecimalFormat("#,###");

    public PropertyAssessments(String filename) {
        this.assessments = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            lines.removeFirst();
            for (String line : lines) {
                this.addProperty(PropertyAssessment.fromCSV(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }

    public void addProperty(PropertyAssessment property) {
        assessments.add(property);
    }

    public PropertyAssessment findProperty(int accountNumber) {
        for (PropertyAssessment p : assessments) {
            if (p.getAccountNumber().equals(accountNumber)) {
                return p;
            }
        }
        return null;
    }

    public String getStatistics(String neighbourhood) {
            if (assessments.isEmpty()) {
                return "No assessments available.";
            }

            List<Integer> values = assessments.stream()
                .filter(p -> neighbourhood == null || neighbourhood.isBlank() || p.getNeighbourhood().equalsIgnoreCase(neighbourhood))
                .map(PropertyAssessment::getAssessedValue)
                .sorted()
                .toList();

            int n = values.size();
            int min = values.getFirst();
            int max = values.get(n - 1);
            int range = max - min;
            double mean = Math.round(values.stream().mapToInt(Integer::intValue).average().orElse(0));
            double median = (n % 2 == 0) ? (values.get(n / 2 - 1) + values.get(n / 2)) / 2.0 : values.get(n / 2);

            return String.format(
                    """
                    n = %d
                    min = $%s
                    max = $%s
                    range = $%s
                    mean = $%s
                    median = $%s
                    """,
                n, formatter.format(min), formatter.format(max), formatter.format(range),
                formatter.format((int) mean), formatter.format((int) median)
            );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PropertyAssessment property : assessments) {
            sb.append(property.toString()).append("\n\n");
        }
        sb.append("\n").append(getStatistics(""));
        return sb.toString().trim();
    }
}
