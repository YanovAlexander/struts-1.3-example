package ua.com.struts.dao;

public interface AuthenticationDao {
    boolean isUserExist(String username, String password);

    void saveUser(String username, String userPassword, String email, String countryCode, String phoneNumber, int authyId);

    boolean isUserExist(String username);
}
