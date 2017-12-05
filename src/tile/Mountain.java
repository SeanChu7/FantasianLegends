package tile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mountain extends Tile{

	private static final Image background = new Image("Mountain.png");
	public Mountain() {
		super();
		movement = 0;
		ImageView bg = new ImageView();
		bg.setImage(background);
		bg.setFitHeight(gettileSizeY());
		bg.setFitWidth(gettileSizeX());
		this.getChildren().add(bg);
	}
}
