import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void testValidAddress() {
        Address address = new Address("123", "Main Street");
        assertEquals("123 Main Street", address.toString());
    }

    @Test
    void testBlankHouseNumber() {
        Address address = new Address("", "Main Street");
        assertEquals("Main Street", address.toString());
    }

    @Test
    void testBlankStreetName() {
        Address address = new Address("123", "");
        assertEquals("123", address.toString());
    }

    @Test
    void testBlankHouseNumberAndStreetName() {
        Address address = new Address("", "");
        assertEquals("N/A", address.toString());
    }

    @Test
    void testNullHouseNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Address(null, "Main Street"));
    }

    @Test
    void testNullStreetName() {
        assertThrows(IllegalArgumentException.class, () -> new Address("123", null));
    }

    @Test
    void testNullHouseNumberAndStreetName() {
        assertThrows(IllegalArgumentException.class, () -> new Address(null, null));
    }

    @Test
    void testNonIntegerHouseNumber() {
        assertThrows(NumberFormatException.class, () -> new Address("ABC", "Main Street"));
    }

    @Test
    void testNegativeHouseNumber() {
        Address address = new Address("-123", "Main Street");
        assertEquals("-123 Main Street", address.toString());
    }

    @Test
    void testZeroHouseNumber() {
        Address address = new Address("0", "Main Street");
        assertEquals("0 Main Street", address.toString());
    }
}