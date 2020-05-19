package masterdata;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLStorage {
    private static final Logger LOGGER = LogManager.getLogger(SQLStorage.class.getName());

    private Connection connection;
    private static SQLStorage storage = new SQLStorage();

    public static SQLStorage getStorage() {
        return storage;
    }

    public Connection getConnection() {
        try (InputStream in = SQLStorage.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
        return this.connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public String getAircraftStatement(String tableName, String columnToCheck) {
        return "select * from " + tableName + " where " + columnToCheck + " = ?";
    }

}
