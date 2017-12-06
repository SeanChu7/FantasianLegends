package screen;

import java.util.List;

import city.Constructable;
import faction.Faction;
import handler.GameHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class FactionScreen {
	public ComboBox<Faction> factionChoice;
	public void selectFaction(List<Faction> factions, Stage primaryStage) {
		BorderPane pane = new BorderPane();
		Text title = new Text("Choose Your Faction");
		title.setFont(Font.font("Verdana",FontWeight.BOLD,70));
		title.setTextAlignment(TextAlignment.CENTER);
		pane.setTop(title);
		BorderPane.setAlignment(title, Pos.CENTER);
		VBox center =  new VBox();
		ObservableList<Faction> items = FXCollections.observableArrayList();
		factionChoice = new ComboBox<Faction>();
		for (Faction f: factions) {
			items.add(f);
		}
		factionChoice.setItems(items);
		Text factionselected = new Text("Faction chosen: ");
		Text factionDesc = new Text("Faction Attributes: ");
		factionChoice.valueProperty().addListener(e -> {
			factionselected.setText("Faction chosen: " + factionChoice.getValue().toString());
			factionDesc.setText("Faction Attributes: " + factionChoice.getValue().getDesc());
		});
		Button playGame = new Button("Play");
		center.setSpacing(10);
		center.setAlignment(Pos.CENTER);
		center.getChildren().add(factionselected);
		center.getChildren().add(factionDesc);
		center.getChildren().add(factionChoice);
		center.getChildren().add(playGame);
		pane.setCenter(center);
		primaryStage.getScene().setRoot(pane);
		playGame.setOnAction(e-> finish(primaryStage));
	}
	public void finish(Stage primaryStage) {
		GameHandler.f = factionChoice.getValue();
		GameHandler.play(primaryStage);
	}

}
