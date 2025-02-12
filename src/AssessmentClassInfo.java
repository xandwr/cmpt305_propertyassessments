import java.util.ArrayList;
import java.util.List;

public class AssessmentClassInfo {
    private final List<AssessmentClass> classes = new ArrayList<>();

    public AssessmentClassInfo(
            String classPercent1, String classPercent2, String classPercent3,
            String className1, String className2, String className3
    ) {
        addClass(classPercent1, className1);
        addClass(classPercent2, className2);
        addClass(classPercent3, className3);
    }

    private void addClass(String classPercent, String className) {
        if (classPercent != null && !classPercent.trim().isEmpty() &&
                className != null && !className.trim().isEmpty()) {
            classes.add(new AssessmentClass(className, classPercent));
        } else if (className != null && !className.trim().isEmpty()) {
            classes.add(new AssessmentClass(className, "0"));
        } else if (classPercent != null && !classPercent.trim().isEmpty()) {
            classes.add(new AssessmentClass("UNKNOWN", classPercent));
        }
    }

    @Override
    public String toString() {
        return classes.toString();
    }
}