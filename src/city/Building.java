package city;

import javafx.scene.image.Image;

public class Building implements Constructable{
	
	public static final Image Buildingdisplay = new Image("Building.png");
	protected int[] yield;
	protected int cost;
	private int x;
	private int y;
	protected int m;
	protected String name;
	public Building() {
		
	}
	public Building(int xCor, int yCor) {
		x = xCor;
		y = yCor;
	}
	public void setX(int xCor) {
		x = xCor;
	}
	public void setY(int yCor) {
		y = yCor;
	}
	public int getM() {
		return m;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getCost() {
		return cost;
	}
	public int[] getYield() {
		return yield;
	}
	public String toString() {
		return name;
	}
	public Building clone() {
		return new Building();
	}
	public boolean equals(Constructable b) {
		if(b.toString().equals(toString()))
			return true;
		return false;
	}
}
