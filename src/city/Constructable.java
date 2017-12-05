package city;

public interface Constructable {

	public int cost = 0;
	public String name = "";
	public int getCost();
	public void setX(int g);
	public void setY(int g);
	public int getX();
	public int getY();
	public String toString();
	public Constructable clone();
	public boolean equals(Constructable c);
}
