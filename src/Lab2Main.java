public class Lab2Main {
    public static void main(String[] args) {
        PropertyAssessments propertyAssessments = new PropertyAssessments("src/Property_Assessment_Data_2024.csv");

        System.out.println("Descriptive statistics for property assessments: ");
        System.out.println(propertyAssessments.getStatistics("", false, ""));

        int searchAccountNumber = 1103530;
        PropertyAssessment foundAssessment = propertyAssessments.findProperty(searchAccountNumber);
        if (foundAssessment != null) {
            System.out.println("Find a property assessment by account number: " + searchAccountNumber);
            System.out.println(foundAssessment);
        } else {
            System.out.println("\nProperty with account number " + searchAccountNumber + " not found.");
        }

        String searchNeighbourhood = "downtown";
        System.out.println("\nNeighbourhood: " + searchNeighbourhood);
        System.out.println(propertyAssessments.getStatistics(searchNeighbourhood, false, ""));
    }
}
