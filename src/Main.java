public class Main {
    public static void main(String[] args)
    {
        PropertyAssessments assessments = new PropertyAssessments("data.csv");
        assessments.getGlobalStats().printStatistics("Descriptive statistics of all property assessments");

        int accountNumber = 1103530;
        PropertyAssessment currentAssessment = assessments.getPropertyByAccountNumber(accountNumber);
        currentAssessment.printAssessmentInfo();

        String neighbourhoodString = "Downtown";
        Statistics neighbourhoodStats = assessments.getStatisticsForNeighbourhood(neighbourhoodString);
        neighbourhoodStats.printStatistics(String.format("Statistics (neighbourhood = %s)", neighbourhoodString));
    }
}
