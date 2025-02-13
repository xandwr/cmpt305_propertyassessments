public class Neighbourhood {
    public String name;
    public String ward;

    /// <summary>Represents a neighbourhood for a CSV entry.</summary>
    public Neighbourhood(String name, String ward) {
        this.name = name;
        this.ward = ward;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.ward + ")";
    }

    public String getName() {
        return name;
    }
}
