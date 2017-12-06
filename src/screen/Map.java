package screen;
import java.util.Arrays;
import java.util.Random;
import faction.Faction;
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
	public static BorderPane pane;
	public RightBar rightBar;
	public topBar top;
	public Map(Stage primaryStage, Faction f) {
		pane = new BorderPane();
		top = new topBar(f,primaryStage.getWidth(),primaryStage);
		rightBar = new RightBar(f,findBounds(primaryStage.getHeight(),primaryStage.getWidth()),primaryStage);
		GridPane map = new GridPane();
		unitMap = new int[50][60];
		buildingMap = new int[50][60];
		tileMap = new Tile[50][60];
		moveMap = new int[50][60];
		int[][] testMap = Mapmaker.makeMap();
		for (int g = 0; g < testMap.length; g++) {
			for (int i = 0; i < testMap[0].length; i++) {
					if(testMap[g][i] == 0) {
						Grassland o = new Grassland();
						map.add(o, i, g);
						moveMap[g][i] = 1;
						tileMap[g][i] = o;
					}
					else if(testMap[g][i] == 1) {
						Forest o = new Forest();
						map.add(o, i, g);
						moveMap[g][i] = 2;
						tileMap[g][i] = o;
					}
					else if(testMap[g][i] == 2) {
						Hill o = new Hill();
						map.add(o, i, g);
						moveMap[g][i] = 2;
						tileMap[g][i] = o;
					}
					else if(testMap[g][i] == 3) {
						Mountain o = new Mountain();
						map.add(o, i, g);
						moveMap[g][i] = 0;
						tileMap[g][i] = o;
					}
					else if(testMap[g][i] == 4) {
						Ocean o = new Ocean();
						map.add(o, i, g);
						moveMap[g][i] = 0;
						tileMap[g][i] = o;
					}
					else if(testMap[g][i] == 5) {
						Ice o = new Ice();
						map.add(o, i, g);
						moveMap[g][i] = 1;
						tileMap[g][i] = o;
					}
					else if(testMap[g][i] == 6) {
						Rockland o = new Rockland();
						map.add(o, i, g);
						moveMap[g][i] = 1;
						tileMap[g][i] = o;
					}
				}
			}
		pane.setTop(top);
		pane.setCenter(rightBar);
		pane.setLeft(map);
		primaryStage.getScene().setRoot(pane);;	
	}
	public static void back(Stage primaryStage) {
		primaryStage.getScene().setRoot(pane);
	}
	public Double findBounds(Double height, Double width) {
		Double mapheight = height - 30;
		Double mapwidth = width - 120;
		Tile.settileSizeX(mapwidth/60);
		Tile.settileSizeY(mapheight/50);
		return mapheight;
	}
}
