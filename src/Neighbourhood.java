public class Neighbourhood {
    public String name;
    public String ward;

    public Neighbourhood(String name, String ward) {
        this.name = name;
        this.ward = ward;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.ward + ")";
    }
}
