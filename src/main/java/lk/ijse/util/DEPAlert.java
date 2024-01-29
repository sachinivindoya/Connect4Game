package lk.ijse.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DEPAlert extends Alert {  //Alert SuperClass inherited to DEPAlert SubClass
    public DEPAlert( AlertType alertType, String title, String header, String message, ButtonType... buttonTypes) {  //DEPAlert constructor must public because if has not public keyword cannot be accessed into outside packages

        super(alertType, message, buttonTypes); //DEPAlert no attributes.they have inherited Alert super class's attributes and methiods therefore we need to make parent class constructor to default constructor .therefore we use super keyword.
                                                           //DEPAlert class inherits alert class so all the properties of Alert will be inherited to DEPAlert by default. To initialize all the property, we are using parent class constructor from child class. In such way, we are reusing the parent class constructor like super(alertType, message, buttonTypes);//reusing parent constructor
        this.setTitle(title);
        this.setHeaderText(header);
        String image = null;

        switch(alertType) {
            case ERROR:
                image = "/asset/error.png";  //image path
                break;
            case INFORMATION:
                image = "/asset/info.png";  //image path
                break;
            case WARNING:
                image = "/asset/warning.png"; //image path
        }

        if (image != null) {            //if image not equal null set image size and graphic
            ImageView imgView = new ImageView(new Image(image));
            imgView.setFitWidth(32.0D);
            imgView.setFitHeight(32.0D);
            this.setGraphic(imgView);
        }

    }
}
