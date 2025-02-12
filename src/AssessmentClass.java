public class AssessmentClass {
    private final String className;
    private final String classPercent;

    public AssessmentClass(String className, String classPercent) {
        this.className = className;
        this.classPercent = classPercent;
    }

    @Override
    public String toString() {
        return className + " = " + classPercent + "%";
    }
}
