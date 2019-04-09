package sample.controller;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;

public class BargrammaController extends mainAccountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView backButton;

    @FXML
    private BarChart<?, ?> BarChart;

    @FXML
    void initialize() {
        Socket sock;
        InputStream inp;
        OutputStream out;
        try {
            sock = new Socket(InetAddress.getByName("localhost"), 1024);   //создаем сокет клиента и соединяемся с сервером который находится на порту 1024 на этом же компьютере
            int i = 5;
            inp = sock.getInputStream();   //получили входной поток для чтения данных
            out = sock.getOutputStream();  //получили выходной поток для записи данных
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            ObjectInputStream objInp = new ObjectInputStream(inp);

            objOut.writeObject(i);
            objOut.writeObject(LoginController.log);

            Double scholarship;
            scholarship = (Double) objInp.readObject();
            ArrayList<Double> scholarships = new ArrayList<Double>();
            scholarships = (ArrayList<Double>) objInp.readObject();
            XYChart.Series list = new XYChart.Series<>();
            list.getData().add(new XYChart.Data("Ваша",scholarship));
            list.getData().add(new XYChart.Data("Самая Большая",scholarships.get(0)));
            list.getData().add(new XYChart.Data("Самая Маленькая",scholarships.get(1)));
            list.getData().add(new XYChart.Data("Средняя",scholarships.get(2)));
            BarChart.getData().addAll(list);








            inp.close();
            objOut.close();
            out.close();
            sock.close();

        } catch (Exception e) { // если возникла непредвиденная ошибка
            System.out.println("Error " + e.toString());
        }

        backButton.setOnMouseClicked(event -> {
            backButton.getScene().getWindow().hide();
            openNewScene("/sample/view/mainaccount.fxml");
        });
    }
}