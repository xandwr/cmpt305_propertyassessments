public class Neighbourhood {
    public final String name;
    public final String ward;

    public Neighbourhood(String neighbourhood, String ward) {
        this.name = neighbourhood;
        this.ward = ward;
    }

    @Override
    public String toString() {
        return name + " (" + ward + ")";
    }
}
