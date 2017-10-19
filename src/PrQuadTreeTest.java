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
    private Point pointNE;
    private Point pointNW;
    private Point pointSW;
    private Point pointSE;

    /**
     * This method will setUp the testing Environment.
     */
    public void setUp()
    {
        tree = new PrQuadTree<Point>();
        point = new Point("point", 1000, 250);
        point1 = new Point("point1", 10, 10);
        point2 = new Point("point2", 100, 100);
        point3 = new Point("point3", 0, 100);
        point4 = new Point("point4", 1024, 1024);
        point5 = new Point("point5", 0, 1024);
        point6 = new Point("point6", 0, 0);
        point7 = new Point("point7", 1024, 0);
        point8 = new Point("point8", 100, 0);
        pointNE = new Point("pointSE", 1000, 1000);
        pointNW = new Point("pointSW", 24, 1000);
        pointSW = new Point("pointNW", 24, 24);
        pointSE = new Point("pointNE", 1000, 24);

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
        tree.insert(point3);
        assertFalse(tree.isEmpty());
        assertTrue(tree.find(point7));
        assertTrue(tree.find(point));
        assertTrue(tree.find(point));
        assertFalse(tree.find(point1));
        assertTrue(tree.find(point3));
        tree.insert(point);
        tree.insert(point);
        tree.insert(point);
        assertTrue(tree.find(point));
        tree.insert(point1);
        assertTrue(tree.find(point1));
        tree.insert(point2);
        tree.insert(point3);
        assertTrue(tree.find(point3));
        tree.insert(point4);
        tree.insert(point5);
        tree.insert(point6);
        tree.insert(point7);
        assertTrue(tree.find(point4));
        assertTrue(tree.find(point5));
        assertTrue(tree.find(point6));
        assertTrue(tree.find(point7));
    }

    public void testDelete()
    {
        assertFalse(tree.delete(point));
        assertTrue(tree.isEmpty());
        tree.insert(point);
        assertFalse(tree.delete(point2));
        tree.insert(point);
        tree.insert(point);
        assertTrue(tree.delete(point));
        assertTrue(tree.delete(point));
        assertTrue(tree.delete(point));
        assertFalse(tree.find(point7));
        tree.insert(point1);
        tree.insert(point3);
        tree.insert(point4);
        tree.insert(point5);
        tree.insert(point6);
        tree.insert(point7);
        tree.insert(point8);
        assertTrue(tree.find(point8));
        assertTrue(tree.delete(point8));
        assertFalse(tree.find(point8));

        tree = new PrQuadTree<Point>();
        assertTrue(tree.isEmpty());
        tree.insert(pointNE);
        assertTrue(tree.find(pointNE));
        tree.insert(pointNW);
        assertTrue(tree.find(pointNW));
        tree.insert(pointSE);
        assertTrue(tree.find(pointSE));
        tree.insert(pointSW);
        assertTrue(tree.find(pointSW));
        assertTrue(tree.delete(pointNE));
        assertFalse(tree.delete(point4));
        tree.insert(pointNE);
        assertTrue(tree.delete(pointNW));
        assertFalse(tree.delete(point5));
        tree.insert(pointNW);
        assertTrue(tree.delete(pointSW));
        assertFalse(tree.delete(point6));
        tree.insert(pointSW);
        assertTrue(tree.delete(pointSE));
        assertFalse(tree.delete(point7));
        tree.insert(pointSE);
        tree.insert(point4);
        tree.insert(point5);
        tree.insert(point6);
        tree.insert(point7);
        assertTrue(tree.find(pointSE));
        assertTrue(tree.find(point7));
        assertTrue(tree.delete(pointNE));

        assertTrue(tree.find(point4));
        assertTrue(tree.find(point5));
        assertTrue(tree.find(point6));
        assertTrue(tree.find(point7));
        assertTrue(tree.find(pointNW));
        tree.insert(pointNE);
        assertTrue(tree.delete(pointNW));
        tree.insert(pointNW);
        assertTrue(tree.delete(pointSW));
        tree.insert(pointSW);
        assertTrue(tree.delete(pointSE));
        tree.insert(pointSE);
        


        System.out.println("new print");
        tree.regionSearch(1024, 0, 1024, 0);
        assertTrue(tree.find(point4));

        // assertTrue(tree.find(point5));
        // assertTrue(tree.find(point6));
        // assertTrue(tree.find(point7));
        // I believe that the whole tree is just point4
        // assertTrue(tree.isEmpty());
        // assertTrue(tree.find(pointNW));
        // assertTrue(tree.delete(pointNW));
        // assertTrue(tree.delete(pointSW));
        // assertTrue(tree.delete(pointSE));

        // assertTrue(tree.find(point5));
        // assertTrue(tree.find(point6));
        // assertTrue(tree.find(point7));
        // I believe that the whole tree is just point4
        // assertTrue(tree.isEmpty());
        // assertTrue(tree.find(pointNW));
        // assertTrue(tree.delete(pointNW));
        // assertTrue(tree.delete(pointSW));
        // assertTrue(tree.delete(pointSE));


        tree = new PrQuadTree<Point>();
        tree.insert(point4);
        tree.insert(pointNE);
        assertTrue(tree.delete(pointNE));
        assertTrue(tree.find(point4));
        assertFalse(tree.find(pointNE));
    }

    public void testFindRoot()
    {
    	assertNull(tree.findRoot());
    	tree.insert(point);
    	assertNotNull(tree.findRoot());
    }

    public void testRegionSearch()
    {
        System.out.println("NEW");
        tree.insert(point);
        tree.insert(point1);
        tree.insert(point2);
        tree.insert(point3);
        tree.insert(point4);
        tree.insert(point5);
        tree.insert(point6);
        tree.insert(point7);
        tree.insert(point8);
        tree.insert(pointNE);
        tree.insert(pointNW);
        tree.insert(pointSW);
        tree.insert(pointSE);
        tree.regionSearch(1024, 0, 1024, 0);
    }

}
