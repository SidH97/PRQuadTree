
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

	@Override
	public int compareTo(point arg0) {
		if((this.getY() == arg0.getY()) && (this.getX() == arg0.getX())) {
			return 0;
		} else {
			return 1;
		}
		
	}
}
