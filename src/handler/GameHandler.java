package handler;
import java.util.ArrayList;

import city.City;
import city.Constructable;
import faction.Blobular;
import faction.Faction;
import faction.Floronis;
import faction.Reptar;
import faction.Zenroyo;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import screen.FactionScreen;
import screen.Map;
import unit.FlyingUnit;
import unit.Salamander;
import unit.Settler;
import unit.Unit;

public class GameHandler {
	public static Map map;
	public static Faction f;
	public static ArrayList<Unit> moveAbleUnit;
	public static ArrayList<Faction> aiFactions;
	public static void initiate(Stage primaryStage) {
		FactionScreen temp = new FactionScreen();
		Floronis floronis = new Floronis();
		Reptar reptar = new Reptar();
		Zenroyo zenroyo = new Zenroyo();
		Blobular blobular = new Blobular();
		aiFactions = new ArrayList<Faction>();
		aiFactions.add(blobular);
		aiFactions.add(reptar);
		aiFactions.add(zenroyo);
		aiFactions.add(floronis);
		temp.selectFaction(aiFactions, primaryStage);
		//Switch Screens to Faction Selection
		//Then Go to the Game Map
	}
	public static void play(Stage primaryStage) {
		f.setNum(1);
		f.setColor(Color.FUCHSIA);
		map = new Map(primaryStage,f);
		aiFactions.remove(f);
		
		updateYield(f);
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
		map.rightBar.clearDisplay();
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
		/*for (Faction g: aiFactions) {
			AIControl.AIaction(g);
		}*/
		ProcessButtons.processMap(f, map.rightBar);
		}
	}
}
