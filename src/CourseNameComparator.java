import java.util.*;

public class CourseNameComparator implements Comparator {
    /**
     * compare two course names with the following priority: department and then number.
     *
     * @param left  first course to be compared
     * @param right second course to be compared
     * @return -1 if the left course name is “less” than the right course name,
     * 0 if they are equal, and 1 if the left course name is “greater” than the right course.
     */
    public int compare(Course left, Course right) {
        return (left.getDepartment().compareTo(right.getDepartment()));
    }

    /**
     * used to call the compare method required.
     */
    public int compare(Object o1, Object o2) {
        return compare((Course) o1, (Course) o2);
    }
}
