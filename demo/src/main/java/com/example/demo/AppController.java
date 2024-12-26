package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField PassWordField;

    @FXML
    private Button SaveButton;

    @FXML
    private TextField SecondNameField;

    @FXML
    private Label welcomeText;

    @FXML
    void initialize() {
        SaveButton.setOnAction(event->{
            String loginText=SecondNameField.getText().trim();
            String loginPassword=PassWordField.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals(""))
                loginUser(loginText,loginPassword);
            else
                System.out.println("Заполните все поля!");
        });

    }


    private void loginUser(String loginText, String loginPassword){
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setSecondname(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        try {
            while (result.next()) {
                counter++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        if(counter >= 1){
            SaveButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("goodbye.fxml"));

            try{
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }
        else{
            Shake userLoginAnim = new Shake(SecondNameField);
            Shake userPassAnim = new Shake(PassWordField);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }


    }

}
