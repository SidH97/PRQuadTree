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
public class PrQuadTreeTest extends student.TestCase
{
    private PrQuadTree<Point> tree;
    private Point point;
    private Point point1;
    private Point point2;
    private Point point3;

    /**
     * This method will setUp the testing Environment.
     */
    public void setUp()
    {
        tree = new PrQuadTree<Point>();
        point = new Point("a", 1, 1);
        point1 = new Point("b", 1, 1);
        point2 = new Point("c", 2, 1);
        point3 = new Point("d", 2, 4);
    }

    /**
     * This method will test the insert Method.
     */
    public void testInsert()
    {
        assertTrue(tree.isEmpty());
        tree.insert(point);
        assertFalse(tree.isEmpty());
        assertEquals(point, tree.find(point));
        tree.insert(point);
        assertEquals(point, tree.find(point));
        tree.insert(point1);
        assertEquals(point1, tree.find(point1));

    }

}
