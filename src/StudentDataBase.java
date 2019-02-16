import java.util.*;
import java.io.Serializable;

/**
 * This is the database of the students which is a hashmap that uses serializable to function.
 */
public class StudentDataBase extends HashMap<String, Student> implements Serializable {
    //Constructor

    /**
     * Default constructor.
     */
    public StudentDataBase() {
    }

    //Methods

    /**
     * this method adds a Student into the data base using the webID as the key.
     *
     * @param webID   String representing the key to the database.
     * @param student Student representng the student being added to the database.
     */
    public void addStudent(String webID, Student student) {
        if (this.get(webID) != null)
            throw new IllegalArgumentException("Student already exists with that webID");
        this.put(webID, student);
    }

    /**
     * Removes the Student from the data base based on the key.
     *
     * @param webID String representing the key to remove the user.
     */
    public void removeStudent(String webID) {
        if (webID == null || this.get(webID) == null)
            throw new IllegalArgumentException();
        this.remove(webID);
    }

    /**
     * returns the user based on the key provided.
     *
     * @param webID String representing the key to access the student from the data base.
     * @return an object of student representing the student returned fro the database
     */
    public Student getStudent(String webID) {
        return this.get(webID);
    }
}
