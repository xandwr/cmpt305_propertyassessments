public class Address {
    private final Integer houseNumber;
    private final String streetName;

    public Address(Integer houseNumber, String streetName) {
        this.houseNumber = houseNumber;
        this.streetName = streetName;
    }

    @Override
    public String toString() {
        return this.houseNumber.toString() + " " + this.streetName;
    }
}
