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
public class FileReaderTest extends student.TestCase
{

    /**
     * This method will test
     */
    public void testHere()
    {
        FileReader fr = new FileReader("SyntaxTest.txt");
        FileReader fr2 = new FileReader("SyntaxTest1.txt");
        try
        {
            @SuppressWarnings("unused")
            FileReader fr3 = new FileReader("fh");
        }
        catch (Exception e)
        {
            // intentionally blank
        }
        assertNotNull(fr.toString());
        assertNotNull(fr2.toString());
    }

}
