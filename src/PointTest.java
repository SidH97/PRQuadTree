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
public class PointTest extends student.TestCase
{

    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;
    private Point point5;
    private BST<Point> tree;

    /**
     * Set's up the test environment.
     */
    public void setUp()
    {
        point1 = new Point("name", 0, 0);
        point2 = new Point("name", 0, 0);
        point3 = new Point("Name", 1, 5);
        point4 = new Point("abc", 1, 1);
        point5 = new Point("zbc", 3, 0);
        tree = new BST<Point>();
        tree.insert(point1);
        tree.insert(point4);
        tree.insert(point5);
    }

    /**
     * This method will test getters
     */
    public void testGetters()
    {
        assertEquals(1, point3.getX());
        assertEquals(1, point4.getY());
        assertEquals("name", point2.getName());

    }

    public void testCompares()
    {
        assertEquals(0, point2.compareTo(point1));
        assertEquals(32, point2.compareTo(point3));
        assertEquals(-12, point2.compareTo(point5));
        assertEquals(1, point4.compareToX(0));
        assertEquals(-1, point4.compareToX(100));
        assertEquals(0, point4.compareToX(1));
        assertEquals(1, point4.compareToY(0));
        assertEquals(-1, point4.compareToY(100));
        assertEquals(0, point4.compareToY(1));

    }

    public void testEqualstoString()
    {
        assertEquals("(name, 0, 0)", point2.toString());
        assertTrue(point2.equals(point2));
        assertTrue(point2.equals(point1));
        assertFalse(point2.equals(point3));

    }

}
