
import java.util.ArrayList;
import java.util.List;

public abstract class CourseType {
    private String code;
    private String name;

    public CourseType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() { return code; }
    public String getName() { return name; }

    public abstract void displayEvaluationMethod();
}

class ExamCourse extends CourseType {
    private int examCount;

    public ExamCourse(String code, String name, int examCount) {
        super(code, name);
        this.examCount = examCount;
    }

    @Override
    public void displayEvaluationMethod() {
        System.out.println("Exam-Based Course: " + getName() + " (" + getCode() + ") - " +
                examCount + " exams");
    }
}

class AssignmentCourse extends CourseType {
    private int assignmentCount;

    public AssignmentCourse(String code, String name, int assignmentCount) {
        super(code, name);
        this.assignmentCount = assignmentCount;
    }

    @Override
    public void displayEvaluationMethod() {
        System.out.println("Assignment-Based Course: " + getName() + " (" + getCode() + ") - " +
                assignmentCount + " assignments");
    }
}


class Course<T extends CourseType> {
    private T courseType;
    private List<String> enrolledStudents = new ArrayList<>();

    public Course(T courseType) {
        this.courseType = courseType;
    }

    public void enrollStudent(String studentId) {
        enrolledStudents.add(studentId);
    }

    public void displayCourseDetails() {
        courseType.displayEvaluationMethod();
        System.out.println("Enrolled Students: " + enrolledStudents.size());
    }

    public static void processCourses(List<? extends CourseType> courses) {
        for (CourseType course : courses) {
            course.displayEvaluationMethod();
        }
    }

    public T getCourseType() {
        return null;
    }
}


class Main2 {
    public static void main(String[] args) {
        Course<ExamCourse> mathCourse = new Course<>(new ExamCourse("MATH101", "Calculus", 2));
        mathCourse.enrollStudent("S001");
        mathCourse.enrollStudent("S002");

        Course<AssignmentCourse> progCourse = new Course<>(new AssignmentCourse("CS101", "Programming", 5));
        progCourse.enrollStudent("S001");
        progCourse.enrollStudent("S003");

        mathCourse.displayCourseDetails();
        progCourse.displayCourseDetails();

        List<CourseType> allCourses = new ArrayList<>();
        allCourses.add(mathCourse.getCourseType());
        allCourses.add(progCourse.getCourseType());

        Course.processCourses(allCourses);
    }
}