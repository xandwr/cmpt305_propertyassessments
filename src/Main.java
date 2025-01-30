public class Main {
    public static void main(String[] args) {
        String testLine = "1103530,,24249,18 STREET NW,N,2681,EDMONTON ENERGY AND TECHNOLOGY PARK,Dene Ward,987500,53.70060957992834,-113.36267820386168,POINT (-113.36267820386168 53.70060957992834),51,44,4,COMMERCIAL,RESIDENTIAL,RESIDENTIAL\n";
        PropertyAssessment testAssessment = PropertyAssessment.fromCSV(testLine);
        System.out.println(testAssessment);
    }
}
