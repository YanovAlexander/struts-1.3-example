package ua.com.struts.services.impl;

import org.apache.log4j.Logger;
import ua.com.struts.services.MailProvider;
import ua.com.struts.utils.AuthenticationConstants;
import ua.com.struts.utils.PropertiesUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailProviderImpl implements MailProvider {
    private static final Logger LOG = Logger.getLogger(MailProviderImpl.class);

    @Override
    public void sendAuthorizationMail(String username, String email) {
        Properties sslProperties = PropertiesUtils.loadProperties(AuthenticationConstants.SSL_MAIL_PROP_FILENAME);
        final Properties mailCredentials = PropertiesUtils.loadProperties(AuthenticationConstants.MAIL_CREDENTIALS_FILENAME);

        Session session = Session.getInstance(sslProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailCredentials.getProperty(AuthenticationConstants.EMAIL),mailCredentials.getProperty(AuthenticationConstants.PASSWORD));
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(AuthenticationConstants.EMAIL));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Registration successful");
            message.setText(String.format("Hello %s.You are registered on the struts test portal.",username));

            Transport.send(message);
        } catch (MessagingException e) {
            LOG.error(String.format("sendAuthorizationMail: Error send email, username=%s", username), e);
        }
    }
}
