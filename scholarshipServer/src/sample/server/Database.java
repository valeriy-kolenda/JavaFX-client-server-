package sample.server;
/*import com.sun.jdi.ClassNotLoadedException;*/

import java.sql.*;
import java.util.ArrayList;

public class Database extends Connect {

    public static Database instance;
    public Connection dbConnection;

    private Database() {
        String connectionString = dbCon;
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }


    public void userRegistration(User user) {
        String insert = "INSERT INTO users("+ Direct.USER_NAME + "," + Direct.USER_SURNAME + "," + Direct.USER_AGE + "," + Direct.USER_COURSE+ "," +
                Direct.USER_LOGIN + "," + Direct.USER_PASSWORD+ "," + Direct.USER_IDpayments+ "," + Direct.USER_IDprivil + "," +
                Direct.USER_IDdormitory + "," + Direct.USER_IDmark + ") VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getInstance().dbConnection.prepareStatement(insert);
            prSt.setString(1, user.getName());
            prSt.setString(2, user.getSurname());
            prSt.setInt(3, user.getAge());
            prSt.setInt(4, user.getCourse());
            prSt.setString(5, user.getLogin());
            prSt.setString(6, user.getPassword());
            prSt.setInt(7, user.getIdpayments());
            prSt.setInt(8, user.getIdprivil());
            prSt.setInt(9, user.getIddormitory());
            prSt.setInt(10, user.getIdmark());
            prSt.executeUpdate();


            Double firstSumm = getInstance().getCoefficient(user);
            Double secondSumm = getInstance().getSumm(user);
            Double scholarship = firstSumm + secondSumm;

            String update = "UPDATE users SET scholarship " + "=?" + "WHERE login " + "=?";
            PreparedStatement prStUp = getInstance().dbConnection.prepareStatement(update);
            prStUp.setDouble(1, scholarship);
            prStUp.setString(2, user.getLogin());
            prStUp.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user) {
        ResultSet resSet = null;
        String select = "SELECT * FROM users WHERE " + Direct.USER_LOGIN + "=? AND " + Direct.USER_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getInstance().dbConnection.prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getAccountInfo(String accountLogin){
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Direct.USER_TABLE + " WHERE " +
                Direct.USER_LOGIN + "=?";

        try {
            PreparedStatement prSt = getInstance().dbConnection.prepareStatement(select);
            prSt.setString(1, accountLogin);


            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getAllAccountInfo() throws SQLException {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Direct.USER_TABLE + " WHERE " +
                Direct.USER_LOGIN + "<> ?";
        PreparedStatement prSt = getInstance().dbConnection.prepareStatement(select);
        prSt.setString(1, "admin");
        resSet = prSt.executeQuery();
        return resSet;
    }
    public Double getCoefficient(User user) throws SQLException {

        Double coeff = 0.0;
        String coefficient = "SELECT mark.coefficient FROM mark INNER JOIN " +
                "users ON mark.idmark"+ "=?"+
                " AND users.login"+"=?";
        PreparedStatement prSt = getInstance().dbConnection.prepareStatement(coefficient);
        prSt.setInt(1, user.getIdmark());
        prSt.setString(2, user.getLogin());
        ResultSet result =  prSt.executeQuery();
        while (result.next()){
            coeff = result.getDouble(1) * 66.0;
        }
        return coeff;
    }
    public Double getSumm(User user) throws SQLException {

        Double summ = 0.0;
        String paymentsSumm = "SELECT payments.summ FROM payments INNER JOIN " +
                "users ON payments.idpayments "+ "=?"+
                " AND users.login "+ "=?";
        String dormitorySumm = "SELECT dormitory.summ FROM dormitory INNER JOIN " +
                "users ON dormitory.iddormitory "+ "=?"+
                " AND users.login " + "=?";
        String privilSumm = "SELECT privil.summ FROM privil INNER JOIN " +
                "users ON privil.idprivil "+ "=?"+
                " AND users.login " + "=?";
        PreparedStatement prStpayments = getInstance().dbConnection.prepareStatement(paymentsSumm);
        prStpayments.setInt(1, user.getIdpayments());
        prStpayments.setString(2, user.getLogin());

        PreparedStatement prStdormitory = getInstance().dbConnection.prepareStatement(dormitorySumm);
        prStdormitory.setInt(1, user.getIddormitory());
        prStdormitory.setString(2, user.getLogin());

        PreparedStatement prStprivil = getInstance().dbConnection.prepareStatement(privilSumm);
        prStprivil.setInt(1, user.getIdprivil());
        prStprivil.setString(2, user.getLogin());
        ResultSet result1 =  prStpayments.executeQuery();
        ResultSet result2 =  prStdormitory.executeQuery();
        ResultSet result3 =  prStprivil.executeQuery();
        while (result3.next()){
            summ = result3.getDouble(1);
        }
        while (result2.next()){
            summ -= result2.getDouble(1);
        }
        while (result1.next()){
            summ -= result1.getDouble(1);
        }
        return summ;
    }

    public void deleteUser(User user) throws SQLException {
        String delete = "DELETE FROM " + Direct.USER_TABLE + " WHERE " +
                Direct.USER_LOGIN + "=?";
        PreparedStatement prSt = getInstance().dbConnection.prepareStatement(delete);
        prSt.setString(1, user.getLogin());
        prSt.executeUpdate();
    }

    public void editUser(User user) throws SQLException {
        String update = "UPDATE " + Direct.USER_TABLE + " SET name " + "=? " +", surname " + "=?" +
                ", age " + "=?" + ", course " + "=?" + ", login " + "=?" + ", password " + "=?" +
                ", idpayments " + "=?" + ", idprivil " + "=?" + ", iddormitory " + "=?" +
                ", idmark " + "=?" + " WHERE idusers " + "=?";
        System.out.println(user.getName());
        PreparedStatement prSt = getInstance().dbConnection.prepareStatement(update);
        prSt.setString(1, user.getName());
        prSt.setString(2, user.getSurname());
        prSt.setInt(3, user.getAge());
        prSt.setInt(4, user.getCourse());
        prSt.setString(5, user.getLogin());
        prSt.setString(6, user.getPassword());
        prSt.setInt(7, user.getIdpayments());
        prSt.setInt(8, user.getIdprivil());
        prSt.setInt(9, user.getIddormitory());
        prSt.setInt(10, user.getIdmark());
        prSt.setInt(11, user.getIdUser());
        prSt.executeUpdate();

        Double firstSumm = getInstance().getCoefficient(user);
        Double secondSumm = getInstance().getSumm(user);
        Double scholarship = firstSumm + secondSumm;

        String updateScholarship = "UPDATE users SET scholarship " + "=?" + "WHERE login " + "=?";
        PreparedStatement prStUp = getInstance().dbConnection.prepareStatement(updateScholarship);
        prStUp.setDouble(1, scholarship);
        prStUp.setString(2, user.getLogin());
        prStUp.executeUpdate();
    }

    public void dormitoryRegistration(Integer number, Double summ, String street, Integer house) {
        String insert = "INSERT INTO dormitory(iddormitory" + ", " + "summ" + ", " + "street " + "," + "house) VALUES (?,?,?,?)";
        try{
            PreparedStatement prSt = getInstance().dbConnection.prepareStatement(insert);
            prSt.setInt(1, number);
            prSt.setDouble(2, summ);
            prSt.setString(3, street);
            prSt.setInt(4, house);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void paymentsRegistration(Integer number, Double summ, String name) {
        String insert = "INSERT INTO payments(idpayments" + ", " + "name" + ", " + "summ " +") VALUES (?,?,?)";
        try{
            PreparedStatement prSt = getInstance().dbConnection.prepareStatement(insert);
            prSt.setInt(1, number);
            prSt.setString(2, name);
            prSt.setDouble(3, summ);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void privilRegistration(Integer number, Double summ, String name) {
        String insert = "INSERT INTO privil(idprivil" + ", " + "name" + ", " + "summ " +") VALUES (?,?,?)";
        try{
            PreparedStatement prState = getInstance().dbConnection.prepareStatement(insert);
            prState.setDouble(3, summ);
            prState.setInt(1, number);
            prState.setString(2, name);

            prState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    }
