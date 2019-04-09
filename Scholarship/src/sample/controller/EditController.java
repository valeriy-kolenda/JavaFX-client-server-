package sample.controller;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.server.User;

public class EditController extends AdministratorAccountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button editButton;

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
    private Button cancelButton;
    @FXML
    private TextField idmarkField;

    @FXML
    void initialize(){
        editButton.setOnAction(event -> {
          /*  String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();
            Integer age = Integer.parseInt(ageField.getText().trim());
            Integer course = Integer.parseInt(courseField.getText().trim());
            String login = loginField.getText().trim();
            String password = passwordField.getText().trim();
            Integer idpayments = Integer.parseInt(idpaymentsField.getText().trim());
            Integer idprivil = Integer.parseInt(idprivilField.getText().trim());
            Integer iddormitory = Integer.parseInt(iddormitoryField.getText().trim());
            Integer idmark = Integer.parseInt(idmarkField.getText().trim());
            */
            Socket sock;
            InputStream inp;
            OutputStream out;
            try {
                sock = new Socket(InetAddress.getByName("localhost"), 1024);   //создаем сокет клиента и соединяемся с сервером который находится на порту 1024 на этом же компьютере
                int i = 8;
                inp = sock.getInputStream();   //получили входной поток для чтения данных
                out = sock.getOutputStream();  //получили выходной поток для записи данных
                ObjectOutputStream objOut = new ObjectOutputStream(out);
                ObjectInputStream objInp = new ObjectInputStream(inp);

                User u = new User();
                u = DeleteController.USER;
                if(!nameField.getText().trim().equals("")) {
                    String name = nameField.getText().trim();
                    u.setName(name);
                }
                if(!surnameField.getText().trim().equals("")) {
                    String surname = surnameField.getText().trim();
                    u.setSurname(surname);
                }
                if(!loginField.getText().trim().equals("")) {
                    String login = loginField.getText().trim();
                    u.setLogin(login);
                }
                if(!passwordField.getText().trim().equals("")) {
                    String password = passwordField.getText().trim();
                    u.setPassword(password);
                }
                if(!ageField.getText().trim().equals(""))
                    u.setAge(Integer.parseInt(ageField.getText().trim()));
                if(!courseField.getText().trim().equals(""))
                    u.setCourse(Integer.parseInt(courseField.getText().trim()));
                if(!idpaymentsField.getText().trim().equals(""))
                    u.setIdpayments(Integer.parseInt(idpaymentsField.getText().trim()));
                if(!idprivilField.getText().trim().equals(""))
                    u.setIdprivil(Integer.parseInt(idprivilField.getText().trim()));
                if(!iddormitoryField.getText().trim().equals(""))
                    u.setIddormitory(Integer.parseInt(iddormitoryField.getText().trim()));
                if(!idmarkField.getText().trim().equals(""))
                    u.setIdmark(Integer.parseInt(idmarkField.getText().trim()));

                objOut.writeObject(i);
                objOut.writeObject(u);
                System.out.println(u);
                inp.close(); //закрываем входной поток
                objOut.close();
                out.close(); //закрываем выходной поток
                sock.close();   //закрываем сокет клиента
                editButton.getScene().getWindow().hide();
                openNewScene("/sample/view/delete.fxml");
            } catch (Exception e) { // если возникла непредвиденная ошибка
                System.out.println("Error " + e.toString());
            }
        });
    }
}
