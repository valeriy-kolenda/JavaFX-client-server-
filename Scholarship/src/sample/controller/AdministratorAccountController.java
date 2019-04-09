package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class AdministratorAccountController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView outButton;

    @FXML
    private Button registrationButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button dormitoryButton;

    @FXML
    private Button privilButton;

    @FXML
    private Button paymentsButton;
    @FXML
    void initialize() {

        registrationButton.setOnAction(event -> {
            registrationButton.getScene().getWindow().hide();
            openNewScene("/sample/view/registration.fxml");
        });

        outButton.setOnMouseClicked(event -> {
            outButton.getScene().getWindow().hide();
            openNewScene("/sample/view/main.fxml");
        });

        deleteButton.setOnMouseClicked(event -> {
            deleteButton.getScene().getWindow().hide();
            openNewScene("/sample/view/delete.fxml");
        });

        dormitoryButton.setOnMouseClicked(event -> {
            dormitoryButton.getScene().getWindow().hide();
            openNewScene("/sample/view/dormitory.fxml");
        });

        paymentsButton.setOnMouseClicked(event -> {
            paymentsButton.getScene().getWindow().hide();
            openNewScene("/sample/view/payments.fxml");
        });

        privilButton.setOnMouseClicked(event -> {
            privilButton.getScene().getWindow().hide();
            openNewScene("/sample/view/privil.fxml");
        });
    }
}