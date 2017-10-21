/**
 * This class will
 *
 * @author Siddharth Hingorani (sid97)
 * @version 2016.xx.xx
 *
 * @param <T>
 *            the abstarct value
 */
public interface Comparable2D<T>
{
    /**
     * This method will
     * 
     * @param x
     *            to compare to
     * @return the in
     */
    public int compareToX(int x);

    /**
     * This method will
     * 
     * @param y
     *            to compare to
     * @return the int
     */
    public int compareToY(int y);

    /**
     * This method will
     * 
     * @return the string
     */
    public String toStringNoName();

    /**
     * This method will
     * 
     * @param value
     *            to compare to
     * @return true if they have same x and y
     */
    public boolean sameXY(T value);

}