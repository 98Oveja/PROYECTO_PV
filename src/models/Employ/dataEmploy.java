package models.Employ;

public class dataEmploy {
    public String name1, name2, lastname1, lastname2, dir, correo,img,fechaInicio,cargo;
    public int idper,idemp, tel;
    public boolean estado;
    public String getName1() {
        return name1;
    }
    public int getId() {
        return idemp;
    }
    public String getCorreo() {
        return correo;
    }

    public String getDir() {
        return dir;
    }
    public String getLastname1() {
        return lastname1;
    }
    public String getLastname2() {
        return lastname2;
    }
    public String getName2() {
        return name2;
    }
    public int getTel() {
        return tel;
    }
    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setLastname1(String lastname1) {
        this.lastname1 = lastname1;
    }

    public void setLastname2(String lastname2) {
        this.lastname2 = lastname2;
    }

    public void setId(int id) {
        this.idemp = id;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
}
