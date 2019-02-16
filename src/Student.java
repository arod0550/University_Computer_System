import java.io.Serializable;
import java.util.*;

/**
 * This class represents the student in the system.
 */
public class Student implements Serializable {
    //Data fields
    private String webID = "";
    private ArrayList<Course> courses;

    //Constructor

    /**
     * Empty constructor.
     */
    public Student() {
    }

    /**
     * Default constructor.
     *
     * @param webID
     */
    public Student(String webID) {
        this.webID = webID;
        courses = new ArrayList<Course>();
    }

    //Methods

    /**
     * checks that arraylist of courses passed in contains the course passed in.
     *
     * @param courses
     * @param c
     * @return true if arraylist contains the course passed in
     */
    public Boolean containsCourse(ArrayList<Course> courses, Course c) {
        boolean result = false;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getDepartment().equalsIgnoreCase(c.getDepartment()) && ((Integer) courses.get(i).getNumber()).equals(c.getNumber())) {
                result = true;
                break;
            } else
                result = false;
        }
        return result;
    }

    /**
     * returns the semester attached to that course
     *
     * @param c
     * @return
     */
    public String getSemester(ArrayList<Course> courses, Course c) {
        String result = "";
        boolean found = false;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getDepartment().equalsIgnoreCase(c.getDepartment()) && ((Integer) courses.get(i).getNumber()).equals(c.getNumber())) {
                result += courses.get(i).getSemester() + " ";
                found = true;
            }
        }
        if (found = false) {
            result += "Nothing was found";
        }

        return result;
    }


    /**
     * Adds course to Student.
     *
     * @param course Object of course to be added o the student.
     */
    public void addCourse(Course course) {
        courses.add(course);
    }

    /**
     * Drops course of student.
     *
     * @param course
     */
    public void dropCourse(Course course) {
        courses.remove(course);
    }

    /**
     * List classes by Department
     *
     * @return String Representing classes by department.
     */
    public String ToStringByDepartment() {
        if (courses.isEmpty()) {
            return "empty";
        }
        //sort them by department
        Collections.sort(courses, new CourseNameComparator());
        //print them
        String result = "";
        result += String.format("%-10s%-15s%s\n", "Dept.", "Course", "Semester");
        result += String.format("%s\n", "---------------------------------");
        for (Course c : courses)
            result += String.format("%-10s%-16s%s\n", c.getDepartment(), c.getNumber(), c.getSemester());

        return result;
    }

    /**
     * List classes by Department
     *
     * @return String Representing classes by department.
     */
    public String ToStringBySemester() {
        if (courses.isEmpty()) {
            return "empty";
        }
        //sort them by semester first
        Collections.sort(courses, new SemesterComparator());
        //print them
        String result = "";
        result += String.format("%-10s%-15s%s\n", "Dept.", "Course", "Semester");
        result += String.format("%s\n", "---------------------------------");
        for (Course c : courses)
            result += String.format("%-10s%-16s%s\n", c.getDepartment(), c.getNumber(), c.getSemester());

        return result;
    }

    /**
     * Prints out all the courses of the student.
     *
     * @return String representing all the courses of the student.
     */
    public String toStringCourses() {
        if (courses.isEmpty()) {
            return "empty";
        }
        String result = "";
        for (Course c : courses)
            result += String.format("%-17s%s\n            ", c.getDepartment(), c.getNumber());


        return result;
    }

    /**
     * Getter for the web ID of the student.
     *
     * @return String of the webID of the student.
     */
    public String getWebID() {
        return webID;
    }

    /**
     * Setter for the web ID of the student.
     *
     * @param webID String representing the webID of he
     */
    public void setWebID(String webID) {
        this.webID = webID;
    }

    /**
     * getter for the list of courses
     *
     * @return An ArrayList with courses of each student.
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Setter for the courses of the student
     *
     * @param courses An arraylist of courses that represents the new list of courses of the student.
     */
    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
