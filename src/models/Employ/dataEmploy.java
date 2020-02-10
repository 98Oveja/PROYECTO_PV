package models.Employ;

public class dataEmploy {
    public String name1;
    public String name2;
    public String lastname1;
    public String lastname2;
    public String dir;
    public String correo;
    public String img ="null";
    public String fechaInicio;
    public String cargo;
    public String tel;
    public int idper,idemp;
    public boolean estado;
    public int id;

    public int getId() {
        return idemp;
    }
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

   public void setId(int id) {
        this.idemp = id;
    }



}
