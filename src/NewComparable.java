public interface NewComparable<T>
{
    // /**
    // * x and y is always the mid point
    // * and arg0 is always the point in question
    // * 0 means that the mid point and point are equal
    // */
    // public int compareTo(Point arg0, int x, int y) {
    // if((this.getY() == arg0.getY()) && (this.getX() == arg0.getX())) {
    // return 0;
    // } else if((y <= arg0.getY())&&(x < arg0.getX())) {
    // return 1;
    // } else if((y < arg0.getY())&&(x >= arg0.getX())) {
    // return 2;
    // } else if((y >= arg0.getY())&&(x > arg0.getX())) {
    // return 3;
    // } else if((y > arg0.getY())&&(x <= arg0.getX())) {
    // return 4;
    // } else {
    // //this should never happen
    // return -1;
    // }
    // }

    public int compareToX(int x);

    public int compareToY(int y);
}