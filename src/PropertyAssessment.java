import java.util.ArrayList;

public class PropertyAssessment {
    private final Integer accountNumber;
    private final Address address;
    private final Neighbourhood neighbourhood;
    private final Integer assessedValue;
    private final Location location;
    private final ArrayList<Integer> assessmentClassPercentages;
    private final ArrayList<String> assessmentClasses;

    // Making this 'private' enforces the use of the factory method for initialization
    private PropertyAssessment(Integer accountNumber, Integer houseNumber, String streetName,
                               String neighbourhood, String ward,
                               Integer assessedValue, String location,
                               ArrayList<Integer> assessmentClassPercentages, ArrayList<String> assessmentClasses) {
        this.accountNumber = accountNumber;
        this.address = new Address(houseNumber, streetName);
        this.neighbourhood = new Neighbourhood(neighbourhood, ward);
        this.assessedValue = assessedValue;
        this.location = parsePoint(location);
        this.assessmentClassPercentages = assessmentClassPercentages;
        this.assessmentClasses = assessmentClasses;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public Integer getAssessedValue() {
        return assessedValue;
    }

    public Neighbourhood getNeighbourhood() {
        return neighbourhood;
    }

    public Boolean isAssessmentClass(String className) {
        return assessmentClasses.contains(className.toUpperCase());
    }

    public static PropertyAssessment fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");

        Integer accountNumber = parseInteger(fields[0]);
        Integer houseNumber = parseInteger(fields[2]);
        String streetName = fields[3].trim();
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

        return new PropertyAssessment(accountNumber, houseNumber, streetName,
                neighbourhood, ward, assessedValue, location, assessmentClassPercentages, assessmentClasses);
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

    private static Location parsePoint(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Point value is null!");
        }

        try {
            String[] parts = value.trim().split(",");
            String xString = parts[0].trim().substring(1);
            String yString = parts[1].trim().substring(0, parts[1].trim().length() - 1);

            Double[] coordinates = new Double[2];
            coordinates[0] = parseDouble(xString);
            coordinates[1] = parseDouble(yString);

            return new Location(coordinates[1], coordinates[0]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse POINT string: " + value, e);
        }
    }

    @Override
    public String toString() {
        ArrayList<String> result_list = new ArrayList<>();
        result_list.add("Account number = " + accountNumber);
        result_list.add("Address = " + address);
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

        result_list.add("Neighbourhood = " + neighbourhood);
        result_list.add("Location = " + location);

        return String.join("\n", result_list);
    }
}
