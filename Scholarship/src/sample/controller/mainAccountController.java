package sample.controller;
import javafx.scene.control.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class mainAccountController extends LoginController{
    public static Integer mark;
    public static Double scholarship;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField scholarshipField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField courseField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private ImageView outButton;

    @FXML
    private ImageView diagrammButton;

    @FXML
    private ImageView barButton;

    @FXML
    private Button messageButton;

    @FXML
    private Button decanatButton;

    @FXML
    private Button scholarshipButton;


    @FXML
    void initialize() throws IOException {

            Socket sock;
            InputStream inp;
            OutputStream out;

            try {
                sock = new Socket(InetAddress.getByName("localhost"), 1024);   //создаем сокет клиента и соединяемся с сервером который находится на порту 1024 на этом же компьютере

                inp = sock.getInputStream();   //получили входной поток для чтения данных
                out = sock.getOutputStream();  //получили выходной поток для записи данных

                ObjectOutputStream objOut = new ObjectOutputStream(out);
                ObjectInputStream objInp = new ObjectInputStream(inp);
                int i = 1;
                objOut.writeObject(i);
                objOut.writeObject(LoginController.log);
                String name = (String) objInp.readObject();
                String surname = (String) objInp.readObject();
                Integer  age = (Integer) objInp.readObject();
                Integer course = (Integer) objInp.readObject();
                String login = (String) objInp.readObject();
                String password = (String) objInp.readObject();
                mark = (Integer) objInp.readObject();
                scholarship = (Double) objInp.readObject();

                nameField.setText(name);
                surnameField.setText(surname);
                ageField.setText(age.toString());
                courseField.setText(course.toString());
                loginField.setText(login);
                passwordField.setText(password);
               // scholarshipField.setText(scholarship.toString());
                inp.close(); //закрываем входной поток
                objOut.close();
                out.close(); //закрываем выходной поток
                sock.close();   //закрываем сокет клиента
            } catch (Exception e) { // если возникла непредвиденная ошибка
                System.out.println("Error " + e.toString());
            }

        outButton.setOnMouseClicked(event -> {
            outButton.getScene().getWindow().hide();
            openNewScene("/sample/view/main.fxml");
        });

        diagrammButton.setOnMouseClicked(event -> {
            diagrammButton.getScene().getWindow().hide();
            openNewScene("/sample/view/diagramma.fxml");
        });

        barButton.setOnMouseClicked(event -> {
            barButton.getScene().getWindow().hide();
            openNewScene("/sample/view/bargramma.fxml");
        });

        messageButton.setOnMouseClicked(event -> {
            messageButton.getScene().getWindow().hide();
            openNewScene("/sample/view/message.fxml");
        });

        scholarshipButton.setOnMouseClicked(event -> {
            scholarshipButton.getScene().getWindow().hide();
            openNewScene("/sample/view/scholarship.fxml");
        });

        decanatButton.setOnMouseClicked(event -> {
            decanatButton.getScene().getWindow().hide();
            openNewScene("/sample/view/decanat.fxml");
        });
    }

}