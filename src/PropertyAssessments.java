import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    public String getStatistics(String neighbourhood, Boolean formatted, String assessmentClass) {
            if (assessments.isEmpty()) {
                return "No assessments available.";
            }

            Stream<PropertyAssessment> assessmentStream = assessments.stream();
            List<Integer> values = new ArrayList<>();

            if (!neighbourhood.isBlank()) {
                values = assessmentStream.filter(p ->
                        neighbourhood.isBlank() || p.getNeighbourhood().name.equalsIgnoreCase(neighbourhood)
                ).map(PropertyAssessment::getAssessedValue).sorted().toList();

                return printStatistics(values, formatted, neighbourhood, assessmentClass);
            }

            if (!assessmentClass.isBlank()) {
                values = assessmentStream.filter(p ->
                        assessmentClass.isBlank() || p.isAssessmentClass(assessmentClass))
                .map(PropertyAssessment::getAssessedValue).sorted().toList();

                return printStatistics(values, formatted, neighbourhood, assessmentClass);
            }

            return printStatistics(values, formatted, neighbourhood, assessmentClass);
    }

    public String printStatistics(List<Integer> values, Boolean formatted, String neighbourhood, String assessmentClass) {
        int n = values.size();
        int min = values.getFirst();
        int max = values.get(n - 1);
        int range = max - min;
        int mean = (int) Math.round(values.stream().mapToInt(Integer::intValue).average().orElse(0));
        int median = (n % 2 == 0) ? (int) ((values.get(n / 2 - 1) + values.get(n / 2)) / 2.0) : values.get(n / 2);

        if (formatted) {
            if (neighbourhood.isBlank()) {
                return String.format(
                        """
                                There are %s %s properties in Edmonton
                                The min value is CAD %s
                                The max value is CAD %s
                                %n""",
                        formatter.format(n), assessmentClass, formatter.format(min), formatter.format(max)
                );
            }
            return String.format(
                    """
                            There are %s properties in %s
                            The mean value is CAD %s
                            The median value is CAD %s
                            %n""",
                    formatter.format(n), neighbourhood, formatter.format(mean), formatter.format(median)
            );
        } else {
           return String.format(
                   """
                           n = %d
                           min = $%s
                           max = $%s
                           range = $%s
                           mean = $%s
                           median = $%s
                           %n""",
                    n, formatter.format(min), formatter.format(max), formatter.format(range),
                    formatter.format(mean), formatter.format(median)
            );
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PropertyAssessment property : assessments) {
            sb.append(property.toString()).append("\n\n");
        }
        sb.append("\n").append(getStatistics("", false, ""));
        return sb.toString().trim();
    }
}
