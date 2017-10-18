import java.awt.Rectangle;

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

    public Database()
    {
        prTree = new PrQuadTree<Point>();
    }

    public void insertPoint(String name, int x, int y)
    {
        Rectangle temp = new Rectangle(name, x, y, width, height);
        if (x >= 0 && y >= 0)
        {
            if (width > 0 && height > 0)
            {
                if ((x + width <= 1024) && (y + height <= 1024))
                {
                    if (Character.isDigit(name.charAt(0)))
                    {
                        System.out.println("Rectangle rejected: "
                                        + temp.toString());
                    }
                    else
                    {
                        bst.insert(temp);
                        System.out.println("Rectangle accepted: "
                                        + temp.toString());
                    }
                }
                else
                {
                    System.out.println(
                                    "Rectangle rejected: " + temp.toString());
                }
            }
            else
            {
                System.out.println("Rectangle rejected: " + temp.toString());
            }
        }
        else
        {
            System.out.println("Rectangle rejected: " + temp.toString());
        }
    }

    public void removeBydimensions(int x, int y)
    {
        boolean found = false;
        Rectangle holder = null;
        Iterator<Rectangle> treeSearch = bst.iterator(); // creates first
                                                         // iterator
        while (treeSearch.hasNext())
        { // goes through the tree once
            holder = treeSearch.next();
            if (holder.xcord == x && holder.ycord == y
                            && holder.recWidth == width
                            && holder.recHeight == height)
            {
                try
                {
                    bst.remove(holder); // remove rectangle here
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
            System.out.println("Rectangle rejected (" + x + ", " + y + ", "
                            + width + ", " + height + ")");
        }
    }

    public void removeByName(String word)
    {
        boolean found = false;
        Rectangle searchRec = new Rectangle(name, 1, 1, 1, 1);
        Iterator<Rectangle> treeSearch = bst.iterator(); // creates first
                                                         // iterator
        while (treeSearch.hasNext())
        { // goes through the tree once
            Rectangle holder = treeSearch.next();
            if (holder.compareTo(searchRec) == 0)
            {
                try
                {
                    bst.remove(holder); // remove rectangle here
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
            System.out.println("Rectangle rejected " + name);
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
        System.out.println("Rectangles intersecting region(" + x + ", " + y
                        + ", " + width + ", " + height + "):");
        Rectangle region = new Rectangle("region", x, y, width, height);
        Iterator<Rectangle> treeSearch = bst.iterator();
        while (treeSearch.hasNext())
        {
            Rectangle holder = treeSearch.next();
            if (holder.checkRegion(region))
            {
                System.out.println(holder.toString());
            }
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
        Rectangle searchRec = new Rectangle(name, 1, 1, 1, 1);
        Iterator<Rectangle> treeSearch = bst.iterator(); // creates first
                                                         // iterator
        while (treeSearch.hasNext())
        { // goes through the tree once
            Rectangle holder = treeSearch.next();
            if (holder.name.equals(searchRec.name))
            {
                System.out.println("Rectangle found: " + holder.toString());
                found = true;
            }
        }
        if (!found)
        {
            System.out.println("Rectangle not found: " + searchRec.name);
        }
    }

    public void duplicates()
    {
        // TODO Auto-generated method stub

    }

}
