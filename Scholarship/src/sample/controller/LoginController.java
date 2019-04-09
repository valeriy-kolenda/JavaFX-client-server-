package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
//import sample.animations.Shake;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Controller{
    public static String log;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView backButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    void initialize() throws IOException {
        loginButton.setOnAction(event -> {
        Socket sock;
        InputStream inp;
        OutputStream out;

            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();

        if (!loginText.equals("") && !loginPassword.equals("")) {
            try {
                sock = new Socket(InetAddress.getByName("localhost"), 1024);   //создаем сокет клиента и соединяемся с сервером который находится на порту 1024 на этом же компьютере

                inp = sock.getInputStream();   //получили входной поток для чтения данных
                out = sock.getOutputStream();  //получили выходной поток для записи данных

                ObjectOutputStream objOut = new ObjectOutputStream(out);
                int i = 2;
                int c;

                ObjectInputStream objInp = new ObjectInputStream(inp);
                objOut.writeObject(i);
                objOut.writeObject(loginText);
                objOut.writeObject(loginPassword);
                c = (int) objInp.readObject();

                if(c >= 1){
                    log = loginText;
                    if(loginText.equals("admin")){
                        loginButton.getScene().getWindow().hide();
                        openNewScene("/sample/view/AdministratorAccount.fxml");
                    }
                    else {
                        loginButton.getScene().getWindow().hide();
                        openNewScene("/sample/view/mainaccount.fxml");
                    }
                }
                else{
                    //   Shake userLoginAnim = new Shake (loginField);
                    //   Shake userPassAnim = new Shake (passwordField);
                    //   userLoginAnim.playAnim();
                    //   userPassAnim.playAnim();
                }
                inp.close(); //закрываем входной поток
                objOut.close();
                out.close(); //закрываем выходной поток
                sock.close();   //закрываем сокет клиента

            } catch (Exception e) { // если возникла непредвиденная ошибка
                System.out.println("Error " + e.toString());
            }
        } else {
            System.out.println("Login and password inp empty");
            //   Shake userLoginAnim = new Shake(loginField);
            //   Shake userPassAnim = new Shake(passwordField);
            //   userLoginAnim.playAnim();
            //   userPassAnim.playAnim();
        }
        });

        backButton.setOnMouseClicked(event -> {
            backButton.getScene().getWindow().hide();
            openNewScene("/sample/view/main.fxml");
        });
    }


    }

