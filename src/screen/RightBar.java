package screen;
import java.util.ArrayList;
import java.util.List;

import city.Building;
import city.City;
import city.Constructable;
import faction.Faction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import unit.RangeUnit;
import unit.Settler;
import unit.Unit;

public class RightBar extends VBox{
	Faction faction;
	public Button research;
	public Button unitDie;
	public Button endTurn;
	public Button settle;
	private Text notif;
	private int turnNum;
	public VBox unitInf;
	public VBox cityInf;
	public ListView<Constructable> canBuild = new ListView<Constructable>();
	public ListView<Constructable> currBuild = new ListView<Constructable>();
	Text queue = new Text();
	VBox bottom;
	Double height;
	public RightBar(Faction f, Double h) {
		height = h;
		turnNum = 1;
		bottom = new VBox();
		GridPane pane = new GridPane();
		research = new Button("Research");
		pane.add(research, 0, 0);
		notif = new Text("Turn Number: " + turnNum);
		notif.setStroke(Color.WHITE);
		endTurn = new Button("End Turn");
		endTurn.setPrefWidth(120);
		research.setPrefWidth(120);
		pane.add(notif, 0, 1);
		pane.add(endTurn, 0, 2);
		bottom.getChildren().add(pane);
		bottom.setSpacing(10);
		bottom.setAlignment(Pos.BOTTOM_LEFT);
		this.getChildren().add(bottom);
		this.setAlignment(Pos.BOTTOM_LEFT);
		this.setBackground(new Background(new BackgroundFill(Paint.valueOf("black"),CornerRadii.EMPTY,Insets.EMPTY)));
	}
	public void displaySelectedCity(City c, Faction f) {
		this.getChildren().remove(bottom);
		Text population = new Text("Population: " + c.getPopulation());
		population.setStroke(Color.WHITE);
		Text foodToNext =new Text("Food Req: " + c.getFoodToNext());
		foodToNext.setStroke(Color.WHITE);
		GridPane yields = cityYields(c);
		Text current = new Text("Buildings");
		current.setStroke(Color.WHITE);
		Text buildable = new Text("Production");
		buildable.setStroke(Color.WHITE);
		queue.setStroke(Color.WHITE);
		updateInfo(c,f);
		cityInf = new VBox();
		cityInf.getChildren().add(population);
		cityInf.getChildren().add(foodToNext);
		cityInf.getChildren().add(yields);
		cityInf.getChildren().add(current);
		cityInf.getChildren().add(currBuild);
		cityInf.getChildren().add(buildable);
		cityInf.getChildren().add(canBuild);
		cityInf.getChildren().add(queue);
		cityInf.setSpacing(10);
		cityInf.setAlignment(Pos.CENTER);
		this.getChildren().add(cityInf);
		this.setAlignment(Pos.TOP_CENTER);
	}
	public void updateInfo(City c, Faction f) {
		ObservableList<Constructable> items = FXCollections.observableArrayList();
		for(Building b: c.getBuildings()) {
			items.add(b.clone());
		}
		currBuild.setPrefHeight(100);
		currBuild.setItems(items);
		List<Constructable> buildings = new ArrayList<Constructable>();
		buildings.addAll(f.getCanBuild());
		for (Building b : c.getBuildings()) {
			for (int i  = 0; i < buildings.size(); i++) {	
				if (b.equals(buildings.get(i)))
					buildings.remove(buildings.get(i));
			}
		}
		for (Building b : c.getBeingBuilt()) {
			for (int i  = 0; i < buildings.size(); i++) {	
				if (b.equals(buildings.get(i)))
					buildings.remove(buildings.get(i));
			}
		}
		buildings.addAll(f.getAvailUnits());
		items = FXCollections.observableArrayList();
		for(Constructable b: buildings) {
			items.add(b.clone());
		}
		canBuild.setPrefHeight(100);
		canBuild.setItems(items);
		String s;
		if (c.getConstruction().peek() == null)
			s = " ";
		else
			s = c.getConstruction().peek().toString();
		queue.setText("Construction:" + "\n" + s);
	}
	private GridPane cityYields(City c) {
		GridPane yields = new GridPane();
		ImageView food = new ImageView(new Image("Apple.png"));
		food.setFitHeight(20);
		food.setFitWidth(20);
		ImageView construct = new ImageView(new Image("Gear.png"));
		construct.setFitHeight(20);
		construct.setFitWidth(20);
		ImageView science = new ImageView(new Image("Beaker.png"));
		science.setFitHeight(20);
		science.setFitWidth(20);
		ImageView gold = new ImageView(new Image("Gold.png"));
		gold.setFitHeight(20);
		gold.setFitWidth(20);
		yields.add(food, 0, 0);
		yields.add(construct, 0, 1);
		yields.add(science, 0, 2);
		yields.add(gold, 0, 3);
		Text foodInc = new Text(""+c.getFoodInc());
		foodInc.setStroke(Color.WHITE);
		Text constructInc = new Text(""+c.getConstructInc());
		constructInc.setStroke(Color.WHITE);
		Text scienceInc = new Text(""+c.getSciInc());
		scienceInc.setStroke(Color.WHITE);
		Text goldInc = new Text(""+c.getGoldInc());
		goldInc.setStroke(Color.WHITE);
		yields.setHgap(10);
		yields.setVgap(10);
		yields.add(foodInc, 1, 0);
		yields.add(constructInc, 1, 1);
		yields.add(scienceInc, 1, 2);
		yields.add(goldInc, 1, 3);
		yields.setAlignment(Pos.CENTER);
		return yields;
	}
	public void displaySelectedunit(Unit u) {
		unitInf = u.displayInfo();
		unitInf.setAlignment(Pos.TOP_CENTER);
		unitDie = new Button("Delete");
		unitInf.getChildren().add(unitDie);
		settle = new Button("Settle");
		unitInf.getChildren().add(settle);
		settle.setVisible(false);
		if (u instanceof Settler) {
			settle.setVisible(true);
		}
		unitInf.setSpacing(10);
		this.getChildren().add(0,unitInf);	
		this.setSpacing(height-(300));
		if (u instanceof RangeUnit)
			this.setSpacing(height - 350);
	}
	public void removeDisplay(VBox v) {
		this.getChildren().remove(v);
		if (!this.getChildren().contains(bottom))
			this.getChildren().add(bottom);
		this.setAlignment(Pos.BOTTOM_LEFT);
	}
	public void updateNotif() {
		turnNum++;
		notif.setText("Turn Number: " + turnNum);
	}
	public void clearDisplay() {
		removeDisplay(unitInf);
		removeDisplay(cityInf);
	}
}
