package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField PassWordField;

    @FXML
    private TextField NameField;

    @FXML
    private Button SaveButton;

    @FXML
    private TextField SecondNameField;

    @FXML
    private TextField ThirdNameField;

    @FXML
    private Label welcomeText;

    @FXML
    private Button EnterButton;

    @FXML
    void initialize() {

        SaveButton.setOnAction(event -> {
            signUpNewUser();
        });


        EnterButton.setOnAction(event -> {
           EnterButton.getScene().getWindow().hide();

           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("app.fxml"));

           try{
               loader.load();
           } catch (IOException e) {
               e.printStackTrace();
           }

           Parent root = loader.getRoot();
           Stage stage = new Stage();
           stage.setScene(new Scene(root));
           stage.showAndWait();
        });




    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName=NameField.getText();
        String secondName=SecondNameField.getText();
        String thirdName=ThirdNameField.getText();
        String password=PassWordField.getText();

        User user = new User(firstName, secondName, thirdName, password);

        dbHandler.signUpUser(user);

    }

}
