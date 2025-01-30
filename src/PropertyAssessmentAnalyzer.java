import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public class PropertyAssessmentAnalyzer {

    public static void main(String[] args) {
        String csvFile = "src/Property_Assessment_Data_2024.csv";
        String line;
        String csvSplitBy = ",";

        int recordCount = 0;
        double lowestAssessedValue = Double.MAX_VALUE;
        double highestAssessedValue = Double.MIN_VALUE;
        Set<String> wards = new HashSet<>();
        Set<String> assessmentClasses = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String header = br.readLine();

            if (header != null) {
                while ((line = br.readLine()) != null) {
                    recordCount++;

                    String[] data = line.split(csvSplitBy, -1);

                    try {
                        if (!data[8].isEmpty()) {
                            double assessedValue = Double.parseDouble(data[8].trim());
                            // Skip properties with assessed value of 0
                            if (assessedValue > 0) {
                                lowestAssessedValue = Math.min(lowestAssessedValue, assessedValue);
                                highestAssessedValue = Math.max(highestAssessedValue, assessedValue);
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid assessed value on line " + (recordCount + 1));
                    }

                    // ward is in column 8 (index 7)
                    if (!data[7].isEmpty()) {
                        wards.add(data[7].trim());
                    }

                    // assessment classes are in columns 14 15 and 16 (indices 13 14 and 15)
                    for (int i = 13; i <= 15; i++) {
                        if (data.length > i && !data[i].isEmpty()) {
                            String value = data[i].trim();
                            if (!value.matches("\\d+")) {
                                assessmentClasses.add(value);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Total records in the dataset: " + recordCount);
        System.out.println("Lowest assessed value: " + formatLargeNumber(lowestAssessedValue));
        System.out.println("Highest assessed value: " + formatLargeNumber(highestAssessedValue));
        System.out.println("Number of wards in Edmonton: " + wards.size());
        System.out.println("Wards: " + wards);
        System.out.println("Property assessment classes in Edmonton: " + assessmentClasses);
    }

    /**
     * Formats a large number with commas and appropriate units (e.g., million, billion)
     */
    private static String formatLargeNumber(double value) {
        if (value >= 1_000_000_000) {
            return String.format("%.2f billion", value / 1_000_000_000);
        } else if (value >= 1_000_000) {
            return String.format("%.2f million", value / 1_000_000);
        } else {
            DecimalFormat formatter = new DecimalFormat("#,###");
            return formatter.format(value);
        }
    }
}
