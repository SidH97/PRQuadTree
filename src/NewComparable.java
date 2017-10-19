public interface NewComparable<T>
{
    public int compareToX(int x);

    public int compareToY(int y);

    public String toStringNoName();

    public boolean sameXY(T value);

}