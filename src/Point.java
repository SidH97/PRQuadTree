
public class Point implements Comparable<Point>
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

    /**
     * x and y is always the mid point and arg0 is always the point in question
     * 0 means that the mid point and point are equal
     */
    public int compareTo(Point arg0, int x, int y)
    {
        if ((this.getY() == arg0.getY()) && (this.getX() == arg0.getX()))
        {
            return 0;
        }
        else if ((y <= arg0.getY()) && (x < arg0.getX()))
        {
            return 1;
        }
        else if ((y < arg0.getY()) && (x >= arg0.getX()))
        {
            return 2;
        }
        else if ((y >= arg0.getY()) && (x > arg0.getX()))
        {
            return 3;
        }
        else if ((y > arg0.getY()) && (x <= arg0.getX()))
        {
            return 4;
        }
        else
        {
            // this should never happen
            return -1;
        }
    }

    /**
     * Compares by name and test
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
