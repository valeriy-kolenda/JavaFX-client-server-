package sample.controller;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PaymentsController extends AdministratorAccountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button addButton;

    @FXML
    private TextField numberField;

    @FXML
    private TextField sumField;

    @FXML
    private TextField nameField;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            Integer number = Integer.parseInt(numberField.getText().trim());
            Double summ = Double.parseDouble(sumField.getText().trim());
            String name = nameField.getText().trim();
            if (!number.equals("") && !summ.equals("") && !name.equals("") ) {
                Socket sock;
                InputStream inp;
                OutputStream out;
                try {
                    sock = new Socket(InetAddress.getByName("localhost"), 1024);   //создаем сокет клиента и соединяемся с сервером который находится на порту 1024 на этом же компьютере
                    inp = sock.getInputStream();   //получили входной поток для чтения данных
                    out = sock.getOutputStream();  //получили выходной поток для записи данных
                    int i = 10;

                    ObjectOutputStream objOut = new ObjectOutputStream(out);
                    ObjectInputStream objInp = new ObjectInputStream(inp);
                    objOut.writeObject(i);
                    objOut.writeObject(number);
                    objOut.writeObject(summ);
                    objOut.writeObject(name);
                    inp.close(); //закрываем входной поток
                    objOut.close();
                    out.close(); //закрываем выходной поток
                    sock.close();   //закрываем сокет клиента

                    addButton.getScene().getWindow().hide();
                    openNewScene("/sample/view/AdministratorAccount.fxml");
                } catch (Exception e) { // если возникла непредвиденная ошибка
                    System.out.println("Error " + e.toString());
                }
            }
            else {
            /*    Shake userNameAnim = new Shake(nameCheckin);
                Shake userLoginAnim = new Shake(loginCheckin);
                Shake userPassAnim = new Shake(passwordCheckin);
                userNameAnim.playAnim();
                userLoginAnim.playAnim();
                userPassAnim.playAnim();
                */
            }

        });

        backButton.setOnMouseClicked(event -> {
            backButton.getScene().getWindow().hide();
            openNewScene("/sample/view/AdministratorAccount.fxml");
        });
    }
}
