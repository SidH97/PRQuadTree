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
    private Point pnt;
    private Point pnt1;
    private Point pnt2;

    /**
     * This method will setUp the testing Environment.
     */
    public void setUp()
    {
        tree = new BST<Point>();
        pnt = new Point("baa", 1, 1);
        pnt1 = new Point("aaa", 1, 1);
        pnt2 = new Point("caa", 1, 1);
    }

    /**
     * This method will test the insert Method.
     */
    public void testInsert()
    {
        tree.insert(pnt);
        tree.insert(pnt1);
        tree.insert(pnt2);
        tree.insert(pnt);
        assertFalse(tree.isEmpty());
        assertEquals(pnt, tree.find(pnt));
    }

    /**
     * This method will the remove method.
     */
    public void testRemove()
    {
        Exception out = null;
        tree.insert(pnt);
        tree.insert(pnt);
        try
        {
            tree.remove(pnt);
        }
        catch (Exception e1)
        {
            out = e1;
        }
        assertNull(out);
        assertFalse(tree.isEmpty());
        try
        {
            tree.remove(pnt);
        }
        catch (Exception e1)
        {
            out = e1;
        }
        assertNull(out);
        assertTrue(tree.isEmpty());
        try
        {
            tree.remove(pnt);
        }
        catch (Exception e)
        {
            out = e;
        }
        assertNotNull(out);
    }

    /**
     * This method will test the find() Method.
     */
    public void testFind()
    {
        tree.insert(pnt);
        tree.insert(pnt1);
        assertEquals(pnt, tree.find(pnt));
        assertNull(tree.find(pnt2));
    }

    /**
     * This method will test Make Empty
     */
    public void testMakeEmpty()
    {
        tree.insert(pnt);
        assertFalse(tree.isEmpty());
        tree.makeEmpty();
        assertTrue(tree.isEmpty());
    }

}