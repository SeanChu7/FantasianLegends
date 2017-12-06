package tile;

import city.Building;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane{
	private static Double tileSizeX = 0.0;
	private static Double tileSizeY = 0.0;
	protected int movement;
	private ImageView building;
	private ImageView unit;
	private Rectangle border;
	private Pane owner;
	public Tile() {
	}
	public static Double gettileSizeX() {
		return tileSizeX;
	}
	public static void settileSizeX(double d) {
		tileSizeX = d;
	}
	public static Double gettileSizeY() {
		return tileSizeY;
	}
	public static void settileSizeY(double d) {
		tileSizeY = d;
	}
	public int getMovement() {
		return movement;
	}
	public void displayBuilding() {
		building = new ImageView(Building.Buildingdisplay);
		building.setFitHeight(tileSizeY);
		building.setFitWidth(tileSizeX);
		this.getChildren().add(building);
	}
	public void removeBuilding() {
		this.getChildren().remove(building);
	}
	public void displayUnit(Image i) {
		unit = new ImageView(i);
		unit.setFitHeight(gettileSizeY());
		unit.setFitWidth(gettileSizeX());
		this.getChildren().add(unit);
		unit.toFront();
	}
	public void removeUnit() {
		this.getChildren().remove(unit);
	}
	public void setBorder(Rectangle r) {
		if (border == null) {
		border = r;
		this.getChildren().add(border);
		}
	}
	public void removeBorder() {
		this.getChildren().remove(border);
		border = null;
	}
	public void setShade(Pane p) {
		if (owner == null) {
		owner = p;
		this.getChildren().add(owner);
		}
	}
	public void removeShade() {
		this.getChildren().remove(owner);
		owner =null;
	}
	
}
