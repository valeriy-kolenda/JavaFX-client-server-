package sample.server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FacadeExecuter {

    //метод для авторизации
    public int executeLoginUser(String login, String Password) throws SQLException {
        User user = new User();
        user.setLogin(login);
        user.setPassword(Password);
        ResultSet result = Database.getInstance().getUser(user);
        int counter = 0;
        while (result.next()) {
            counter++;
        }
        return counter;
    }

    //метод для регистрации
    public void executeNewUser (String name, String surname, Integer age, Integer course, String login, String password,
                                Integer idpayments, Integer idprivil, Integer iddormitory, Integer idmark){
        User user = new User(name, surname, age, course, login, password, idpayments, idprivil, iddormitory, idmark);
        Database.getInstance().userRegistration(user);
    }

    public ArrayList<Integer> getDiagramm() throws SQLException {
        int counter5 = 0;
        int counter6 = 0;
        int counter7 = 0;
        int counter8 = 0;
        int counter9 = 0;
        int counter10 = 0;
        ArrayList<Integer> marks = new ArrayList<Integer>();
        ResultSet result = Database.getInstance().getAllAccountInfo();
        while (result.next()){
            if(result.getInt(11) == 5)
                counter5++;
            else if(result.getInt(11) == 6)
                counter6++;
            else if(result.getInt(11) == 7)
                counter7++;
            else if(result.getInt(11) == 8)
                counter8++;
            else if(result.getInt(11) == 9)
                counter9++;
            else if(result.getInt(11) == 10)
                counter10++;
        }
        marks.add(counter5);
        marks.add(counter6);
        marks.add(counter7);
        marks.add(counter8);
        marks.add(counter9);
        marks.add(counter10);
        return marks;
    }

    public ArrayList<Double> getBar() throws SQLException {
        Double big = 0.0;
        Double small = 1000.0;
        Double avg = 0.0;
        Integer amount = 0;
        ArrayList<Double> scholarships = new ArrayList<Double>();
        ResultSet result = Database.getInstance().getAllAccountInfo();
        while (result.next()){
            if(result.getDouble(12) > big && result.getDouble(12) != 0)
                big = result.getDouble(12);

            if (result.getDouble(12) < small && result.getDouble(12) != 0)
                small = result.getDouble(12);

            if(result.getDouble(12) != 0 && result.getDouble(12) != 0) {
                avg += result.getDouble(12);
                amount++;
            }
        }
        avg = avg / amount;
        scholarships.add(big);
        scholarships.add(small);
        scholarships.add(avg);
        return scholarships;
    }

    public ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        ResultSet result = Database.getInstance().getAllAccountInfo();
        User user;
        while (result.next()){
            user = new User();
            user.setIdUser(result.getInt(1));
            user.setName(result.getString(2));
            user.setSurname(result.getString(3));
            user.setAge(result.getInt(4));
            user.setCourse(result.getInt(5));
            user.setLogin(result.getString(6));
            user.setPassword(result.getString(7));
            user.setIdpayments(result.getInt(8));
            user.setIdprivil(result.getInt(9));
            user.setIddormitory(result.getInt(10));
            user.setIdmark(result.getInt(11));
            user.setScholarship(result.getDouble(12));
            users.add(user);
        }
        return users;
    }

}
