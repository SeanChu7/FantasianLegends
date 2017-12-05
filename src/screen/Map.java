package screen;
import java.util.Random;

import faction.Faction;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tile.*;
public class Map{
	Random r = new Random();
	public static int[][] unitMap;
	public static int[][] moveMap;
	public static Tile[][] tileMap;
	public static int[][] buildingMap;
	public RightBar rightBar;
	public topBar top;
	public Map(Stage primaryStage, Faction f) {
		BorderPane pane = new BorderPane();
		top = new topBar(f);
		rightBar = new RightBar(f,findBounds(primaryStage.getHeight(),primaryStage.getWidth()));
		GridPane map = new GridPane();
		unitMap = new int[60][50];
		buildingMap = new int[60][50];
		tileMap = new Tile[60][50];
		moveMap = new int[60][50];
		for (int i = 0; i < 60; i++) {
			for (int g = 0; g < 50; g++) {
				switch(r.nextInt(5)) {
				case 0: Ocean o = new Ocean();
					map.add(o, i, g);
					tileMap[i][g] = o;
					moveMap[i][g] =0;
					break;
				case 1: Mountain m = new Mountain(); 
					map.add(m, i, g);
					tileMap[i][g] = m;
					moveMap[i][g] = 0;
					break;
				case 2: Forest fr = new Forest();
					map.add(fr, i, g);
					tileMap[i][g] = fr;
					moveMap[i][g] = 2;
					break;
				case 3: Hill h = new Hill();
					map.add(h, i, g);
					tileMap[i][g] = h;
					moveMap[i][g] = 2;
					break;
				case 4: Grassland l = new Grassland();
					map.add(l, i, g);
					tileMap[i][g] = l;
					moveMap[i][g] = 1;
					break;
				}
			}
		}
		pane.setTop(top);
		pane.setCenter(rightBar);
		pane.setLeft(map);
		primaryStage.getScene().setRoot(pane);;
		
	}
	
	public Double findBounds(Double height, Double width) {
		Double mapheight = height - 30;
		Double mapwidth = width - 120;
		Tile.settileSizeX(mapwidth/60);
		Tile.settileSizeY(mapheight/50);
		return mapheight;
	}
}
