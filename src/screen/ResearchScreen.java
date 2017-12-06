package screen;

import java.util.ArrayList;

import faction.Faction;
import handler.GameHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import research.Research;

public class ResearchScreen {
	public static Text currRes =  new Text();
	public static Text desc = new Text("Research Description :                ");
	public static void displayResearch(Faction f, Stage primaryStage) {
		BorderPane pane = new BorderPane();
		Text title = new Text("Research");
		title.setFont(Font.font("Verdana",FontWeight.BOLD,70));
		desc.setWrappingWidth(120);
		title.setTextAlignment(TextAlignment.CENTER);
		pane.setTop(title);
		BorderPane.setAlignment(title, Pos.CENTER);
		HBox researches = new HBox();
		CircularPane tech = new CircularPane();
		ArrayList<Research> lvltech = new ArrayList<Research>(GameHandler.research1);
		if (!f.getResearched().isEmpty())
			lvltech.removeAll(f.getResearched());
		for (int g = 0; g < lvltech.size(); g++) {
			Button techno = new Button(lvltech.get(g).toString() + " \n Cost: " + lvltech.get(g).getScienceReq());
			Research r = lvltech.get(g);
			techno.setOnAction(e -> finish(f,r,primaryStage));
			techno.setOnMouseEntered(e -> setDesc(r));
			techno.setOnMouseExited(e -> reset());
			tech.getChildren().add(techno);
		}
		currRes.setText("Current Science: " + f.getScience() + "\n"+"Science per Turn: "+ f.getScienceInc());
		researches.getChildren().add(tech);
		researches.setAlignment(Pos.CENTER);
		pane.setLeft(currRes);
		pane.setRight(desc);
		pane.setCenter(researches);
		Button returnTomap = new Button("Return to Game");
		returnTomap.setOnAction(e->Map.back(primaryStage));
		returnTomap.setPrefHeight(120);
		returnTomap.setPrefWidth(300);
		pane.setBottom(returnTomap);
		primaryStage.getScene().setRoot(pane);
	}
	public static void reset() {
		desc.setText("Research Description :                ");
	}			
	public static void finish(Faction f,Research r, Stage primaryStage) {
		f.setTechGoal(r);
		Map.back(primaryStage);
	}
	public static void setDesc(Research r) {
		desc.setText(r.getDesc() + " ");
	}
}
