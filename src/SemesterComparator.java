import java.util.*;

public class SemesterComparator implements Comparator {

    //Data Fields

    /**
     * compare two courses based on the semester they were offered.
     *
     * @param left
     * @param right
     * @return -1 if the left course’s semester is less than the right,
     * 0 if the semesters are equal, and 1 if the left’s semester is greater than the right.
     */
    public int compare(Course left, Course right) {
        return (left.getSemester().compareTo(right.getSemester()));
    }

    /**
     * used to call the compare method required.
     */
    public int compare(Object o1, Object o2) {
        return compare((Course) o1, (Course) o2);
    }
}
