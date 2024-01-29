package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {
    //AppInitializer extended from Application class(inherited).The entry point for JavaFX applications is the Application class.Super class is AppInitializer and SubClass is Application class

    public static void main(String[] args) {
        launch(args);   //Launch a standalone application.
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CreatePlayer.fxml"))));  //set first scene of game using CreatePlayer.fxml file
        primaryStage.setResizable(false);
        primaryStage.setTitle("Connect 4 Game - Create Player"); //title of the first scene
        primaryStage.show();  //show the first scene
        primaryStage.centerOnScreen();
    }
}
