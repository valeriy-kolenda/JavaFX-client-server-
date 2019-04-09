package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class DecanatController extends mainAccountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView backButton;

    @FXML
    private TextArea messageArea;

    @FXML
    void initialize() {
        String message =
                "Инженерно-экономический факультет. Деканат\n" +
                "\n" +
                "Время работы деканата \n" +
                "\n" +
                "\n" +
                "8:30-13:00, 13:45-17:30 \n" +
                "понедельник-четверг \n" +
                "8:30-13:00, 13:30-16:00 \n" +
                "пятница \n" +
                "\n" +
                "\n" +
                "ДЕКАН ФАКУЛЬТЕТА\n" +
                "\n" +
                "Князева Людмила Павловна\n" +
                "персональная страница \n" +
                "кандидат физико-математических наук, доцент \n" +
                "Адрес деканата: г.Минск, ул. Платонова 39, аудитория 907, 5 корпус БГУИР (на карте) \n" +
                "Телефон: +375 17 293-22-88 \n" +
                "E-mail: knyazeva@bsuir.by";
        messageArea.setText(message);
        backButton.setOnMouseClicked(event -> {
            backButton.getScene().getWindow().hide();
            openNewScene("/sample/view/mainaccount.fxml");
        });

    }
}
