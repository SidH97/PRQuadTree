
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

}