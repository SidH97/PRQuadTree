/**
 * 
 */

/**
 * This class will test our BST.
 *
 * @author Siddharth Hingorani (sid97)
 * @author Matthew Evans (mattce93)
 * @version 09.16.2017
 *
 */
public class BSTTest extends student.TestCase
{

    private BST<Point> tree;
    private Point rec;
    private Point rec1;
    private Point rec2;

    /**
     * This method will setUp the testing Environment.
     */
    public void setUp()
    {
        tree = new BST<Point>();
        rec = new Point("baa", 1, 1);
        rec1 = new Point("aaa", 1, 1);
        rec2 = new Point("caa", 1, 1);
    }

    /**
     * This method will test the insert Method.
     */
    public void testInsert()
    {
        assertTrue(tree.isEmpty());
        tree.insert(rec);
        assertEquals(rec, tree.find(rec));
        tree.insert(rec1);
        assertEquals(rec1, tree.find(rec1));
        tree.insert(rec2);
        assertEquals(rec2, tree.find(rec2));
        tree.insert(rec);
        assertFalse(tree.isEmpty());
        rec = new Point("ZZ", 2, 3);
        tree.insert(rec);
        tree.insert(rec);
        assertEquals(rec, tree.find(rec));
    }

    /**
     * This method will the remove method.
     */
    public void testRemove()
    {
        Exception out = null;
        tree.insert(rec);
        tree.insert(rec);
        try
        {
            tree.remove(rec);
        }
        catch (Exception e1)
        {
            out = e1;
        }
        assertNull(out);
        assertFalse(tree.isEmpty());
        try
        {
            tree.remove(rec);
        }
        catch (Exception e1)
        {
            out = e1;
        }
        assertNull(out);
        assertTrue(tree.isEmpty());
        try
        {
            tree.remove(rec);
        }
        catch (Exception e)
        {
            out = e;
        }
        assertNotNull(out);
        assertTrue(tree.isEmpty());
        tree.insert(rec);
        tree.insert(rec);
        tree.insert(rec1);
        tree.insert(rec2);
        try
        {
            tree.remove(rec2);
        }
        catch (Exception e)
        {
            out = e;
        }

    }

    /**
     * This method will test the find() Method.
     */
    public void testFind()
    {
        tree.insert(rec);
        tree.insert(rec1);
        assertEquals(rec, tree.find(rec));
        assertNull(tree.find(rec2));
    }

    /**
     * This method will test Make Empty
     */
    public void testMakeEmpty()
    {
        tree.insert(rec);
        assertFalse(tree.isEmpty());
        tree.makeEmpty();
        assertTrue(tree.isEmpty());
    }

    public void testNew()
    {
        Point point1 = new Point("r_r", 1, 20);
        Point point2 = new Point("rec", 10, 30);
        Point point3 = new Point("r_42", 1, 20);
        Point point4 = new Point("far", 200, 200);

        tree.insert(point1);
        tree.insert(point2);
        tree.insert(point3);
        tree.insert(point4);

        assertEquals(point3, tree.find(point3));
        assertEquals(point4, tree.find(point4));

        try
        {

            tree.remove(point1);
            tree.remove(point2);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(point3, tree.find(point3));
        assertEquals(point4, tree.find(point4));
    }
}