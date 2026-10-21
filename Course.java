
import java.util.Vector;
import java.util.Enumeration;

public class Course {
    private String title;
    private Vector<CourseRecord> courseRecords;

    public Course() {
        courseRecords = new Vector<CourseRecord>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String t) {
        title = t;
    }

    public void addCourseRecord(CourseRecord cr) {
        courseRecords.addElement(cr);
    }

    public Enumeration<CourseRecord> getCourseRecords() {
        return courseRecords.elements();
    }

    public void printCourseStudents() {
        System.out.println("Daftar Mahasiswa: " + title);

        Enumeration<CourseRecord> e = getCourseRecords();

        while (e.hasMoreElements()) {
            CourseRecord cr = e.nextElement();
            Student s = cr.getStudent();

            if (s != null) {
                System.out.println("- " + s.getName());
            }
        }
    }

    public void printBestStudent() {
        Student bestStudent = null;
        double bestMark = -1;

        Enumeration<CourseRecord> e = getCourseRecords();

        while (e.hasMoreElements()) {
            CourseRecord cr = e.nextElement();

            try {
                double average = cr.getAverageMark();

                if (average > bestMark) {
                    bestMark = average;
                    bestStudent = cr.getStudent();
                }
            } catch (NotYetSetException ex) {
                System.out.println(
                    "Nilai belum lengkap: " + ex.getMessage()
                );
            }
        }

        if (bestStudent != null) {
            System.out.println("Mahasiswa dengan Nilai Terbaik:");
            System.out.println("Nama: " + bestStudent.getName());
            System.out.println("Rata-rata: " + bestMark);
        } else {
            System.out.println("Belum ada nilai yang dapat dihitung.");
        }
    }

    public void printFinalExamStudents() {
        System.out.println("Mahasiswa yang Layak Ujian Akhir:");

        Enumeration<CourseRecord> e = getCourseRecords();
        boolean found = false;

        while (e.hasMoreElements()) {
            CourseRecord cr = e.nextElement();

            try {
                double average = cr.getAverageMark();

                if (average >= 60) {
                    Student s = cr.getStudent();

                    if (s != null) {
                        System.out.println(
                            "- " + s.getName() +
                            " (Rata-rata: " + average + ")"
                        );
                        found = true;
                    }
                }
            } catch (NotYetSetException ex) {
                System.out.println(
                    "Nilai belum lengkap: " + ex.getMessage()
                );
            }
        }

        if (!found) {
            System.out.println(
                "Belum ada mahasiswa yang memenuhi syarat."
            );
        }
    }
}