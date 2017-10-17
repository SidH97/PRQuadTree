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
    private Point point8;

    /**
     * This method will setUp the testing Environment.
     */
    public void setUp()
    {
        tree = new PrQuadTree<Point>();
        point = new Point("a", 1, 1);
        point1 = new Point("b", 10, 10);
        point2 = new Point("c", 100, 100);
        point3 = new Point("d", 0, 100);
        point4 = new Point("NE", 1024, 1024);
        point5 = new Point("NW", 0, 1024);
        point6 = new Point("SW", 0, 0);
        point7 = new Point("SE", 1024, 0);
        point8 = new Point("e", 100, 0);

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
    
    public void testDelete()
    {
    	//base case
    	assertTrue(tree.isEmpty());
        tree.insert(point);
        tree.insert(point);
        tree.insert(point);
        assertTrue(tree.delete(point));
        tree.insert(point7);
        assertFalse(tree.isEmpty());
        assertTrue(tree.find(point7));
        assertTrue(tree.delete(point7));
        assertFalse(tree.find(point7));
        //has 2 "point"
        tree.insert(point1);
        //CAN NOT USE "point2"
        tree.insert(point3);
        tree.insert(point4);
        tree.insert(point5);
        tree.insert(point6);
        tree.insert(point7);
        tree.insert(point8);
        assertTrue(tree.delete(point8));
        assertFalse(tree.find(point8));
    }

}
