public class AssessmentClass {
    private final String className;
    private final String classPercent;

    /// <summary>Stores a name-percent pair for each assessment class in a CSV entry.</summary>
    public AssessmentClass(String className, String classPercent) {
        this.className = className;
        this.classPercent = classPercent;
    }

    @Override
    public String toString() {
        return className + " = " + classPercent + "%";
    }
}
