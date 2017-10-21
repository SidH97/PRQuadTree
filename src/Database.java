import java.util.Iterator;

/**
 * This class is a work in progress. Once BST is imported it will work. Need to
 * implement pr quad tree in here. Need to replace rectangle with point.
 *
 * @author Siddharth Hingorani (sid97)
 * @version 2016.xx.xx
 *
 */
public class Database
{
    private PrQuadTree<Point> prTree;
    private BST<Point> bstTree;

    /**
     * This is our default constructor
     *
     */
    public Database()
    {
        prTree = new PrQuadTree<Point>();
        bstTree = new BST<Point>();

    }

    /**
     * This method will insert point
     * 
     * @param name
     *            name
     * @param x
     *            cord
     * @param y
     *            cord
     */
    public void insertPoint(String name, int x, int y)
    {
        Point temp = new Point(name, x, y);
        if (x >= 0 && y >= 0)
        {
            if ((x <= 1024) && (y <= 1024))
            {
                if (Character.isDigit(name.charAt(0)))
                {
                    System.out.println("Point Rejected: " + temp.toString());
                }
                else
                {
                    bstTree.insert(temp);
                    prTree.insert(temp);
                    System.out.println("Point Inserted: " + temp.toString());
                }
            }
            else
            {
                System.out.println("Point Rejected: " + temp.toString());
            }
        }
        else
        {
            System.out.println("Point Rejected: " + temp.toString());
        }

    }

    /**
     * This method will remove by dimensions
     * 
     * @param x
     *            cord
     * @param y
     *            cord
     */
    public void removeBydimensions(int x, int y)
    {
        boolean found = false;
        Point holder = null;
        Iterator<Point> treeSearch = bstTree.iterator(); // creates first
        // iterator
        while (treeSearch.hasNext())
        { // goes through the tree once
            holder = treeSearch.next();
            if (holder.xcord == x && holder.ycord == y)
            {
                try
                {
                    bstTree.remove(holder);
                    prTree.delete(holder); // remove rectangle
                                           // here
                    found = true;
                    break;
                }
                catch (Exception e)
                {
                    // intentionally blank
                }
            }
        }
        if (!found)
        {
            System.out.println("Point Rejected: (" + x + ", " + y + ")");
        }
    }

    /**
     * This method will remove
     * 
     * @param word
     *            to rmeove
     */
    public void removeByName(String word)
    {
        boolean found = false;
        Point searchRec = new Point(word, 1, 1);
        Iterator<Point> treeSearch = bstTree.iterator(); // creates first
                                                         // iterator
        while (treeSearch.hasNext())
        { // goes through the tree once
            Point holder = treeSearch.next();
            if (holder.compareTo(searchRec) == 0)
            {
                try
                {
                    bstTree.remove(holder);
                    prTree.delete(holder); // remove rectangle
                                           // here
                    found = true;
                    break;
                }
                catch (Exception e)
                {
                    // to do
                }
            }
        }
        if (!found)
        {
            System.out.println("Point Rejected: " + word);
        }

    }

    /**
     * This method will search the region.
     * 
     * @param x
     *            of region.
     * @param y
     *            of region.
     * @param width
     *            of region.
     * @param height
     *            of region.
     */
    public void regionSearch(int x, int y, int width, int height)
    {
        if (width > 0 && height > 0)
        {

            System.out.println("Points Intersecting Region: (" + x + ", " + y
                            + ", " + width + ", " + height + ")");
            prTree.regionSearch((x + width), x, (y + height), y);

        }
        else
        {
            System.out.println("Invalid Region: (" + x + ", " + y + ", " + width
                            + ", " + height + ")");
        }

    }

    /**
     * This method will search for rec in our bst by name only.
     * 
     * @param name
     *            to be searched.
     */
    public void search(String name)
    {
        boolean found = false;
        Point searchRec = new Point(name, 1, 1);
        Iterator<Point> treeSearch = bstTree.iterator(); // creates firs
        // iterator
        while (treeSearch.hasNext())
        { // goes through the tree once
            Point holder = treeSearch.next();
            if (holder.name.equals(searchRec.name))
            {
                System.out.println("Point found: " + holder.toString());
                found = true;
            }
        }
        if (!found)
        {
            System.out.println("Point not found: " + searchRec.name);
        }
    }

    /**
     * This method will
     */
    public void duplicates()
    {
        System.out.println("Duplicate Points:");
        prTree.duplicateFind();

    }

    /**
     * This method will
     */
    public void dump()
    {
        bstTree.dump();
        Iterator<Point> numSearch = bstTree.iterator();
        int size = 0;
        while (numSearch.hasNext())
        {
            size++;
            numSearch.next();
        }
        System.out.println("BST size is: " + size);
        System.out.println("QuadTree Dump:");
        prTree.dump();

    }

}
