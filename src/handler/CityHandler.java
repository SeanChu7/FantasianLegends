package handler;
import java.util.ArrayList;

import city.Building;
import city.City;
import city.CityCenter;
import faction.Faction;
import javafx.geometry.Insets;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import screen.Map;
import unit.Settler;

public class CityHandler {
	public static ArrayList<City> cities = new ArrayList<City>();
	public static void spawnCity(Settler s) {
		City c = new City(s.getOwner());
		s.getOwner().getCities().add(c);
		c.setX(s.getX());
		c.setY(s.getY());
		spawnBuilding(new CityCenter(s.getX(),s.getY()),c,s.getOwner());
		UnitControl.die(s);
		GameHandler.deSelectUnit();
		GameHandler.updateYield(s.getOwner());
		cities.add(c);
		s.getOwner().harvest();
	}
	public static void spawnCityAI(Settler s) {
		City c = new City(s.getOwner());
		s.getOwner().getCities().add(c);
		c.setX(s.getX());
		c.setY(s.getY());
		spawnBuilding(new CityCenter(s.getX(),s.getY()),c,s.getOwner());
		UnitControl.die(s);
		cities.add(c);
	}
	public static void spawnBuilding(Building b, City c, Faction f) {
		Map.buildingMap[b.getX()][b.getY()] = f.getNum();
		c.getBuildings().add(b);
		Map.tileMap[b.getX()][b.getY()].displayBuilding();
		calcTerritory(b,c,f);
	}
	public static void burnCity(City c) {
		for (Building b: c.getBuildings()) {
			Map.buildingMap[b.getX()][b.getY()] = 0;
			Map.tileMap[b.getX()][b.getY()].removeBuilding();
		}
		for (Coordinate z : c.getTiles()) {
			Map.tileMap[z.getX()][z.getY()].removeShade();
		}
	}
	public static void calcTerritory(Building b, City c,Faction f) {
		c.getTiles().add(new Coordinate(b.getX(),b.getY()));
		Map.tileMap[b.getX()][b.getY()].setShade(SetShade(f.getColor()));
		if (b.getX()+1 < Map.moveMap.length) {
		c.getTiles().add(new Coordinate(b.getX()+1,b.getY()));
		Map.tileMap[b.getX()+1][b.getY()].setShade(SetShade(f.getColor()));
		}
		if (b.getX()-1 >= 0) {
			c.getTiles().add(new Coordinate(b.getX()-1,b.getY()));
			Map.tileMap[b.getX()-1][b.getY()].setShade(SetShade(f.getColor()));
			}
		if (b.getY()+1 < Map.moveMap[0].length) {
			c.getTiles().add(new Coordinate(b.getX(),b.getY()+1));
			Map.tileMap[b.getX()][b.getY()+1].setShade(SetShade(f.getColor()));
			}
		if (b.getY()-1 >= 0) {
			c.getTiles().add(new Coordinate(b.getX(),b.getY()-1));
			Map.tileMap[b.getX()][b.getY()-1].setShade(SetShade(f.getColor()));
			}
	}
	public static Pane SetShade(Color c) {
		Pane shade = new Pane();
		RadialGradient shadePaint = new RadialGradient(
	            0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
	            new Stop(1, c),
	            new Stop(0, Color.TRANSPARENT)
	    );
		shade.setBackground(
	   		 new Background(
	                    new BackgroundFill(
	                            shadePaint, null, Insets.EMPTY
	                    )
	            )
	    );

	    // blur helps reduce visible banding of the radial gradient.
	   shade.setEffect(new BoxBlur(5, 5, 3));
	   return shade;
	}
}
