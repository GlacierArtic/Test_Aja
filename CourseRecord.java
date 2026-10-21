
import java.util.Vector;
import java.util.Enumeration;

public class CourseRecord {
    private Student student;
    private Vector<Assignment> assignments;

    public CourseRecord() {
        assignments = new Vector<Assignment>();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student s) {
        student = s;
    }

    public void addAssignment(Assignment a) {
        assignments.addElement(a);
    }

    public Enumeration<Assignment> getAssignments() {
        return assignments.elements();
    }

    public double getAverageMark() throws NotYetSetException {
        if (assignments.isEmpty()) {
            throw new NotYetSetException(
                "No assignment has been added"
            );
        }

        double total = 0;
        int count = 0;

        Enumeration<Assignment> e = getAssignments();

        while (e.hasMoreElements()) {
            Assignment a = e.nextElement();
            total += a.getMark();
            count++;
        }

        return total / count;
    }
}