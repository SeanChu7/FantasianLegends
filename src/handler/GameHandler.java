package handler;
import java.util.ArrayList;

import city.City;
import city.Constructable;
import city.Granary;
import faction.Faction;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import screen.Map;
import unit.FlyingUnit;
import unit.RangeUnit;
import unit.Salamander;
import unit.Settler;
import unit.Unit;

public class GameHandler {
	public static Map map;
	public static Faction f;
	public static ArrayList<Unit> moveAbleUnit;
	public static void initiate(Stage primaryStage) {
		//Switch Screens to Faction Selection
		//Then Go to the Game Map
		f = new Faction();
		map = new Map(primaryStage,f);
		f.setNum(1);
		f.getCanBuild().add(new Granary());
		f.setColor(Color.AQUA);
		Unit test = new Unit(6,10,2,3,3,3,3);
		test.setOwner(f);
		UnitControl.spawnUnit(test, test.getX(), test.getY());
		RangeUnit testing2 = new RangeUnit(2, 10, 4, 2,2, 2, 2, 2, 2);
		testing2.setOwner(f);
		UnitControl.spawnUnit(testing2, testing2.getX(), testing2.getY());
		Settler testing = new Settler();
		testing.setOwner(f);
		testing.setX(5);
		testing.setY(8);
		UnitControl.spawnUnit(testing, testing.getX(), testing.getY());
		updateYield(f);
		f.getAvailUnits().add(new Salamander());
		f.harvest();
		moveAbleUnit = new ArrayList<Unit>();
		moveAbleUnit.addAll(f.getUnits());
		map.top.update();
		ProcessButtons.processMap(f,map.rightBar);
	}
	public static void updateYield(Faction g) {
		g.harvest();
		map.top.update();
	}
	public static void selectUnit(Unit u) {
		UnitControl.reset();
		if(u instanceof FlyingUnit)
			UnitControl.showMoveRangeFlying(u.getX(),u.getY(),u.getMoveLeft(),true,u.getRng(),Map.unitMap[u.getX()][u.getY()],0);
		else
			UnitControl.showMoveRange(u.getX(),u.getY(),u.getMoveLeft(),true,u.getRng(),Map.unitMap[u.getX()][u.getY()],0);
		GameHandler.displayUnit(u);
		ProcessButtons.processSelectedUnit(u,map.rightBar);
	}
	public static void selectCity(City c) {
		map.rightBar.displaySelectedCity(c, f);
		ProcessButtons.processSelectedCity(c);
	}
	public static void deSelectCity() {
		map.rightBar.removeDisplay(map.rightBar.cityInf);
		ProcessButtons.processMap(f,map.rightBar);
	}
	public static void constructBuilding(City c,Coordinate g) {
		Constructable b = map.rightBar.canBuild.getSelectionModel().getSelectedItem();
		if (b !=  null)
			c.addConstruct(b,g);
		map.rightBar.updateInfo(c,f);
	}
	public static void deSelectUnit() {
		UnitControl.reset();
		map.rightBar.removeDisplay(map.rightBar.unitInf);
		ProcessButtons.processMap(f,map.rightBar);
	}
	public static void displayUnit(Unit u) {
		map.rightBar.removeDisplay(map.rightBar.unitInf);
		map.rightBar.displaySelectedunit(u);
	}
	public static void endTurn() {
		boolean ready = true;
		for (City c: f.getCities()) {
			if (c.getConstruction().peek() == null)
				ready = false;
		}
		if (ready) {
			if(f.getCities().size()!=0) {
				for (City c: f.getCities()) {
					c.calcConstruct();
					c.calcFood();
				}
			}
		f.calcGold();
		map.top.update();
		map.rightBar.updateNotif();
		moveAbleUnit = new ArrayList<Unit>();
		moveAbleUnit.addAll(f.getUnits());
		for (int i = 0; i < moveAbleUnit.size(); i++) {
			moveAbleUnit.get(i).setMovesLeft(moveAbleUnit.get(i).getMovement());
		}
		ProcessButtons.processMap(f, map.rightBar);
		}
	}
}
