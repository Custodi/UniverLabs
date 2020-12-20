package MainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UI_1.fxml"));
        primaryStage.setTitle("Lists movement");
        primaryStage.setScene(new Scene(root, 750, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
