package vuongluis.veryeasy.bean;

import java.io.Serializable;

/**
 * Created by vuongluis on 5/14/2016.
 */
@SuppressWarnings("all")
public class User implements Serializable{
    private int id_user;
    private String username;
    private String password;
    private String fullname;
    private String birthday;
    private String address;

    public User() {
    }

    public User(String username, String password, String fullname,String birthday,String address) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.birthday = birthday;
        this.address = address;

    }

    public User(int id_user, String username, String password, String fullname,String birthday,String address) {
        this.id_user = id_user;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.birthday = birthday;
        this.address = address;
    }

    public int getId_user() {
        return id_user;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
