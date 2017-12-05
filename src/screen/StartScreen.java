package screen;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
		Button load = new Button("Load Game");
		Button help = new Button("Tutorial");
		Button quit = new Button("Quit");
		menu.add(start, 0, 0);
		menu.add(load, 0, 1);
		menu.add(help, 0, 2);
		menu.add(quit, 0, 3);
		pane.setCenter(menu);
		menu.setAlignment(Pos.CENTER);
		menu.setVgap(10);
		primaryStage.getScene().setRoot(pane);;
		Button[] Startbuttons = new Button[] {start,load,help,quit};
		return Startbuttons;
	}
}
