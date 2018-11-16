package ua.com.struts.services;

public interface MailProvider {
    void sendAuthorizationMail(String username, String email);
}
