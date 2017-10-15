/**
 * This class will
 *
 * @author Siddharth Hingorani (sid97)
 * @version 2016.xx.xx
 *
 */
public class Point implements Comparable<Point>, NewComparable<Point>
{
    public String name;
    public int xcord;
    public int ycord;

    public Point(String title, int x, int y)
    {
        name = title;
        xcord = x;
        ycord = y;
    }

    public int getX()
    {
        return xcord;
    }

    public int getY()
    {
        return ycord;
    }

    public String getName()
    {
        return name;
    }

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
     **/
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
     * @return
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
    public boolean equals(Point ct)
    {
        return ((this.getX() == ct.getX()) && (this.getY() == ct.getY())
                        && (this.getName().equals(ct.getName())));
    }

}
