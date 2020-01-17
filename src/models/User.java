
package models;


public class User {
int    id_ususer;
String email;
String name;
String last_name;
String pass;
int status;
String Admin;

    public User(String name, int status, String admin) {
        this.name = name;
        this.status = status;
        Admin = admin;
    }

    public User(int id_ususer, String email, String name, String last_name, String pass, int status, String admin) {
        this.id_ususer = id_ususer;
        this.email = email;
        this.name = name;
        this.last_name = last_name;
        this.pass = pass;
        this.status = status;
        Admin = admin;
    }

    public int getId_ususer() {
        return id_ususer;
    }

    public void setId_ususer(int id_ususer) {
        this.id_ususer = id_ususer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAdmin() {
        return Admin;
    }

    public void setAdmin(String admin) {
        Admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_ususer=" + id_ususer +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", pass='" + pass + '\'' +
                ", status='" + status + '\'' +
                ", Admin='" + Admin + '\'' +
                '}';
    }
}