public class Lab3Main {
    public static void main(String[] args) {
        System.out.println("Please enter the CSV filename: Property_Assessment_Data_2024.csv");
        PropertyAssessments propertyAssessments = new PropertyAssessments("src/Property_Assessment_Data_2024.csv");

        String testNeighbourhoodName = "Oliver";
        System.out.println("Please enter a neighbourhood name: " + testNeighbourhoodName);
        System.out.println(propertyAssessments.getStatistics(testNeighbourhoodName, true, ""));

        String testAssessmentClass = "Residential";
        System.out.println("Please enter an assessment class: " + testAssessmentClass);
        System.out.println(propertyAssessments.getStatistics("", true, testAssessmentClass));
    }
}
