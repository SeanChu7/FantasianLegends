package handler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import screen.StartScreen;

public class Launcher extends Application{
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setFullScreen(true);
		primaryStage.setScene(new Scene(new Pane()));
		primaryStage.setFullScreenExitHint("");
		primaryStage.show();
		Button[] Startbuttons = StartScreen.Setup(primaryStage);
		ProcessButtons.processStartbuttons(Startbuttons, primaryStage);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
