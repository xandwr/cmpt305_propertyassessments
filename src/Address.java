public class Address {
    public Integer houseNumber;
    public String streetName;

    public Address(String houseNumber, String streetName) {
        if (houseNumber == null || streetName == null) {
            throw new IllegalArgumentException("A house number and street name must be provided!");
        }

        this.houseNumber = houseNumber.isBlank() ? null : Integer.parseInt(houseNumber);
        this.streetName = streetName.isBlank() ? null : streetName;
    }

    @Override
    public String toString() {
        String result =
                (this.houseNumber == null ? "" : this.houseNumber) +
                ((this.houseNumber == null || this.streetName == null)? "" : " ") +
                (this.streetName == null ? "" : this.streetName);

        if (result.isBlank()) { return "N/A"; }
        return result;
    }
}
