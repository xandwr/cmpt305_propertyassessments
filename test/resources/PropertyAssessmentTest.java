import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PropertyAssessmentTest {

    @Test
    void testConstructorAndGetters() {
        String inputLine = "1297472,,9617,152 STREET NW,Y,4580,WEST JASPER PLACE,Nakota Isga Ward,321000,53.53364235570965,-113.58280461570331,POINT (-113.58280461570331 53.53364235570965),100,,,RESIDENTIAL,,";

        PropertyAssessment propertyAssessment = new PropertyAssessment(inputLine);

        assertEquals(1297472, propertyAssessment.getAccountNumber());
        assertEquals(new BigDecimal("321000"), propertyAssessment.getAssessedValue());
        assertEquals("WEST JASPER PLACE", propertyAssessment.getNeighbourhood().getName());
    }

    @Test
    void testCompareTo() {
        String inputLine1 = "1053974,,4204,38 STREET NW,N,6370,KINISKI GARDENS,Sspomitapi Ward,284500,53.47891370593659,-113.39928952181278,POINT (-113.39928952181278 53.47891370593659),100,,,RESIDENTIAL,,";
        String inputLine2 = "1108539,,,,N,3150,CUMBERLAND,Anirniq Ward,145500,53.61427702104238,-113.54751077498673,POINT (-113.54751077498673 53.61427702104238),100,,,COMMERCIAL,,";

        PropertyAssessment propertyAssessment1 = new PropertyAssessment(inputLine1);
        PropertyAssessment propertyAssessment2 = new PropertyAssessment(inputLine2);

        assertTrue(propertyAssessment1.compareTo(propertyAssessment2) < 0); // 12345 < 67890
        assertTrue(propertyAssessment2.compareTo(propertyAssessment1) > 0); // 67890 > 12345
        assertEquals(0, propertyAssessment1.compareTo(propertyAssessment1)); // 12345 == 12345
    }

    @Test
    void testPrintAssessmentInfo() {
        String inputLine = "1053974,,4204,38 STREET NW,N,6370,KINISKI GARDENS,Sspomitapi Ward,284500,53.47891370593659,-113.39928952181278,POINT (-113.39928952181278 53.47891370593659),100,,,RESIDENTIAL,,";
        PropertyAssessment propertyAssessment = new PropertyAssessment(inputLine);

        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        propertyAssessment.printAssessmentInfo();

        String expectedOutput = """
               Account number = 1053974
               Address = 4204 38 STREET NW
               Assessed value = $284,500.00
               Assessment class = null
               Neighbourhood = KINISKI GARDENS (Sspomitapi Ward)
               Location = (53.47891370593659, -113.39928952181278)

               """;

        assertEquals(expectedOutput, outContent.toString());
    }
}