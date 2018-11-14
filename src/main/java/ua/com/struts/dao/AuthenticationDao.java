package ua.com.struts.dao;

public interface AuthenticationDao {
    boolean isUserExist(String username, String password);

    void saveUser(String username, String userPassword);

    boolean isUserExist(String username);
}
