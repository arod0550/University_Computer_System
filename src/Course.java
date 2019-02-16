import java.io.Serializable;

/**
 * This class represents the course the student will take.
 */
public class Course implements Serializable {
    //Data Fields
    private String department = "";
    private int number;
    private String semester = "";

    //Constructor

    public Course(String department, int number, String semester) {
        this.department = department;
        this.number = number;
        this.semester = semester;
    }

    //Methods

    /**
     * Getter for the department of the course.
     *
     * @return String representing the department the course belongs to.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Setter for the course's department.
     *
     * @param department String representing the course deparment.
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Getter for the course number
     *
     * @return Int representing the number of the course.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Setter for the number of the course.
     *
     * @param number int representing the new number of the course.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Getter for the semester of the course.
     *
     * @return String representing the semester of the course.
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Setter for the semester of the course
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }
}