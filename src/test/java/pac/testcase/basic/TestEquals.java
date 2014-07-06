package pac.testcase.basic;

import java.awt.Color;


public class TestEquals {
	public static void main(String[] args) {
	}
}

class Point {
	private final int x;
	private final int y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point))
			return false;
		Point p = (Point) obj;
		return p.x == x && p.y == y;
	}

}

class ColorPoint {
	private final Color color;
	private final Point point;

	public ColorPoint(Color color, Point point) {
		super();
		this.color = color;
		this.point = point;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ColorPoint))
			return obj.equals(this);
		return ((ColorPoint) obj).point.equals(point)
				&& ((ColorPoint) obj).color == color;
	}

}

// enum Color{
// RED,GREEN,BLUE
// }