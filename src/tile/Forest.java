package tile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Forest extends Tile implements Buildable{

	public static final Image background = new Image("Forest.jpg");
	public Forest() {
		super();
		movement = 2;
		ImageView bg = new ImageView();
		bg.setImage(background);
		bg.setFitHeight(gettileSizeY());
		bg.setFitWidth(gettileSizeX());
		this.getChildren().add(bg);
	}
}
