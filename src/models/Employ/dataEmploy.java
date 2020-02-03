package models.Employ;

public class dataEmploy {
    public String name1, name2, lastname1, lastname2, dir, correo,img ="null",fechaInicio,cargo,tel;
    public int idper,idemp;
    public boolean estado;
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
