
/**
 * This is the main class and takes the I/O of the project
 * 
 * On my honor: - I have not used source code obtained from another student, or
 * any other unauthorized source, either modified or unmodified. - All source
 * code and documentation used in my program is either my original work, or was
 * derived by me from the source code published in the textbook for this course.
 * - I have not discussed coding details about this project with anyone other
 * than my partner (in the case of a joint submission), instructor, ACM/UPE
 * tutors or the TAs assigned to this course. I understand that I may discuss
 * the concepts of this program with other students, and that another student
 * may help me debug my program so long as neither of us writes anything during
 * the discussion or modifies any computer file during the discussion. I have
 * violated neither the spirit nor letter of this restriction.
 * 
 * @author Matthew Evans (matce93)
 * @author Sid Hingorani (sid97)
 * @version 2017.20.10
 */
public class Point1
{

    /**
     * This method will run the program
     * 
     * @param args
     *            the argument to run with (ie: input files)
     */
    public static void main(String[] args)
    {
        if (args.length != 0)
        {
            new FileReader(args[0]);
        }
        else
        {
            new FileReader("SyntaxTest.txt");
        }
    }

}
