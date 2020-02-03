
package Models;

public class User {
int    id_ususer;
String email;
String name;
String last_name;
String pass;
int    status;
String admin;
String urlPhoto;

    public User(String name, int status, String admin, String urlPhoto) {
        this.name = name;
        this.status = status;
        this.admin = admin;
        this.urlPhoto = urlPhoto;
    }

    public User(int id_ususer, String email, String name, String last_name, String pass, int status, String admin) {
        this.id_ususer = id_ususer;
        this.email = email;
        this.name = name;
        this.last_name = last_name;
        this.pass = pass;
        this.status = status;
        this.admin = admin;
    }

    public User(String name, int status, String admin, String url_photo, String apellidos_) {

        this.name = name;
        this.last_name = apellidos_;
        this.status = status;
        this.admin = admin;
        this.urlPhoto = url_photo;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
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
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public boolean isEmpty(){
        if(urlPhoto == null)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_ususer=" + id_ususer +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", pass='" + pass + '\'' +
                ", status=" + status +
                ", admin='" + admin + '\'' +
                ", urlPhoto='" + urlPhoto + '\'' +
                '}';
    }
}