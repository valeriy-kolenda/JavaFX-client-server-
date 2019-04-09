package sample.controller;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;

public class DiagrammaController extends mainAccountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView backButton;

    @FXML
    private PieChart PieChart;

    @FXML
    void initialize(){
        Socket sock;
        InputStream inp;
        OutputStream out;
        try {
            sock = new Socket(InetAddress.getByName("localhost"), 1024);   //создаем сокет клиента и соединяемся с сервером который находится на порту 1024 на этом же компьютере
            int i = 4;
            inp = sock.getInputStream();   //получили входной поток для чтения данных
            out = sock.getOutputStream();  //получили выходной поток для записи данных
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            ObjectInputStream objInp = new ObjectInputStream(inp);
            objOut.writeObject(i);

            ArrayList<Integer> marks = new ArrayList<Integer>();
            marks = (ArrayList<Integer>) objInp.readObject();
            ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
            list.add(new PieChart.Data("5 Стипендия", marks.get(0)));
            list.add(new PieChart.Data("6 Стипендия", marks.get(1)));
            list.add(new PieChart.Data("7 Стипендия", marks.get(2)));
            list.add(new PieChart.Data("8 Стипендия", marks.get(3)));
            list.add(new PieChart.Data("9 Стипендия", marks.get(4)));
            list.add(new PieChart.Data("10 Стипендия", marks.get(5)));
            PieChart.setData(list);

            inp.close(); //закрываем входной поток
            objOut.close();
            out.close(); //закрываем выходной поток
            sock.close();   //закрываем сокет клиента

        } catch (Exception e) { // если возникла непредвиденная ошибка
            System.out.println("Error " + e.toString());
        }


        backButton.setOnMouseClicked(event -> {
            backButton.getScene().getWindow().hide();
            openNewScene("/sample/view/mainaccount.fxml");
        });
    }
}