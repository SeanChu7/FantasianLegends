package unit;

import city.Constructable;
import faction.Faction;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
public class Unit implements Constructable{

	public static final Image Unitdisplay = new Image("Unit.png");
	private Faction owner;
	protected int movement;
	protected int movesleft;
	protected int x;
	protected int y;
	protected int health;
	protected int meleeStr;
	protected int cost;
	protected int maint;
	protected String name;
	protected int rng = 1;
	public Unit() {
		
	}
	public Unit(int m, int xCor, int yCor, int cos,int h, int melee,int maintenance) {
		movement = m;
		movesleft = m;
		x = xCor;
		y = yCor;
		health = h;
		meleeStr = melee;
		cost = cos;
		maint = maintenance;
	}
	public Faction getOwner() {
		return owner;
	}
	public void setOwner(Faction f) {
		owner = f;
	}
	public int getRng() {
		return rng;
	}
	public int getMoveLeft() {
		return movesleft;
	}
	public void setMovesLeft(int x) {
		movesleft = Math.max(x, 0);
	}
	public String toString() {
		return name;
	}
	public int getMaint() {
		return maint;
	}
	public int getCost() {
		return cost;
	}
	public int getHealth() {
		return health;
	}
	public int getMelee() {
		return meleeStr;
	}
	public int getMovement() {
		return movement;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int xCor) {
		x = xCor;
	}
	public void setY(int yCor) {
		y = yCor;
	}
	public void setMelee(int m) {
		meleeStr = m;
	}
	public void setHealth(int h) {
		health = h;
	}
	public VBox displayInfo() {
		VBox unitInf = new VBox();
		unitInf.getChildren().add(new Text(name));
		unitInf.getChildren().add(new Text("Health: " + health+"/" + 100));
		unitInf.getChildren().add(new Text("MeleeStrength: " + meleeStr));
		unitInf.getChildren().add(new Text("Movement: " + movesleft+"/"+movement));
		for (Node t : unitInf.getChildren()) {
			((Text)t).setStroke(Color.WHITE);
		}
		return unitInf;	
	}
	public Unit clone() {
		return new Unit();
	}
	public boolean equals(Constructable u) {
		if(u.toString().equals(toString()))
			return true;
		return false;
	}
}
