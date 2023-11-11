package game_logic;

public class Point {

	private int x;
	public int getX() {
		return x;
	}




	public void setX(int x) {
		this.x = x;
	}




	public int getY() {
		return y;
	}




	public void setY(int y) {
		this.y = y;
	}




	private int y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public static int randomPointValue()
	{  
     return (int)Math.floor(Math.random()*2.9);
	}
	
	
	public static Point intToPoint(int i)
	{
		int x;
		int y;
		x = (i % 3 == 0) ? (i / 3) - 1 : i / 3;
		y = i-(x*3)-1;
		
		return new Point(x,y);
	}

	public static int pointToInt(Point point)
	{
		return (point.getX()*2)+point.getY()+point.getX()+1;
	}
}
