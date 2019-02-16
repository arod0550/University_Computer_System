import java.util.*;
import java.io.*;

/**
 * this is the main class of the project. This is a University Student Computer System using Hashmaps as the data structure.
 * In this system, the students can be registered into by an admin. The student has their own login to acceess the system.
 * The student can then proceed with adding or removing courses. The admin has access to many features within such as,
 * viewing all the students registered for a particular course
 */
public class LunarSystem {
    //Data fields
    private static Registrar registrar = new Registrar();
    private static Student student = new Student();
    private static String webId = "";

    public static void main(String[] args) {
        //Deserialize an object
        try {
            FileInputStream fileIn = new FileInputStream("src\\Lunar.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            registrar = (Registrar) in.readObject();
            in.close();
            fileIn.close();
            //Welcoming message
            System.out.println("Welcome to the Lunar System, a second place course registration system for second rate courses at a ");
            System.out.println("second class school.");
            System.out.println();
            System.out.println("previous data has been loaded!");
            System.out.println();
        } catch (IOException e) {
            //Welcoming message
            System.out.println("Welcome to the Lunar System, a second place course registration system for second rate courses at a ");
            System.out.println("second class school.");
            System.out.println();
            System.out.println("No previous data found.");
            System.out.println();
        } catch (ClassNotFoundException e) {
            //Welcoming message
            System.out.println("Welcome to the Lunar System, a second place course registration system for second rate courses at a ");
            System.out.println("second class school.");
            System.out.println();
            System.out.println("No previous data found.");
            System.out.println();
        }

        //create a scanner
        Scanner input = new Scanner(System.in);

        //initialize section variable to a empty string
        String section;

        while (true) {
            mainMenu();
            section = input.nextLine();

            switch (section.toLowerCase()) {
                case "l":
                    //Login
                    try {
                        System.out.println("Please enter webid:");
                        webId = input.nextLine();

                        if (webId.equalsIgnoreCase(registrar.getRegistrarWebID())) {
                            System.out.println();
                            System.out.println("Welcome Registrar");
                            registrarMenu();//grant access and send to registrar
                            break;
                        } else if ((student = registrar.getStudents().getStudent(webId)) != null) {
                            System.out.println();
                            System.out.println("Welcome " + registrar.getStudents().getStudent(webId).getWebID());
                            studentMenu();//grant access and send to student section
                            break;
                        } else
                            System.out.println();
                        System.out.println("Invalid webID. Try again.");
                    } catch (InputMismatchException ex) {
                        System.out.println("Wrong input. Try again!!!");
                    }
                    break;
                case "x":
                    //Save state and quit
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream("src\\Lunar.ser");
                        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                        out.writeObject(registrar);
                        out.close();
                        fileOutputStream.close();
                        System.out.println("State has been saved!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //Exit
                    System.out.println("You are now leaving the the Lunar System.!");
                    System.exit(1);
                    break;
                case "q":
                    try {
                        // Section Q (Quit without saving data)
                        for (String student : registrar.getStudents().keySet()) {
                            //deregister all students to erase all data
                            registrar.getStudents().removeStudent(student);
                        }
                    } catch (Exception ex) {
                        System.out.println("You are now leaving the the Lunar System.");
                        System.out.println("We congratulate you on your decision to do something more productive with your time.");
                        System.exit(0);
                    }
                    System.out.println("You are now leaving the the Lunar System.");
                    System.out.println("We congratulate you on your decision to do something more productive with your time.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid section, please try again");
            }
        }
    }

    /**
     * Method containing Menu.
     */
    public static void mainMenu() {
        //Menu
        System.out.println("Menu:");
        System.out.println("    L) Login");
        System.out.println("    X) Save state and quit");
        System.out.println("    Q)Quit without saving state.");

        System.out.println();

        //prompt user to go to section desired
        System.out.println("please select an option: ");
    }

    /**
     * Method containing Menu.
     */
    public static void registrarMenu() {

        //create a scanner
        Scanner input = new Scanner(System.in);

        //initialize section variable to a empty string
        String section3;

        while (true) {
            //Menu
            System.out.println("Options:");
            System.out.println("    R) Register a student");
            System.out.println("    D) De-register a student");
            System.out.println("    E) View course enrollment");
            System.out.println("    A) Show all students enrolled");
            System.out.println("    L) Logout");

            System.out.println();

            //prompt user to go to section desired
            System.out.println("please select an option: ");
            section3 = input.nextLine();

            switch (section3.toLowerCase()) {
                //Register a student
                case "r":
                    System.out.println("Please enter a webid for the new student:");
                    String newWebId = input.nextLine();

                    //create student
                    Student studentAdded = new Student(newWebId.toLowerCase());

                    //add student to database
                    try {
                        registrar.registerStudent(newWebId.toLowerCase(), studentAdded);
                        System.out.println(newWebId + " registered.");
                        System.out.println();
                    } catch (InputMismatchException ex) {
                        System.out.println("invalid input. try again");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Student exists already. Try again");
                    }
                    break;

                //DEregister a student
                case "d":
                    System.out.println("Please enter a webid for the student to be deregistered:");
                    String removeWebId = input.nextLine();

                    try {
                        registrar.deRegisterStudent(removeWebId.toLowerCase());
                        System.out.println(removeWebId + " deregistered.");
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println("Student doesn't exist. try again");
                    }
                    break;
                //View course enrollment
                case "e":
                    System.out.println("please enter course department:");
                    String courseDepartment = input.nextLine();
                    System.out.println("please enter the course number:");
                    int courseNumber = input.nextInt();

                    System.out.println(registrar.toStringStudentsTakingCourse(courseDepartment, courseNumber));
                    System.out.println();
                    input.nextLine();//flush
                    break;
                //show all
                case "a":
                    System.out.println(registrar.toStringForAll());
                    break;
                //logout
                case "l":
                    System.out.println(registrar.getRegistrarWebID() + " has logged out");
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Method containing Menu.
     */
    public static void studentMenu() {
        //Data fields
        Student student = registrar.getStudents().getStudent(webId);

        //create a scanner
        Scanner input = new Scanner(System.in);

        //initialize section variable to a empty string
        String section2;

        while (true) {
            //Menu
            System.out.println("Options:");
            System.out.println("    A) Add a class");
            System.out.println("    D) Drop a class");
            System.out.println("    C) View your classes sorted by course name/department");
            System.out.println("    S) View your courses sorted by semester");
            System.out.println("    L) Logout");

            System.out.println();

            //prompt user to go to section desired
            System.out.println("please select an option: ");
            section2 = input.nextLine();

            switch (section2.toLowerCase()) {
                //Add a class
                case "a":
                    try {
                        System.out.println("Please enter a semester:");
                        String courseSemester = input.nextLine();
                        System.out.println("Please enter course deparment:");
                        String courseDepartment = input.nextLine();
                        System.out.println("Please enter course number:");
                        int courseNumber = input.nextInt();

                        //Class created with parameters
                        Course course = new Course(courseDepartment, courseNumber, courseSemester);

                        //Add the course to student
                        if ((student = registrar.getStudents().getStudent(webId)) != null) {
                            student.addCourse(course);
                        } else
                            System.out.println("Student not found");

                        System.out.println("" + courseDepartment + " " + courseNumber + " was added to " + student.getWebID() + "'s classes.");
                        System.out.println();
                    } catch (InputMismatchException ex) {
                        System.out.println("invalid input and course wasn't added. try again");
                    }
                    input.nextLine();//flush
                    break;
                //Drop a class
                case "d":
                    try {
                        System.out.println("Please enter a semester:");
                        String courseSemester = input.nextLine();
                        System.out.println("Please enter course deparment:");
                        String courseDepartment = input.nextLine();
                        System.out.println("Please enter course number:");
                        int courseNumber = input.nextInt();


                        //Class created with parameters
                        Course course = new Course(courseDepartment, courseNumber, courseSemester);

                        if ((student = registrar.getStudents().getStudent(webId)) != null) {
                            if ((student = registrar.getStudents().getStudent(webId)).containsCourse(student.getCourses(), course)) {
                                student.dropCourse(course);
                            } else {
                                System.out.println("Class not found");
                                System.out.println();
                                break;
                            }
                        } else
                            System.out.println("Student not found");
                    } catch (InputMismatchException ex) {
                        System.out.println("invalid input. try again");
                    }
                    input.nextLine();//flush
                    break;

                //View your classes sorted by course name/department and logs them off
                case "c":
                    System.out.println(student.getWebID() + "'s classes are the following:");
                    System.out.println("*********************************");
                    System.out.println(student.ToStringByDepartment());
                    System.out.println();
                    System.out.println(student.getWebID() + " has been logged out.");
                    System.out.println();
                    return;
                //View your courses sorted by semester and logs them off
                case "s":
                    System.out.println(student.getWebID() + "'s classes are the following:");
                    System.out.println("*********************************");
                    System.out.println(student.ToStringBySemester());
                    System.out.println();
                    System.out.println(student.getWebID() + " has been logged out.");
                    System.out.println();
                    return;
                //logout
                case "l":
                    System.out.println(student.getWebID() + " has logged out.");
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
