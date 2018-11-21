package ua.com.struts.dao;

import ua.com.struts.model.User;

public interface AuthenticationDao {

    User findUserByEmailAndPassword(String email, String password);

    User findUserById(long userId);

    boolean isUserExist(String username, String password);

    void saveUser(String username, String userPassword, String email, String countryCode, String phoneNumber, int authyId);

    boolean isUserExist(String username);
}
