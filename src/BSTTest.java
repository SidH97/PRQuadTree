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
        tree.insert(rec);
        tree.insert(rec1);
        tree.insert(rec2);
        tree.insert(rec);
        assertFalse(tree.isEmpty());
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

}