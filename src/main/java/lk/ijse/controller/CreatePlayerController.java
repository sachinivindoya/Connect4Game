package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;
import lk.ijse.util.DEPAlert;


import java.io.IOException;
import java.util.Objects;

public class CreatePlayerController {
    public JFXTextField txtName;  //name textField
    public JFXButton btnPlay;  //play button
    public CubicCurve curve;   //mouse move curve animation on the first scene


    public void btnPlayOnAction(ActionEvent actionEvent) throws IOException {
        String name = txtName.getText(); //after click button get textField's text

        if (name.isBlank()) {  //if blank textField clicked show Alert
            (new DEPAlert( AlertType.ERROR, "Error", "Empty Name", "Name can't be empty", new ButtonType[0]) ).show();
            this.txtName.requestFocus();
            this.txtName.selectAll();
        } else if (!name.matches("[A-Za-z ]+")) { //if name haven't english words clicked show Alert
            (new DEPAlert( AlertType.WARNING, "Error", "Invalid Name", "Please enter a valid name", new ButtonType[0]) ).show();
            this.txtName.requestFocus();
            this.txtName.selectAll();
        } else {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/Board.fxml"));//set second scene of game using Board.fxml file
            stage.setScene(new Scene((Parent)fxmlLoader.load()));

            ((BoardController)fxmlLoader.getController()).initData(name);//(pass player name to  intitData() method) > non-static method initData(java.lang.String) cannot be referenced from a static context therefore JavaFX FXMLLoader getController() Returns the controller associated with the root object.

            stage.setResizable(false);
            stage.setTitle("Connect 4 Game - Player: " + name); //set title second scene
            stage.show();
            stage.centerOnScreen();

            this.btnPlay.getScene().getWindow().hide(); //hide this fiirst scene after click button

            Objects.requireNonNull(stage);
            Platform.runLater(stage::sizeToScene);
        }
    }

    public void rootOnMouseExited(MouseEvent mouseEvent) { //first scene's cubic curve animating while mouse moving
        this.curve.setControlX2(451.8468017578125D);
        this.curve.setControlY2(-36.0D);
    }

    public void rootOnMouseMove(MouseEvent mouseEvent) { //first scene's cubic curve animating while mouse moving
        this.curve.setControlX2(mouseEvent.getX());
    }
}
