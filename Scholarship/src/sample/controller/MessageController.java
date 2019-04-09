package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class MessageController extends mainAccountController {

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
        String message58 = "Здравствуйте, ваша стипендиальная оценка = " + mainAccountController.mark +
                        ".\nВыплата стипендий будет осуществляться 24 числа каждого месяца. \n" +
                        "Надеемся, в следующем семестре Вы повысите свой балл\n" +
                        "С наилучшими пожеланиями, Деканат. ";
        String message9 = "Здравствуйте, ваша стипендиальная оценка = " + mainAccountController.mark +
                ".\nВыплата стипендий будет осуществляться 24 числа каждого месяца. \n" +
                "Так держать!\n" +
                "С наилучшими пожеланиями, Деканат. ";

        backButton.setOnMouseClicked(event -> {
            backButton.getScene().getWindow().hide();
            openNewScene("/sample/view/mainaccount.fxml");
        });
        if(mainAccountController.mark>=5 && mainAccountController.mark<=8)
        messageArea.setText(message58);
        if(mainAccountController.mark>=9 && mainAccountController.mark<=10)
            messageArea.setText(message9);
    }
}