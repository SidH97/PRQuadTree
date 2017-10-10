
public class point implements Comparable<point>{
	public String name;
	public int xcord;
	public int ycord;
	
	public point(String title, int x, int y)
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
	 * this is always the mid point
	 * and arg0 is always the point in question
	 * 0 means that the mid point and point are equal
	 */
	@Override
	public int compareTo(point arg0) {
		if((this.getY() == arg0.getY()) && (this.getX() == arg0.getX())) {
			return 0;
		} else if((this.getY() <= arg0.getY())&&(this.getX() < arg0.getX())) {
			return 1;
		} else if((this.getY() < arg0.getY())&&(this.getX() >= arg0.getX())) {
			return 2;
		} else if((this.getY() >= arg0.getY())&&(this.getX() > arg0.getX())) {
			return 3;
		} else if((this.getY() > arg0.getY())&&(this.getX() <= arg0.getX())) {
			return 4;
		} else {
			//this should never happen
			return -1;
		}
		
	}
}
