package sample.controller;
import java.io.Serializable;

public  class  User implements Serializable {
    private Integer idUser;
    private String name;
    private String surname;
    private Integer age;
    private Integer course;
    private String login;
    private String password;
    private Integer idpayments;
    private Integer idprivil;
    private Integer iddormitory;
    private Integer idmark;
    private Double Scholarship;

    public User() {}

    public User(Integer idUser, String name, String surname, Integer age, Integer course, String login, String password, Integer idpayments, Integer idprivil, Integer iddormitory, Integer idmark, Double scholarship) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.course = course;
        this.login = login;
        this.password = password;
        this.idpayments = idpayments;
        this.idprivil = idprivil;
        this.iddormitory = iddormitory;
        this.idmark = idmark;
        this.Scholarship = scholarship;
    }

    public User(Integer idUser, String name, String surname, Integer age, Integer course, String login, String password, Integer idpayments, Integer idprivil, Integer iddormitory, Integer idmark) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.course = course;
        this.login = login;
        this.password = password;
        this.idpayments = idpayments;
        this.idprivil = idprivil;
        this.iddormitory = iddormitory;
        this.idmark = idmark;
    }

    public User(String name, String surname, Integer age, Integer course, String login, String password, Integer idpayments, Integer idprivil, Integer iddormitory, Integer idmark) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.course = course;
        this.login = login;
        this.password = password;
        this.idpayments = idpayments;
        this.idprivil = idprivil;
        this.iddormitory = iddormitory;
        this.idmark = idmark;
    }

    public User(String name, String surname, Integer age, Integer course, String login, String password, Integer idpayments, Integer idprivil, Integer iddormitory, Integer idmark, Double scholarship) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.course = course;
        this.login = login;
        this.password = password;
        this.idpayments = idpayments;
        this.idprivil = idprivil;
        this.iddormitory = iddormitory;
        this.idmark = idmark;
        Scholarship = scholarship;
    }




    public Integer getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getCourse() {
        return course;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Integer getIdpayments() {
        return idpayments;
    }

    public Integer getIdprivil() {
        return idprivil;
    }

    public Integer getIddormitory() {
        return iddormitory;
    }

    public Integer getIdmark() {
        return idmark;
    }

    public Double getScholarship() {
        return Scholarship;
    }

    public void setIdUser(Integer idUser) {

        this.idUser = idUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdpayments(Integer idpayments) {
        this.idpayments = idpayments;
    }

    public void setIdprivil(Integer idprivil) {
        this.idprivil = idprivil;
    }

    public void setIddormitory(Integer iddormitory) {
        this.iddormitory = iddormitory;
    }

    public void setIdmark(Integer idmark) {
        this.idmark = idmark;
    }

    public void setScholarship(Double scholarship) {
        Scholarship = scholarship;
    }
}


