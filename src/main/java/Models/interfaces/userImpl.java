package Models.interfaces;

import Models.User;

public interface userImpl {
    public void insert(User user);
    public void update(User user);
    public void delete(Integer id);
    public boolean existEmail(String email);
    public User read(String user, String pass);
}
