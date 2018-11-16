package ua.com.struts.dao;

import java.util.Properties;
import org.apache.log4j.Logger;
import ua.com.struts.utils.AuthenticationConstants;
import ua.com.struts.utils.DatabaseException;
import ua.com.struts.utils.PropertiesUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SybaseConnector {
    private static Connection connection;
    private static final Logger LOG = Logger.getLogger(SybaseConnector.class);

    public static Connection connect(String userName, String password) {
        try {
            Class.forName(AuthenticationConstants.DRIVER_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please add JDBC jar to project.", e);
        }

        try {
            if (connection != null) {
                return connection;
            }
            Properties properties = PropertiesUtils.loadProperties(AuthenticationConstants.DB_PROP_FILENAME);
            String url = String.format("jdbc:jtds:sybase://%s:%s/", properties.getProperty(AuthenticationConstants.HOST), properties.getProperty(AuthenticationConstants.PORT));
            connection = DriverManager.getConnection(url,
                    userName, password);
        } catch (SQLException e) {
            connection = null;
            throw new DatabaseException(
                    String.format("Can't get connection for model :sybase user:%s", userName), e);
        }

        return connection;
    }

    public static void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (Exception e) {
            LOG.error("disconnect: Error", e);
        }
    }
}
