package handler;
import city.City;
import faction.Faction;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import screen.Map;
import screen.RightBar;
import tile.Buildable;
import unit.RangeUnit;
import unit.Settler;
import unit.Unit;

public class ProcessButtons {

	public static void processStartbuttons(Button[] buttons, Stage primaryStage) {
		buttons[0].setOnAction(e -> {GameHandler.initiate(primaryStage);;;});
		buttons[1].setOnAction(e -> {System.out.println("Tutorial");});
		buttons[2].setOnAction(e -> {primaryStage.close();});
		buttons[2].setCancelButton(true);
	}
	public static void processSelectedCity(City c) {
		for(int i = 0; i < Map.tileMap.length; i++) {
			for (int g = 0 ; g < Map.tileMap[i].length; g++) {
				Map.tileMap[i][g].setOnMouseClicked(e->GameHandler.deSelectCity());
			}
		}
		for (Coordinate g: c.getTiles()) {
			if(Map.buildingMap[g.getX()][g.getY()] == 0 && (Map.tileMap[g.getX()][g.getY()] instanceof Buildable))
				Map.tileMap[g.getX()][g.getY()].setOnMouseClicked(e->GameHandler.constructBuilding(c,g));
		}
		
	}
	public static void processMap(Faction f, RightBar r) {
		resetAll();
		for(City c: f.getCities()) {
			Map.tileMap[c.getX()][c.getY()].setOnMouseClicked(e->GameHandler.selectCity(c));
		}
		for (Unit u : UnitControl.units) {
			if (GameHandler.moveAbleUnit.contains(u)) {
				Map.tileMap[u.getX()][u.getY()].setOnMouseClicked(e->GameHandler.selectUnit(u));
			}
			else {
				Map.tileMap[u.getX()][u.getY()].setOnMouseClicked(e->GameHandler.displayUnit(u));
			}
		}
		r.endTurn.setOnMouseClicked(e->GameHandler.endTurn());
	}
	public static void resetAll() {
		for(int i = 0; i < Map.tileMap.length; i++) {
			for (int g = 0 ; g < Map.tileMap[i].length; g++) {
				Map.tileMap[i][g].setOnMouseClicked(null);
			}
		}
	}
	public static void processSelectedUnit(Unit u,RightBar r) {
		for(int i = 0; i < Map.tileMap.length; i++) {
			for (int g = 0 ; g < Map.tileMap[i].length; g++) {
				Map.tileMap[i][g].setOnMouseClicked(e->GameHandler.deSelectUnit());
			}
		}
		for (Coordinate c: UnitControl.inRangeUnit) {
			if (u.getRng() > 1)
				Map.tileMap[c.getX()][c.getY()].setOnMouseClicked(e->ProcessCombat.Rangedattack((RangeUnit)u,c.getX(),c.getY()));
			else
				Map.tileMap[c.getX()][c.getY()].setOnMouseClicked(e->ProcessCombat.attack(u,c.getX(),c.getY()));
		}
		for (Coordinate c: UnitControl.inRangeTile) {
			Map.tileMap[c.getX()][c.getY()].setOnMouseClicked(e->UnitControl.move(u, c.getX(), c.getY()));
		}
		r.unitDie.setOnMouseClicked(e->{UnitControl.die(u);GameHandler.deSelectUnit();});
		if (u instanceof Settler) {
			r.settle.setOnMouseClicked(e->CityHandler.spawnCity((Settler) u));
		}
	}
}
