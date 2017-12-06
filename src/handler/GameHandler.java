package handler;
import java.util.ArrayList;

import city.City;
import city.Constructable;
import city.Granary;
import faction.Blobular;
import faction.Faction;
import faction.Floronis;
import faction.Reptar;
import faction.Zenroyo;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import research.AdvancedFarming;
import research.Foresting;
import research.Research;
import screen.FactionScreen;
import screen.Map;
import screen.StartScreen;
import unit.BasicBlob;
import unit.Disciple;
import unit.FlyingUnit;
import unit.Salamander;
import unit.Settler;
import unit.Treekin;
import unit.Unit;

public class GameHandler {
	public static Map map;
	public static Faction f;
	public static ArrayList<Unit> moveAbleUnit;
	public static ArrayList<Faction> aiFactions;
	public static ArrayList<Research> research1;
	public static Stage home;
	static Blobular blobular;
	static Zenroyo zenroyo;
	static Floronis floronis;
	public static void initiate(Stage primaryStage) {
		home =primaryStage;
		research1 = new ArrayList<Research>();
		AdvancedFarming farming = new AdvancedFarming();
		research1.add(farming);
		research1.add(new Foresting());
		FactionScreen temp = new FactionScreen();
	    floronis = new Floronis();
		Reptar reptar = new Reptar();
		reptar.getAvailUnits().add(new Salamander());
		reptar.getCanBuild().add(new Granary());
		zenroyo = new Zenroyo();
		blobular = new Blobular();
		zenroyo.getAvailUnits().add(new Disciple());
		zenroyo.setNum(2);
		blobular.setNum(3);
		floronis.setNum(4);
		zenroyo.setColor(Color.HOTPINK);
		floronis.setColor(Color.SLATEBLUE);
		blobular.setColor(Color.MAROON);
		blobular.getAvailUnits().add(new BasicBlob());
		floronis.getAvailUnits().add(new Treekin());
		zenroyo.getCanBuild().add(new Granary());
		blobular.getCanBuild().add(new Granary());
		floronis.getCanBuild().add(new Granary());
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
		Settler test = new Settler();
		test.setX(40);
		test.setY(50);
		test.setOwner(f);
		UnitControl.spawnUnit(test, test.getX(), test.getY());
		Settler temp = new Settler();
		temp.setX(23);
		temp.setY(15);
		temp.setOwner(blobular);
		CityHandler.spawnCityAI(temp);
		temp.setX(33);
		temp.setY(33);
		temp.setOwner(floronis);
		CityHandler.spawnCityAI(temp);
		temp.setX(15);
		temp.setY(33);
		temp.setOwner(zenroyo);
		CityHandler.spawnCityAI(temp);
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
	public static boolean checkVictory() {
		if (aiFactions.isEmpty())
			return true;
		//Research Condition
		return false;
	}
	public static boolean isDead() {
		if (f.getCities().isEmpty() && f.getUnits().isEmpty())
			return true;
		return false;
	}
	public static void endTurn() {
		boolean ready = true;
		for (City c: f.getCities()) {
			if (c.getConstruction().peek() == null)
				ready = false;
		}
		if (f.getTechGoal() == null)
			ready = false;
		if (ready) {
			if(f.getCities().size()!=0) {
				for (City c: f.getCities()) {
					c.calcConstruct();
					c.calcFood();
				}
			}
		f.calcGold();
		f.progressResearch();
		map.top.update();
		map.rightBar.updateNotif();
		moveAbleUnit = new ArrayList<Unit>();
		moveAbleUnit.addAll(f.getUnits());
		for (int i = 0; i < moveAbleUnit.size(); i++) {
			moveAbleUnit.get(i).setMovesLeft(moveAbleUnit.get(i).getMovement());
		}
		for (Faction g: aiFactions) {
			AIControl.AIaction(g);
		}
		ArrayList<Faction> holder = new ArrayList<Faction>();
		for (int i = 0; i < aiFactions.size(); i++) {
			if(aiFactions.get(i).getCities().isEmpty() && aiFactions.get(i).getUnits().isEmpty()) {
			}
			else
				holder.add(aiFactions.get(i));
		}
		aiFactions = new ArrayList<Faction>(holder);
		
		if (isDead())
			showLose();
		else if (checkVictory()) {
			showVictory();
		}
		ProcessButtons.processMap(f, map.rightBar);
		}
	}
	public static void ending(Stage dialog) {
		dialog.close();
		ProcessButtons.processStartbuttons(StartScreen.Setup(home), home);
	}
	
	public static void showVictory() {
		Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(home);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("You Won!"));
        dialogVbox.setAlignment(Pos.CENTER);
        Button returnToMenu = new Button("Return to Main Menu");
        dialogVbox.setSpacing(20);
        returnToMenu.setOnAction(e ->ending(dialog));
        dialogVbox.getChildren().add(returnToMenu);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
	}
	public static void showLose() {
		Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(home);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("You Lost!"));
        dialogVbox.setAlignment(Pos.CENTER);
        Button returnToMenu = new Button("Return to Main Menu");
        dialogVbox.setSpacing(20);
        returnToMenu.setOnAction(e ->ending(dialog));
        dialogVbox.getChildren().add(returnToMenu);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
	}
}
