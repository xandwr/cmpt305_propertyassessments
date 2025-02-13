import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PropertyAssessmentsTest {

    @Test
    void testConstructorWithCSV() {
        String csvFilePath = "data.csv";
        PropertyAssessments propertyAssessments = new PropertyAssessments(csvFilePath);

        assertEquals(426913, propertyAssessments.getSize());
    }

    @Test
    void testConstructorWithList() {
        List<PropertyAssessment> assessments = List.of(
                new PropertyAssessment("3925286,,,,N,2311,GORMAN,Dene Ward,162000,53.61782538629719,-113.39340667965742,POINT (-113.39340667965742 53.61782538629719),100,,,COMMERCIAL,,"),
                new PropertyAssessment("1194976,,15927,88A AVENUE NW,Y,4310,MEADOWLARK PARK,sipiwiyiniwak Ward,336000,53.5224970838542,-113.59797430793354,POINT (-113.59797430793354 53.5224970838542),100,,,RESIDENTIAL,,")
        );

        PropertyAssessments propertyAssessments = new PropertyAssessments(assessments);

        assertEquals(2, propertyAssessments.getSize());
    }

    @Test
    void testGetMin() {
        List<PropertyAssessment> assessments = List.of(
                new PropertyAssessment("3925286,,,,N,2311,GORMAN,Dene Ward,162000,53.61782538629719,-113.39340667965742,POINT (-113.39340667965742 53.61782538629719),100,,,COMMERCIAL,,"),
                new PropertyAssessment("1194976,,15927,88A AVENUE NW,Y,4310,MEADOWLARK PARK,sipiwiyiniwak Ward,336000,53.5224970838542,-113.59797430793354,POINT (-113.59797430793354 53.5224970838542),100,,,RESIDENTIAL,,")
        );

        PropertyAssessments propertyAssessments = new PropertyAssessments(assessments);

        assertEquals(new BigDecimal("162000"), propertyAssessments.getMin());
    }

    @Test
    void testGetMax() {
        List<PropertyAssessment> assessments = List.of(
                new PropertyAssessment("3925286,,,,N,2311,GORMAN,Dene Ward,162000,53.61782538629719,-113.39340667965742,POINT (-113.39340667965742 53.61782538629719),100,,,COMMERCIAL,,"),
                new PropertyAssessment("1194976,,15927,88A AVENUE NW,Y,4310,MEADOWLARK PARK,sipiwiyiniwak Ward,336000,53.5224970838542,-113.59797430793354,POINT (-113.59797430793354 53.5224970838542),100,,,RESIDENTIAL,,")
        );

        PropertyAssessments propertyAssessments = new PropertyAssessments(assessments);

        assertEquals(new BigDecimal("336000"), propertyAssessments.getMax());
    }

    @Test
    void testGetRange() {
        List<PropertyAssessment> assessments = List.of(
                new PropertyAssessment("3925286,,,,N,2311,GORMAN,Dene Ward,162000,53.61782538629719,-113.39340667965742,POINT (-113.39340667965742 53.61782538629719),100,,,COMMERCIAL,,"),
                new PropertyAssessment("1194976,,15927,88A AVENUE NW,Y,4310,MEADOWLARK PARK,sipiwiyiniwak Ward,336000,53.5224970838542,-113.59797430793354,POINT (-113.59797430793354 53.5224970838542),100,,,RESIDENTIAL,,")
        );

        PropertyAssessments propertyAssessments = new PropertyAssessments(assessments);

        assertEquals(new BigDecimal("174000"), propertyAssessments.getRange());
    }

    @Test
    void testGetMean() {
        List<PropertyAssessment> assessments = List.of(
                new PropertyAssessment("3925286,,,,N,2311,GORMAN,Dene Ward,162000,53.61782538629719,-113.39340667965742,POINT (-113.39340667965742 53.61782538629719),100,,,COMMERCIAL,,"),
                new PropertyAssessment("1194976,,15927,88A AVENUE NW,Y,4310,MEADOWLARK PARK,sipiwiyiniwak Ward,336000,53.5224970838542,-113.59797430793354,POINT (-113.59797430793354 53.5224970838542),100,,,RESIDENTIAL,,")
        );

        PropertyAssessments propertyAssessments = new PropertyAssessments(assessments);

        assertEquals(new BigDecimal("249000"), propertyAssessments.getMean());
    }

    @Test
    void testGetMedian() {
        List<PropertyAssessment> assessments = List.of(
                new PropertyAssessment("3925286,,,,N,2311,GORMAN,Dene Ward,162000,53.61782538629719,-113.39340667965742,POINT (-113.39340667965742 53.61782538629719),100,,,COMMERCIAL,,"),
                new PropertyAssessment("1194976,,15927,88A AVENUE NW,Y,4310,MEADOWLARK PARK,sipiwiyiniwak Ward,336000,53.5224970838542,-113.59797430793354,POINT (-113.59797430793354 53.5224970838542),100,,,RESIDENTIAL,,"),
                new PropertyAssessment("1034339,,9420,92 STREET NW,N,6710,STRATHEARN,Métis Ward,49836000,53.53094866121484,-113.46927857387664,POINT (-113.46927857387664 53.53094866121484),100,,,OTHER RESIDENTIAL,,")
        );

        PropertyAssessments propertyAssessments = new PropertyAssessments(assessments);

        assertEquals(new BigDecimal("336000"), propertyAssessments.getMedian());
    }

    @Test
    void testGetStatisticsForNeighbourhood() {
        List<PropertyAssessment> assessments = List.of(
                new PropertyAssessment("3925286,,,,N,2311,GORMAN,Dene Ward,162000,53.61782538629719,-113.39340667965742,POINT (-113.39340667965742 53.61782538629719),100,,,COMMERCIAL,,"),
                new PropertyAssessment("1194976,,15927,88A AVENUE NW,Y,4310,MEADOWLARK PARK,sipiwiyiniwak Ward,336000,53.5224970838542,-113.59797430793354,POINT (-113.59797430793354 53.5224970838542),100,,,RESIDENTIAL,,")
        );

        PropertyAssessments propertyAssessments = new PropertyAssessments(assessments);

        Statistics stats = propertyAssessments.getStatisticsForNeighbourhood("MEADOWLARK PARK");
        assertEquals(new BigDecimal("336000"), stats.getMax());
    }

    @Test
    void testGetPropertyByAccountNumber() {
        List<PropertyAssessment> assessments = List.of(
                new PropertyAssessment("3925286,,,,N,2311,GORMAN,Dene Ward,162000,53.61782538629719,-113.39340667965742,POINT (-113.39340667965742 53.61782538629719),100,,,COMMERCIAL,,"),
                new PropertyAssessment("1194976,,15927,88A AVENUE NW,Y,4310,MEADOWLARK PARK,sipiwiyiniwak Ward,336000,53.5224970838542,-113.59797430793354,POINT (-113.59797430793354 53.5224970838542),100,,,RESIDENTIAL,,")
        );

        PropertyAssessments propertyAssessments = new PropertyAssessments(assessments);

        PropertyAssessment property = propertyAssessments.getPropertyByAccountNumber(3925286);
        assertNotNull(property);
        assertEquals(3925286, property.getAccountNumber());
    }
}