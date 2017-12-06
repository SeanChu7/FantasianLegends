package screen;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class StartScreen {
	public static Button[] Setup(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		Text title = new Text("Fantasian Legends");
		title.setFont(Font.font("Verdana",FontWeight.BOLD,70));
		title.setTextAlignment(TextAlignment.CENTER);
		pane.setTop(title);
		BorderPane.setAlignment(title, Pos.CENTER);
		GridPane menu = new GridPane();
		Button start = new Button("New Game");
		start.setPrefWidth(120);
		start.setPrefHeight(50);
		Button help = new Button("Tutorial");
		help.setPrefWidth(120);
		help.setPrefHeight(50);
		Button quit = new Button("Quit");
		quit.setPrefWidth(120);
		quit.setPrefHeight(50);
		menu.add(start, 0, 0);
		menu.add(help, 0, 2);
		menu.add(quit, 0, 3);
		pane.setCenter(menu);
		menu.setAlignment(Pos.CENTER);
		menu.setVgap(10);
		primaryStage.getScene().setRoot(pane);;
		Button[] Startbuttons = new Button[] {start,help,quit};
		return Startbuttons;
	}
}
