package sample.server;
import java.net.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class ServerTCP {
    private static class ClientHandler implements Runnable {
        private int clientid = 0;
        private InputStream inp;
        private OutputStream out;
        private ObjectInputStream objInp;
        private ObjectOutputStream objOut;
        private Socket client;
        private FacadeExecuter executer = new FacadeExecuter();
        int k;

        public ClientHandler(int id, Socket s) {
            clientid = id;
            client = s;
        }

        public void run() {
            try {

                out = client.getOutputStream();    //получили выходной поток для записи данных

                inp = client.getInputStream(); //получили входной поток для чтения данных

                objInp = new ObjectInputStream(inp);

                objOut = new ObjectOutputStream(out);

                boolean flag = true;
                while (flag == true) {

                    try {
                        k = (int) objInp.readObject();
                        switch (k) {
                            case 1: {
                                System.out.println("Выполняется загрузка информации аккаунта");
                                String accountLogin = (String) objInp.readObject() ;
                                ResultSet result = Database.getInstance().getAccountInfo(accountLogin);
                                String name = "";
                                String surname = "";
                                Integer age = 0;
                                Integer course = 0;
                                String login = "";
                                String password = "";
                                Double scholarship = 0.0;
                                Integer idmark = 0;
                                while (result.next()) {
                                    name = result.getString(2);
                                    surname = result.getString(3);
                                    age = result.getInt(4);
                                    course = result.getInt(5);
                                    login = result.getString(6);
                                    password = result.getString(7);
                                    idmark = result.getInt(11);
                                    scholarship = result.getDouble(12);
                                }
                                objOut.writeObject(name);
                                objOut.writeObject(surname);
                                objOut.writeObject(age);
                                objOut.writeObject(course);
                                objOut.writeObject(login);
                                objOut.writeObject(password);
                                objOut.writeObject(idmark);
                                objOut.writeObject(scholarship);
                                break;
                            }
                            case 2:{
                                System.out.println("Выполняется авторизация");
                                String login = (String) objInp.readObject() ;
                                String Password = (String) objInp.readObject();
                                int test = executer.executeLoginUser(login, Password);
                                objOut.writeObject(test);
                                break;
                            }
                            case 3:{
                                System.out.println("Выполняется регистрация");
                                String name = (String) objInp.readObject();
                                String surname = (String) objInp.readObject();
                                Integer age = (Integer) objInp.readObject();
                                Integer course = (Integer) objInp.readObject();
                                String login = (String) objInp.readObject();
                                String password = (String) objInp.readObject();
                                Integer idpayments = (Integer) objInp.readObject();
                                Integer idprivil = (Integer) objInp.readObject();
                                Integer iddormitory = (Integer) objInp.readObject();
                                Integer idmark = (Integer) objInp.readObject();

                                executer.executeNewUser(name, surname, age, course, login, password, idpayments,
                                        idprivil,iddormitory,idmark);
                                break;
                            }
                            case 4:{
                                System.out.println("Выполняется показ ди-ммы");
                                ArrayList<Integer> marks = executer.getDiagramm();
                                objOut.writeObject(marks);//отправляем список с кол-вом оценок
                                break;
                            }
                            case 5:{
                                System.out.println("Выполняется показ графика");
                                String accountLogin = (String) objInp.readObject();
                                ResultSet result = Database.getInstance().getAccountInfo(accountLogin);
                                Double scholarship = 0.0;
                                while (result.next()){
                                    scholarship = result.getDouble(12);
                                }
                                objOut.writeObject(scholarship);

                                ArrayList<Double> scholarships = executer.getBar();
                                objOut.writeObject(scholarships);//отправляем стипендии
                                break;
                            }
                            case 6:{
                                System.out.println("Выполняется загрузка всех пользователей");
                                ArrayList<User> users;
                                users = executer.getUsers();
                                objOut.writeObject(users);
                                break;
                            }
                            case 7:{
                                System.out.println("Выполняется удаление");
                                User user = (User) objInp.readObject();
                                Database.getInstance().deleteUser(user);
                                break;
                            }
                            case 8:{
                                System.out.println("Выполняется изменение");
                                User user = (User) objInp.readObject();
                                Database.getInstance().editUser(user);
                                break;
                            }
                            case 9:{
                                System.out.println("Выполняется добавление общаги");
                                Integer number = (Integer) objInp.readObject();
                                Double summ = (Double) objInp.readObject();
                                String street = (String) objInp.readObject();
                                Integer house = (Integer) objInp.readObject();
                                Database.getInstance().dormitoryRegistration(number,summ,street,house);
                                break;
                            }
                            case 10:{
                                System.out.println("Выполняется добавление выплат");
                                Integer number = (Integer) objInp.readObject();
                                Double summ = (Double) objInp.readObject();
                                String name = (String) objInp.readObject();
                                Database.getInstance().paymentsRegistration(number,summ,name);
                                break;
                            }
                            case 11:{
                                System.out.println("Выполняется добавление льгот");
                                Integer number = (Integer) objInp.readObject();
                                Double summ = (Double) objInp.readObject();
                                String name = (String) objInp.readObject();
                                Database.getInstance().privilRegistration(number,summ,name);
                                break;
                            }
                        }
                    } catch (Exception e) {     //если клиент просто остановил свою работу не отправив disconnect
                        break;  //завершение цикла обработки данного клиента
                    }
                }
                inp.close();//закрытие входного потока
                out.close();//закрытие выходного потока
                objInp.close();
                objOut.close();
                client.close();//закрытие сокета, выделенного для работы с подключившимся клиентом
            } catch (Exception e) {
                e.printStackTrace();
            }
            // System.out.println("Client " + clientid + " disconnected");
        }
    }

    static int countclients = 0;//счетчик подключившихся клиентов
    public static void main(String args[]) {
        ServerSocket sock;

        try {
            sock = new ServerSocket(1024);//создаем серверный сокет работающий локально по порту 1024

            while (true) {//бесконечный цикл для возможности подключения последовательно нескольних клиентов
                Socket client = sock.accept();//сработает, когда клиент подключится, для него выделится отдельный сокет client
                countclients++;//количество подключившихся клиентов увеличивается на 1
                new Thread(new ClientHandler(countclients, client)).start();// стартуем новый поток обработки сообщений нового клиента

                // сама обработка находится в методе run класса ClientHandler
            }
        } catch (Exception e) {// в случае возникновения других не предвиденных ошибок
            System.out.println("Error " + e.toString());
        }

    }


}
