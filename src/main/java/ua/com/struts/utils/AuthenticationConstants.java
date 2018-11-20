package ua.com.struts.utils;

public final class AuthenticationConstants {
    public static final String MD5 = "MD5";

    public static final String HOST = "HOST";
    public static final String PORT = "PORT";
    public static final String DB_PROP_FILENAME = "db.properties";
    public static final String SSL_MAIL_PROP_FILENAME = "sslMail.properties";
    public static final String MAIL_CREDENTIALS_FILENAME = "mailCredentials.properties";
    public static final String DRIVER_CLASSNAME = "net.sourceforge.jtds.jdbc.Driver";

    public static final String DATABASE_USERNAME = "sa";
    public static final String DATABASE_PASSWORD = "qazwsx";

    public static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_SECURITY_ERROR = "password.not.secure";
    public static final String CONNECTION_REFUSED = "connection.refused";
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";
    public static final String LOGIN = "login";
    public static final String LOGIN_VERIFICATION = "loginVerification";
    public static final String USER_EXISTS = "user.exists";
    public static final String USER_NOT_EXISTS = "user.not.exists";
    public static final String LOGIN_ERROR = "login.error";
    public static final String USERNAME_ERROR_KEY = "username.error";
    public static final String USERNAME_EMPTY_ERROR = "username.empty.error";
    public static final String PASSWORD_ERROR_KEY = "password.error";
    public static final String PASSWORD_EMPTY_ERROR = "password.empty.error";
    public static final String REGISTRATION_ERROR = "registration.error";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String EMAIL_ERROR_KEY = "email.error";
    public static final String EMAIL_NOT_CORRECT = "email.not.correct";
    public static final String EMAIL_EMPTY_ERROR = "email.empty.error";
    public static final String VERIFICATION_EMPTY_ERROR_KEY = "verification.empty.error";
    public static final String VERIFICATION_ERROR_KEY = "verification.error";
}
