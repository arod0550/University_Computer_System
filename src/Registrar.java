import java.io.Serializable;

/**
 * this class represents the admin of the system
 */
public class Registrar implements Serializable {
    //Data Fields
    private String registrarWebID = "registrar";
    private StudentDataBase students = new StudentDataBase();

    //Constructor

    /**
     * Empty Constructor
     */
    public Registrar() {
    }

    /**
     * Default constructor.
     */
    public Registrar(String registrarWebID) {
        this.registrarWebID = registrarWebID;
    }

    //Methods

    /**
     * getter for the id of the registrar
     *
     * @return String representing the registrar
     */
    public String getRegistrarWebID() {
        return registrarWebID;
    }

    /**
     * Setter for the registar.
     */
    public void setRegistrarWebID(String registrarWebID) {
        this.registrarWebID = registrarWebID;
    }

    /**
     * registers student and adds it to database.
     *
     * @param webID   String representing the webId of the student
     * @param student object of student reresenting the newly registered student.
     */
    public void registerStudent(String webID, Student student) {
        if (students.getStudent(webID) != null)
            throw new IllegalArgumentException();

        //add student to database
        students.addStudent(webID, student);
    }

    /**
     * Deregisters student and removes it from database.
     *
     * @param webID String representing the ID of the student being removed.
     */
    public void deRegisterStudent(String webID) {
        //Check for illegal args
        if (students.getStudent(webID) == null)
            throw new IllegalArgumentException();

        //Remove user and account from the Database.
        students.removeStudent(webID);
    }

    /**
     * returns data base of students
     *
     * @return object of StudentDataBase representing the database of students.
     */
    public StudentDataBase getStudents() {
        return students;
    }

    /**
     * Setter for the data base of students
     *
     * @param students a database of students representing the new one.
     */
    public void setStudents(StudentDataBase students) {
        this.students = students;
    }

    /**
     * ToString for students taking the course passed as parameter
     *
     * @param courseDepartment
     * @param courseNumber
     * @return
     */
    public String toStringStudentsTakingCourse(String courseDepartment, int courseNumber) {
        Course course = new Course(courseDepartment, courseNumber, "");

        String result = "Students Registered in " + courseDepartment + " " + courseNumber + ":\n";
        result += "----------------------------------------------\n";
        result += String.format("%-10s%s\n", "WebID:", "Semester:");
        result += "\n";

        boolean found = false;
        for (String student : students.keySet()) {
            while (students.get(student).containsCourse(students.getStudent(student).getCourses(), course)) {
                result += String.format("%-10s%s\n", students.get(student).getWebID(), students.get(student).getSemester(students.getStudent(student).getCourses(), course));
                found = true;
                break;
            }
        }
        if (found = false)
            result += String.format("%-10s\n", "No Students enrolled in class");

        return result;
    }

    /**
     * prints students registered.
     *
     * @return String representing students registered.
     */
    public String toStringForAll() {
        if (students.isEmpty()) {
            return "There are no Students registered";
        }
        String result = "Students Registered:\n";
        result += "----------------------------------------------\n";
        result += String.format("%-10s%-15s%s\n", "WebID:", "Department:", "Course Number:");
        result += "\n";

        for (String student : students.keySet()) {
            result += String.format("%-12s%s\n", students.get(student).getWebID(), students.get(student).toStringCourses());
        }

        return result += "\n";
    }
}
