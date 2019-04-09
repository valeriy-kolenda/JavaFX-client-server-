package sample.controller;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import sample.server.User;
public class DeleteController extends AdministratorAccountController {
    public static int Id;
    public static User USER = new User();
    private ObservableList<User> usersData = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView backButton;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> surnameColumn;

    @FXML
    private TableColumn<User,Integer> scholarshipColumn;

    @FXML
    private TableView<User> Table;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<User,Integer> ageColumn;

    @FXML
    private TableColumn<User,Integer> courseColumn;

    @FXML
    private TableColumn<User, String> loginColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableColumn<User,Integer> paymentsColumn;

    @FXML
    private TableColumn<User,Integer> privilColumn;

    @FXML
    private TableColumn<User,Integer> dormitoryColumn;

    @FXML
    private TableColumn<User,Integer> markColumn;

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
    private Button printButton;

    @FXML
    void initialize() {

        Socket sock;
        InputStream inp;
        OutputStream out;
        try {
            sock = new Socket(InetAddress.getByName("localhost"), 1024);   //создаем сокет клиента и соединяемся с сервером который находится на порту 1024 на этом же компьютере
            int i = 6;
            inp = sock.getInputStream();   //получили входной поток для чтения данных
            out = sock.getOutputStream();  //получили выходной поток для записи данных
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            ObjectInputStream objInp = new ObjectInputStream(inp);
            objOut.writeObject(i);

            nameColumn.setCellValueFactory( new PropertyValueFactory<User,String>("name"));
            surnameColumn.setCellValueFactory( new PropertyValueFactory<User,String>("surname"));
            ageColumn.setCellValueFactory( new PropertyValueFactory<User,Integer>("age"));
            courseColumn.setCellValueFactory( new PropertyValueFactory<User,Integer>("course"));
            loginColumn.setCellValueFactory( new PropertyValueFactory<User,String>("login"));
            passwordColumn.setCellValueFactory( new PropertyValueFactory<User,String>("password"));
            paymentsColumn.setCellValueFactory( new PropertyValueFactory<User,Integer>("idpayments"));
            privilColumn.setCellValueFactory( new PropertyValueFactory<User,Integer>("idprivil"));
            dormitoryColumn.setCellValueFactory( new PropertyValueFactory<User,Integer>("iddormitory"));
            markColumn.setCellValueFactory( new PropertyValueFactory<User,Integer>("idmark"));
            scholarshipColumn.setCellValueFactory( new PropertyValueFactory<User,Integer>("scholarship"));

            ArrayList<User> users = ( ArrayList<User>)objInp.readObject();
            usersData.setAll(users);
            Table.setItems(usersData);


            deleteButton.setOnAction(event ->{
                int a = 7;
                try {
                    objOut.writeObject(a);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                User u = Table.getSelectionModel().getSelectedItem();
                try {
                    objOut.writeObject(u);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                deleteButton.getScene().getWindow().hide();
                openNewScene("/sample/view/delete.fxml");
            });

            Table.setOnMouseClicked( event -> {
                if( event.getClickCount() == 2 ) {
                    USER = Table.getSelectionModel().getSelectedItem();
                    deleteButton.getScene().getWindow().hide();
                    openNewScene("/sample/view/edit.fxml");
                    try {
                        inp.close(); //закрываем входной поток
                        objOut.close();
                        out.close(); //закрываем выходной поток
                        sock.close();   //закрываем сокет клиента
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            printButton.setOnMouseClicked(event -> {
                try
                {
                    File file = new File("scholarship.txt");
                    // Создание файла
                    file.createNewFile();
                    // Создание объекта FileWriter
                    FileWriter writer = new FileWriter(file);

                    // Запись содержимого в файл
                    writer.write(String.valueOf(usersData));
                    writer.flush();
                    writer.close();
                }
                catch(IOException ex){

                    System.out.println(ex.getMessage());
                }
            });

        } catch (Exception e) { // если возникла непредвиденная ошибка
            System.out.println("Error " + e.toString());
        }

        backButton.setOnMouseClicked(event -> {
            backButton.getScene().getWindow().hide();
            openNewScene("/sample/view/AdministratorAccount.fxml");

        });



    }
}
