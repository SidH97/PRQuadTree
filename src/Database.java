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

    public Database()
    {
        prTree = new PrQuadTree<Point>();
        bstTree = new BST<Point>();

    }

    public void insertPoint(String name, int x, int y)
    {
        Point temp = new Point(name, x, y);
        if (x >= 0 && y >= 0)
        {
            if ((x <= 1024) && (y <= 1024))
            {
                if (Character.isDigit(name.charAt(0)))
                {
                    System.out.println("Point rejected: " + temp.toString());
                }
                else
                {
                    bstTree.insert(temp);
                    prTree.insert(temp);
                    System.out.println("Point accepted: " + temp.toString());
                }
            }
            else
            {
                System.out.println("Point rejected: " + temp.toString());
            }
        }
        else
        {
            System.out.println("Point rejected: " + temp.toString());
        }

    }

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
                    prTree.remove(holder); // remove rectangle here
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
            System.out.println("Point rejected (" + x + ", " + y + ")");
        }
    }

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
                    prTree.remove(holder); // remove rectangle here
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
            System.out.println("Point rejected " + word);
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
        // TODO Auto-generated method stub
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
        Iterator<Point> treeSearch = bstTree.iterator(); // creates first
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

    public void duplicates()
    {
        // TODO Auto-generated method stub

    }

}
