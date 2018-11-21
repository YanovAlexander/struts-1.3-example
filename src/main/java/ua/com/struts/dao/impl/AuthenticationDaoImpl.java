package ua.com.struts.dao.impl;

import org.apache.log4j.Logger;
import ua.com.struts.dao.AuthenticationDao;
import ua.com.struts.dao.SybaseConnector;
import ua.com.struts.model.User;
import ua.com.struts.utils.AuthenticationConstants;
import ua.com.struts.utils.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDaoImpl implements AuthenticationDao {

    private static final Logger LOG = Logger.getLogger(AuthenticationDaoImpl.class);
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_COUNTRY_CODE = "country_code";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_AUTHY_ID = "authy_id";

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        try {
            Connection connection = SybaseConnector.connect(AuthenticationConstants.DATABASE_USERNAME,
                    AuthenticationConstants.DATABASE_PASSWORD);
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, username, email, country_code, phone_number, authy_id FROM users WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return getUser(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public User findUserById(long userId) {
        try {
            Connection connection = SybaseConnector.connect(AuthenticationConstants.DATABASE_USERNAME,
                    AuthenticationConstants.DATABASE_PASSWORD);
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, username, email, country_code, phone_number, authy_id FROM users WHERE id = ?");
            ps.setLong(1, userId);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return getUser(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isUserExist(String username, String  password) {
        boolean status = false;
        try {
            Connection connection = SybaseConnector.connect(AuthenticationConstants.DATABASE_USERNAME, AuthenticationConstants.DATABASE_PASSWORD);

            PreparedStatement ps = connection.prepareStatement(
                    "SELECT email, password FROM users WHERE email = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet resultSet = ps.executeQuery();
            status = resultSet.next();

            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            LOG.error("isUserExist: unexpected exception : " + e.toString() + ", sqlstate = " + e.getSQLState());
            return status;
        } catch (DatabaseException e) {
            LOG.error("isUserExist: connection refused");
            throw new DatabaseException(e.getMessage(), e);
        } catch (Exception e) {
            LOG.error("isUserExist: Error", e);
            return status;
        }

        return status;
    }

    @Override
    public void saveUser(String username, String userPassword, String email, String countryCode, String phoneNumber, int authyId) {
        try {
            Connection connection = SybaseConnector.connect(AuthenticationConstants.DATABASE_USERNAME, AuthenticationConstants.DATABASE_PASSWORD);

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO users(username, password, email, country_code, phone_number, authy_id) VALUES (?,?,?,?,?,?)");
            ps.setString(1, username);
            ps.setString(2, userPassword);
            ps.setString(3, email);
            ps.setString(4, countryCode);
            ps.setString(5, phoneNumber);
            ps.setInt(6, authyId);
            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            LOG.error("saveUser: unexpected exception : " + e.toString() + ", sqlstate = " + e.getSQLState());
            throw new RuntimeException("saveUser error");
        } catch (DatabaseException e) {
            LOG.error("saveUser: connection refused");
            throw new DatabaseException(e.getMessage(), e);
        } catch (Exception e) {
            LOG.error("saveUser: Error", e);
            throw new RuntimeException("saveUser error");
        }
    }

    @Override
    public boolean isUserExist(String username) {
        boolean status = false;
        try {
            Connection connection = SybaseConnector.connect(AuthenticationConstants.DATABASE_USERNAME, AuthenticationConstants.DATABASE_PASSWORD);

            PreparedStatement ps = connection.prepareStatement(
                    "SELECT username, password FROM users WHERE username = ?");
            ps.setString(1, username);

            ResultSet resultSet = ps.executeQuery();
            status = resultSet.next();

            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            LOG.error("isUserExist: unexpected exception : " + e.toString() + ", sqlstate = " + e.getSQLState());
            return status;
        } catch (DatabaseException e) {
            LOG.error("isUserExist: connection refused");
            throw new DatabaseException(e.getMessage(), e);
        } catch (Exception e) {
            LOG.error("isUserExist: Error", e);
            return status;
        }

        return status;
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(COLUMN_ID));
        user.setUsername(rs.getString(COLUMN_USERNAME));
        user.setEmail(rs.getString(COLUMN_EMAIL));
        user.setCountryCode(rs.getString(COLUMN_COUNTRY_CODE));
        user.setPhoneNumber(rs.getString(COLUMN_PHONE_NUMBER));
        user.setAuthyId(rs.getInt(COLUMN_AUTHY_ID));
        return user;
    }
}
