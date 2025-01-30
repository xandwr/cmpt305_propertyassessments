import java.util.ArrayList;

public class PropertyAssessment {
    private final Integer accountNumber;
    private final Integer suite;
    private final Integer houseNumber;
    private final String streetName;
    private final Boolean hasGarage;
    private final Integer neighbourhoodId;
    private final String neighbourhood;
    private final String ward;
    private final Integer assessedValue;
    private final Double latitude;
    private final Double longitude;
    private final String location;
    private ArrayList<Integer> assessmentClassPercentages = new ArrayList<>();
    private ArrayList<String> assessmentClasses = new ArrayList<>();

    // Making this 'private' enforces the use of the factory method for initialization
    private PropertyAssessment(Integer accountNumber, Integer suite, Integer houseNumber, String streetName,
                               Boolean hasGarage, Integer neighbourhoodId, String neighbourhood, String ward,
                               Integer assessedValue, Double latitude, Double longitude, String location,
                               ArrayList<Integer> assessmentClassPercentages, ArrayList<String> assessmentClasses) {
        this.accountNumber = accountNumber;
        this.suite = suite;
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.hasGarage = hasGarage;
        this.neighbourhoodId = neighbourhoodId;
        this.neighbourhood = neighbourhood;
        this.ward = ward;
        this.assessedValue = assessedValue;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.assessmentClassPercentages = assessmentClassPercentages;
        this.assessmentClasses = assessmentClasses;
    }

    public static PropertyAssessment fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");

        Integer accountNumber = parseInteger(fields[0]);
        Integer suite = parseInteger(fields[1]);
        Integer houseNumber = parseInteger(fields[2]);
        String streetName = fields[3].trim();
        Boolean hasGarage = fields[4].trim().equalsIgnoreCase("Y");
        Integer neighbourhoodId = parseInteger(fields[5]);
        String neighbourhood = fields[6].trim();
        String ward = fields[7].trim();
        Integer assessedValue = parseInteger(fields[8]);
        Double latitude = parseDouble(fields[9]);
        Double longitude = parseDouble(fields[10]);
        String location = String.format("(%.14f, %.14f)", latitude, longitude);

        ArrayList<Integer> assessmentClassPercentages = new ArrayList<>();
        for (int i = 12; i <= 14; i++) {
            Integer percentage = parseInteger(fields[i]);
            assessmentClassPercentages.add(percentage != null ? percentage : 0);
        }

        ArrayList<String> assessmentClasses = new ArrayList<>();
        for (int i = 15; i <= 17; i++) {
            if (i < fields.length && !fields[i].trim().isEmpty()) {
                assessmentClasses.add(fields[i].trim());
            }
        }

        return new PropertyAssessment(accountNumber, suite, houseNumber, streetName, hasGarage, neighbourhoodId,
                neighbourhood, ward, assessedValue, latitude, longitude, location, assessmentClassPercentages, assessmentClasses);
    }

    private static Integer parseInteger(String value) {
        try {
            return value.trim().isEmpty() ? null : Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Double parseDouble(String value) {
        try {
            return value.trim().isEmpty() ? null : Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        ArrayList<String> result_list = new ArrayList<>();
        result_list.add("Account number = " + accountNumber);
        result_list.add("Address = " + (houseNumber != null ? houseNumber + " " : "") + streetName);
        result_list.add("Assessed value = " + assessedValue);

        if (!assessmentClasses.isEmpty()) {
            StringBuilder assessmentString = new StringBuilder();
            for (int i = 0; i < assessmentClasses.size(); i++) {
                String className = assessmentClasses.get(i);
                String percentage = (i < assessmentClassPercentages.size()) ? assessmentClassPercentages.get(i) + "%" : "0%";
                assessmentString.append(className).append(" (").append(percentage).append("), ");
            }
            result_list.add("Assessment class = [" + assessmentString.substring(0, assessmentString.length() - 2) + "]");
        } else {
            result_list.add("Assessment class = None");
        }

        result_list.add("Neighbourhood = " + neighbourhood + " (" + ward + ")");
        result_list.add("Location = " + location);

        return String.join("\n", result_list);
    }
}
