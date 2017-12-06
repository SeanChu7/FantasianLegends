package tile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rockland extends Tile implements Buildable{
	public static final Image background = new Image("Rockland.jpg");
	public Rockland() {
		super();
		movement = 1;
		ImageView bg = new ImageView();
		bg.setImage(background);
		bg.setFitHeight(gettileSizeY());
		bg.setFitWidth(gettileSizeX());
		this.getChildren().add(bg);
	}
}
