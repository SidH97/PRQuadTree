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
    private Point point4;
    private Point point5;
    private Point point6;
    private Point point7;

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
        point4 = new Point("NE", 1024, 1024);
        point5 = new Point("NW", 0, 1024);
        point6 = new Point("SW", 0, 0);
        point7 = new Point("SE", 1024, 0);

    }

    /**
     * This method will test the insert Method.
     */
    public void testInsert()
    {
        assertTrue(tree.isEmpty());
        tree.insert(point);
        tree.insert(point);
        tree.insert(point);
        tree.insert(point7);
        // tree.insert(point3);
        assertFalse(tree.isEmpty());
        assertTrue(tree.find(point7));
        assertTrue(tree.find(point));
        // assertTrue(tree.find(point));
        // assertTrue(tree.find(point1));
        // assertTrue(tree.find(point2));
        // assertTrue(tree.find(point3));
        // tree.insert(point);
        // tree.insert(point);
        // tree.insert(point);
        // assertTrue(tree.find(point));
        // tree.insert(point1);
        // assertTrue(tree.find(point1));
        // tree.insert(point2);
        // tree.insert(point3);
        // assertTrue(tree.find(point3));
        // tree.insert(point4);
        // tree.insert(point5);
        // tree.insert(point6);
        // tree.insert(point7);
        // assertTrue(tree.find(point4));
        // assertTrue(tree.find(point5));
        // assertTrue(tree.find(point6));
        // assertTrue(tree.find(point7));

    }

}
