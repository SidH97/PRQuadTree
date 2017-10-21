/**
 * This class will
 *
 * @author Siddharth Hingorani (sid97)
 * @version 2016.xx.xx
 *
 */
public class Point implements Comparable<Point>, Comparable2D<Point>
{
    /**
     * This field will
     */
    public String name;
    /**
     * This field will
     */
    public int xcord;
    /**
     * This field will
     */
    public int ycord;

    /**
     * This is our default constructor
     *
     * @param title
     *            name
     * @param x
     *            the xcord
     * @param y
     *            cord
     */
    public Point(String title, int x, int y)
    {
        name = title;
        xcord = x;
        ycord = y;
    }

    /**
     * This method will
     * 
     * @return the x
     */
    public int getX()
    {
        return xcord;
    }

    /**
     * This method will
     * 
     * @return the y
     */
    public int getY()
    {
        return ycord;
    }

    /**
     * This method will
     * 
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * overwrriten method
     * 
     * @param x
     *            cord
     * @return the int
     */
    public int compareToX(int x)
    {
        if (this.getX() > x)
        {
            return 1;
        }
        else if (this.getX() == x)
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }

    /**
     * this > that 1 this = that 0 this < that -1
     * 
     * @param y
     *            cord
     * @return the int
     */
    public int compareToY(int y)
    {
        if (this.getY() > y)
        {
            return 1;
        }
        else if (this.getY() == y)
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }

    /**
     * Compares by name
     * 
     * @param shape
     *            to compare
     * @return the int
     */
    @Override
    public int compareTo(Point shape)
    {
        return this.name.compareTo(shape.name);
    }

    /**
     * Prints the rectangle according to our needs
     * 
     * @return the name of the rectangle
     */
    @Override
    public String toString()
    {
        return "(" + name + ", " + xcord + ", " + ycord + ")";

    }

    /**
     * This method checks to see if 2 points are equal.
     * 
     * @param ct
     *            the point to compare to
     * @return true if the points are equal false if not.
     */
    public boolean sameXY(Point ct)
    {
        return ((this.getX() == ct.getX()) && (this.getY() == ct.getY()));
    }

    /**
     * the to stringNo name
     * 
     * @return the string with no name.
     */
    public String toStringNoName()
    {
        return "(" + xcord + ", " + ycord + ")";
    }

}
