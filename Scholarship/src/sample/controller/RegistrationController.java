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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class RegistrationController extends Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button registrationButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField courseField;

    @FXML
    private TextField idpaymentsField;

    @FXML
    private TextField idprivilField;

    @FXML
    private TextField iddormitoryField;

    @FXML
    private TextField idmarkField;


    @FXML
    private ImageView backButton;

    @FXML
    void initialize() {
        registrationButton.setOnAction(event -> {
            String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();
            Integer age = Integer.parseInt(ageField.getText().trim());
            Integer course = Integer.parseInt(courseField.getText().trim());
            String login = loginField.getText().trim();
            String password = passwordField.getText().trim();
            Integer idpayments = Integer.parseInt(idpaymentsField.getText().trim());
            Integer idprivil = Integer.parseInt(idprivilField.getText().trim());
            Integer iddormitory = Integer.parseInt(iddormitoryField.getText().trim());
            Integer idmark = Integer.parseInt(idmarkField.getText().trim());
            if (!name.equals("") && !login.equals("") && !password.equals("")) {
                Socket sock;
                InputStream inp;
                OutputStream out;
                try {
                    sock = new Socket(InetAddress.getByName("localhost"), 1024);   //создаем сокет клиента и соединяемся с сервером который находится на порту 1024 на этом же компьютере
                    inp = sock.getInputStream();   //получили входной поток для чтения данных
                    out = sock.getOutputStream();  //получили выходной поток для записи данных
                    int i = 3;

                    ObjectOutputStream objOut = new ObjectOutputStream(out);
                    ObjectInputStream objInp = new ObjectInputStream(inp);
                    objOut.writeObject(i);
                    objOut.writeObject(name);
                    objOut.writeObject(surname);
                    objOut.writeObject(age);
                    objOut.writeObject(course);
                    objOut.writeObject(login);
                    objOut.writeObject(password);
                    objOut.writeObject(idpayments);
                    objOut.writeObject(idprivil);
                    objOut.writeObject(iddormitory);
                    objOut.writeObject(idmark);
                    inp.close(); //закрываем входной поток
                    objOut.close();
                    out.close(); //закрываем выходной поток
                    sock.close();   //закрываем сокет клиента
                    System.out.println("Регистрация прошла успешно");
                    registrationButton.getScene().getWindow().hide();
                    openNewScene("/sample/view/main.fxml");
                } catch (Exception e) { // если возникла непредвиденная ошибка
                    System.out.println("Error " + e.toString());
                }
            }
            else {
                System.out.println("Login and password inp empty");
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
