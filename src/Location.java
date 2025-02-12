public class Location {
    public Double longitude;
    public Double latitude;

    public Location(String longitude, String latitude) {
        this.longitude = Double.parseDouble(longitude);
        this.latitude = Double.parseDouble(latitude);
    }

    @Override
    public String toString() {
        return "(" + this.latitude + ", " + this.longitude + ")";
    }
}
