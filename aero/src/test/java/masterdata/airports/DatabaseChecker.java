package masterdata.airports;

import masterdata.SQLStorage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DatabaseChecker {
    private static final Logger LOGGER = LogManager.getLogger(DatabaseChecker.class.getName());
    private Properties config;
    private SQLStorage storage;

    public DatabaseChecker(Properties config, SQLStorage storage) {
        this.config = config;
        this.storage = storage;
    }

    public void existInDB(String query, boolean expected) {
        Connection connection = storage.getConnection();
        String tableName = config.getProperty("tableName");
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            Assert.assertThat(resultSet.next(), is(expected));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        storage.closeConnection();
    }

    public static class SQLStorageTest {
        private static final Logger LOGGER = LogManager.getLogger(SQLStorageTest.class.getName());
        private static SQLStorage storage = SQLStorage.getStorage();


        @Test
        public void testQuery() {
            Connection connection = storage.getConnection();
            String query = storage.getAircraftStatement("master_data.masterdata.airports","id");
            try (
                    PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, 28);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String iataString = resultSet.getString("iata");
                    String icao = resultSet.getString("icao");
                    System.out.println(id + " " + iataString + " " + icao);
                }
                resultSet.close();
            } catch (
                    SQLException e) {
                LOGGER.error(e.getMessage(), e);
                e.printStackTrace();
            }
            storage.closeConnection();
        }
    }
}