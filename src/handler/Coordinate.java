package handler;

public class Coordinate {

	private int xCor;
	private int yCor;
	public Coordinate(int x, int y) {
		xCor = x;
		yCor = y;
	}
	public int getX() {
		return xCor;
	}
	public int getY() {
		return yCor;
	}
	public boolean equals(Coordinate c) {
		if (xCor == c.getX())
			if (yCor == c.getY())
				return true;
		return false;
	}
}
