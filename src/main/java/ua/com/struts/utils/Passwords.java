package ua.com.struts.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Passwords {
    private static final Logger LOG = Logger.getLogger(Passwords.class);

    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        if (StringUtils.isEmpty(password)) {
            LOG.warn("encryptPassword: Password is null or empty");
            throw new IllegalArgumentException("Password is null or empty");
        }

        MessageDigest messageDigest = MessageDigest.getInstance(AuthenticationConstants.MD5);
        messageDigest.update(password.getBytes());
        byte[] digest = messageDigest.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
}
