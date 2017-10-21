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
public class DatabaseTest extends student.TestCase
{

    private Database db;

    /**
     * Set's up the test environment.
     */
    public void setUp()
    {
        db = new Database();
    }

    /**
     * This method will test getters
     */
    public void testGetters()
    {
        db.dump();
        db.duplicates();
        db.insertPoint("name", 1, 0);
        db.insertPoint("name1", 1, 0);
        db.insertPoint("name", -123, 0);
        db.insertPoint("name", 1, -20);
        db.insertPoint("name", 1045, 0);
        db.insertPoint("name", 1, 1045);
        db.insertPoint("1name", 1, 0);
        db.dump();
        db.duplicates();
        db.regionSearch(0, 0, 1024, 1024);
        db.regionSearch(0, 0, 0, 1024);
        db.regionSearch(0, 0, 1024, 0);
        db.regionSearch(1024, 1024, 0, 1024);
        db.regionSearch(1024, 1024, 1024, 1024);
        db.search("name");
        db.search("no");
        db.removeBydimensions(1, 0);
        db.removeByName("name");
        db.removeBydimensions(1, 15);
        db.removeByName("zoop");
        db.removeByName("abc");
        db.removeBydimensions(101, 101);
        db.removeBydimensions(45, 15);
        assertTrue(db.equals(db));
    }

}
