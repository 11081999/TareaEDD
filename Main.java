import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.Serializable;

public class Main extends Application implements Serializable{
	private Scene loadScene = new LoadScene(this);
	private Stage primaryStage;

	public static void main(String[] args) {
    	Application.launch(args);
	}

	public void start(Stage stage){
		primaryStage = stage;
		primaryStage.setScene(loadScene);
		//640px X 480px
		primaryStage.setWidth(450);
		primaryStage.setHeight(450);
		primaryStage.show();
	}

}
