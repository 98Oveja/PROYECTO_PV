
package models;


public class User {

    private String pass;

    private String email;

    private String lastname;

    private String firstname;
    public User(String pass,String email,String lastname,String firstname){
        this.pass = pass;
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public String getPass ()
    {
        return pass;
    }

    public void setPass (String pass)
    {
        this.pass = pass;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getLastname ()
    {
        return lastname;
    }

    public void setLastname (String lastname)
    {
        this.lastname = lastname;
    }

    public String getFirstname ()
    {
        return firstname;
    }

    public void setFirstname (String firstname)
    {
        this.firstname = firstname;
    }

    @Override
    public String toString()
    {
        return "Class [pass = "+pass+", email = "+email+", lastname = "+lastname+", firstname = "+firstname+"]";
    }
}