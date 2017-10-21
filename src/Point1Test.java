/**
 * 
 */

/**
 * This class will
 *
 * @author Siddharth Hingorani (sid97)
 * @version 2016.xx.xx
 *
 */
public class Point1Test extends student.TestCase
{
    /**
     * set up enivronment.
     */
    public void setUp()
    {
        @SuppressWarnings("unused")
        Point1 point = new Point1();
    }

    /**
     * This method will
     */
    public void testHere()
    {
        String[] temp = new String[1];
        temp[0] = "SyntaxTest.txt";
        Point1.main(temp);
        assertNotNull(temp);
    }

    /**
     * This method will
     */
    public void testHere1()
    {
        String[] temp = new String[0];
        Point1.main(temp);
        assertNotNull(temp);
    }

}
