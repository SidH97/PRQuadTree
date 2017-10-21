
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
        pointNE = new Point("pointNE", 1000, 1000);
        pointNW = new Point("pointNW", 24, 1000);
        pointSW = new Point("pointSW", 24, 24);
        pointSE = new Point("pointSE", 1000, 24);

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

    /**
     * This method will
     */
    public void testDelete()
    {
        tree.delete(point1);
        assertTrue(tree.isEmpty());
        tree.insert(point1);
        tree.delete(point1);
        assertTrue(tree.isEmpty());
    }

    /**
     * This method will
     */
    public void testFindRoot()
    {
        String name = tree.findRoot().getClass().getName();
        assertEquals("PrQuadTree$PrEmpty", name);
    }

    /**
     * This method will
     */
    public void testRegionSearch()
    {
        System.out.println("regionSearch Test");
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
        assertTrue(tree.find(point));
        assertTrue(tree.find(point1));
        assertTrue(tree.find(point2));
        assertTrue(tree.find(point3));
        assertTrue(tree.find(point4));
        assertTrue(tree.find(point5));
        assertTrue(tree.find(point6));
        assertTrue(tree.find(point7));
        assertTrue(tree.find(point8));
        assertTrue(tree.find(pointNE));
        assertTrue(tree.find(pointNW));
        assertTrue(tree.find(pointSW));
        assertTrue(tree.find(pointSE));
        tree.delete(point);
        tree.delete(point1);
        tree.delete(point2);
        tree.delete(point3);
        tree.delete(point4);
        tree.delete(point5);
        tree.delete(point6);
        tree.delete(point7);
        tree.delete(point8);
        tree.delete(pointNE);
        tree.delete(pointNW);
        tree.delete(pointSW);
        tree.delete(pointSE);
        assertFalse(tree.find(point));
        assertFalse(tree.find(point1));
        assertFalse(tree.find(point2));
        assertFalse(tree.find(point3));
        assertFalse(tree.find(point4));
        assertFalse(tree.find(point5));
        assertFalse(tree.find(point6));
        assertFalse(tree.find(point7));
        assertFalse(tree.find(point8));
        assertFalse(tree.find(pointNE));
        assertFalse(tree.find(pointNW));
        assertFalse(tree.find(pointSW));
        assertFalse(tree.find(pointSE));
        tree.regionSearch(256, 128, 256, 128);
    }

    /**
     * This method will
     */
    public void testDupes()
    {
        System.out.println("DUPES");
        Point point10 = new Point("Y", 10, 10);
        Point point11 = new Point("U", 10, 10);
        assertTrue(point10.sameXY(point11));
        tree.insert(point10);
        tree.insert(point11);
        tree.duplicateFind();
    }

    /**
     * This method will
     */
    public void testDelete1()
    {
        System.out.println("HERE!");
        point1 = new Point("point1", 1, 1);
        point2 = new Point("point2", 100, 100);
        point3 = new Point("point3", 10, 50);
        point4 = new Point("point4", 1024, 1024);
        tree.regionSearch(0, 0, 1024, 1024);
        tree.insert(point1);
        tree.insert(point1);
        tree.insert(point1);
        tree.regionSearch(0, 0, 1024, 1024);
        tree.insert(point2);
        tree.insert(point2);
        tree.regionSearch(0, 0, 1024, 1024);
        tree.dump();
        System.out.println("HERE@!");
        tree.delete(point2);
        assertFalse(tree.isEmpty());
        tree.dump();
    }

}
