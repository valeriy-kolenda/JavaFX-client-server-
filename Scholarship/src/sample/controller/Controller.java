package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    void initialize() throws IOException {

        loginButton.setOnAction(event -> {
            loginButton.getScene().getWindow().hide();
            openNewScene("/sample/view/login.fxml");
        });
    }


    public void openNewScene(String Window){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Window));

        try {

            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

    }
}