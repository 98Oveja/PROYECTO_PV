package models;

public class UserController {
    UserView view;
    User model;
    public UserController(User model, UserView view){
        this.view = view;
        this.model = model;
    }

    public String getPass ()
    {
        return model.getPass();
    }

    public void setPass (String pass) { model.setPass(pass); }

    public String getEmail (){ return model.getEmail(); }

    public void setEmail (String email)
    {
        model.setEmail(email);
    }

//    public String getLastname ()
//    {
//        return model.getLastname();
//    }

//    public void setLastname (String lastname) { model.setLastname(lastname); }

//    public String getFirstname (){ return model.getFirstname(); }

//    public void setFirstname (String firstname)
//    {
//        model.setFirstname(firstname);
//    }

//    public void UpdateView() {
//        view.list(model.getPass(),model.getEmail(), model.getLastname(),model.getFirstname());
//    }
}
