package screen;


import faction.Faction;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class topBar extends HBox{
	Text science = new Text();
	Text gold = new Text();
	Faction faction;
	Double width;
	public topBar(Faction f, double w) {
		width = w;
		GridPane pane = new GridPane();
		faction = f;
		updateScience();
		science.setStroke(Paint.valueOf("WHITE"));
		pane.add(science,0, 0);
		updateGold();
		gold.setStroke(Paint.valueOf("WHITE"));
		pane.add(gold, 1, 0);
		pane.setHgap(10);
		this.getChildren().add(pane);
		pane.setAlignment(Pos.CENTER_LEFT);
		Button close = new Button("X");
		//close.setOnAction(e->);
		close.setCancelButton(true);
		close.setPrefWidth(120);
		this.setSpacing(width-305);
		this.getChildren().add(close);
		this.setBackground(new Background(new BackgroundFill(Paint.valueOf("black"),CornerRadii.EMPTY,Insets.EMPTY)));
	}
	public void update() {
		updateScience();
		updateGold();
	}
	public void updateScience() {
		science.setText("Science: " + faction.getScience() + "(+"+faction.getScienceInc()+")");
	}
	public void updateGold() {
		faction.calcUpkeep();
		faction.checkIncome();
		gold.setText("Gold: " + faction.getGold() + "(+" +faction.getGoldInc()+")");
	}
}
