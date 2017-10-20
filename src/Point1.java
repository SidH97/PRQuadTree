
/**
 * This is the main class and takes the I/O of the project
 * 
 * @author Matthew Evans (matce93)
 * @author Sid Hingorani (sid97)
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
            new FileReader("SyntaxTest2.txt");
            // System.out.println("No File given");
        }
    }

}
