package tile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Grassland extends Tile{

	public static final Image background = new Image("Grassland.jpg");
	public Grassland() {
		super();
		movement = 1;
		ImageView bg = new ImageView();
		bg.setImage(background);
		bg.setFitHeight(gettileSizeY());
		bg.setFitWidth(gettileSizeX());
		this.getChildren().add(bg);
	}
}
