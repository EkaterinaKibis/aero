package masterdata.airports;

import masterdata.SQLStorage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * 004 Проверка того что запись не сохранится при условии Data range start > Data range Finish. Выдается сообщение об ошибке
 */

public class DataStartBiggerThenDataFinishInEdit {
    WebDriver driver;
    HashMap<String, String> propertiesMap;
    Random random = new Random();
    private static final Logger LOGGER = LogManager.getLogger(masterdata.airports.DataStartBiggerThenDataFinishInEdit.NewRecordCreationValid.class.getName());
    private static SQLStorage storage = SQLStorage.getStorage();
    Properties config;
    String iata;
    String icao;
    String englishName;
    String localName;

    @Before
    public void initTest() throws IOException {
        InputStream in = DataStartBiggerThenDataFinishInEdit.class.getClassLoader().getResourceAsStream("airportsTest.properties");
        config = new Properties();
        config.load(in);

        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver/chromedriver");
        driver = new ChromeDriver();

        iata = getRandomString(3);
        icao = getRandomString(4);
        englishName = getRandomString(10);
        localName = getRandomString(10);
    }

    // Проверка для окна редактирования
    @Test
    public void checkingInExistig() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement firstRow = getButton(config.getProperty("firstRowXpath"));
        firstRow.click();
        Thread.sleep(1000);

        WebElement dateRangeStart = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStart.clear();
        Thread.sleep(500);
        dateRangeStart.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinish = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinish.clear();
        Thread.sleep(500);
        dateRangeFinish.sendKeys("20.04.2022");
        Thread.sleep(500);

        WebElement saveButton = getButton(config.getProperty("saveButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);
        WebElement dateRangeMistake = getField(config.getProperty("DateRangeMistakeXpath"));
        WebElement editButtonInDateRangeMistakeWindow = getButton(config.getProperty("EditButtonInDateRangeMistakeWindow"));
        boolean result = editButtonInDateRangeMistakeWindow.isDisplayed() && dateRangeMistake.isDisplayed() && dateRangeMistake.getText().equals("Date range start cannot be greater than Date range finish");
        assertThat(result, is(true));
        driver.close();
    }

    // Проверка при добавлении новой записи
    @Test
    public void checingNewCreation() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement newButton = getButton(config.getProperty("newButtonXpath"));
        newButton.click();
        Thread.sleep(500);
        WebElement iataElement = getField(config.getProperty("iataElementXpath"));
        iataElement.sendKeys(iata);
        Thread.sleep(500);

        WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
        icaoElement.sendKeys(icao);
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
        englishNameElement.sendKeys(englishName);
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
        localNameElement.sendKeys(localName);
        Thread.sleep(500);

        WebElement timeZoneWinterElement = getField(config.getProperty("timeZoneWinterXpath"));
        timeZoneWinterElement.sendKeys("11");
        Thread.sleep(500);

        WebElement timeZoneSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
        timeZoneSummerElement.sendKeys("11");
        Thread.sleep(500);
        WebElement dateRangeStart = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStart.clear();
        Thread.sleep(500);
        dateRangeStart.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinish = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinish.clear();
        Thread.sleep(500);
        dateRangeFinish.sendKeys("20.04.2022");
        Thread.sleep(500);

        WebElement saveButton = getButton(config.getProperty("saveButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);

        WebElement dateRangeMistake = getField(config.getProperty("DateRangeMistakeXpath"));
        WebElement editButtonInDateRangeMistakeWindow = getButton(config.getProperty("EditButtonInDateRangeMistakeWindow"));
        boolean result = editButtonInDateRangeMistakeWindow.isDisplayed() && dateRangeMistake.isDisplayed() && dateRangeMistake.getText().equals("Date range start cannot be greater than Date range finish");
        assertThat(result, is(true));

        driver.close();
    }

    // Проверяем, что при условии, что Date Range Start > Date Range Finish и нажатии кнопки Close,without Saving запись не запишется в базу и на фронт
    @Test
    public void checingCloseWithoutSaving() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement newButton = getButton(config.getProperty("newButtonXpath"));
        newButton.click();
        Thread.sleep(500);
        WebElement iataElement = getField(config.getProperty("iataElementXpath"));
        iataElement.sendKeys(iata);
        Thread.sleep(500);

        WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
        icaoElement.sendKeys(icao);
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
        englishNameElement.sendKeys(englishName);
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
        localNameElement.sendKeys(localName);
        Thread.sleep(500);

        WebElement timeZoneWinterElement = getField(config.getProperty("timeZoneWinterXpath"));
        timeZoneWinterElement.sendKeys("11");
        Thread.sleep(500);

        WebElement timeZoneSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
        timeZoneSummerElement.sendKeys("11");
        Thread.sleep(500);

        WebElement dateRangeStart = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStart.clear();
        Thread.sleep(500);

        dateRangeStart.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinish = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinish.clear();
        Thread.sleep(500);

        dateRangeFinish.sendKeys("20.04.2022");
        Thread.sleep(500);

        WebElement saveButton = getButton(config.getProperty("saveButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);

        WebElement CloseWithoutSavingButtonInDateRangeMistakeWindow = getButton(config.getProperty("CloseWithoutSavingButtonInDateRangeMistakeWindowXpath"));
        CloseWithoutSavingButtonInDateRangeMistakeWindow.click();

        WebElement text = getField(config.getProperty("forSearchInMainTableXpath"));

        Thread.sleep(500);
        String pageText = text.getText();

        assertThat(pageText.contains(iata), is(false));
        assertThat(pageText.contains(icao), is(false));

        Connection connection = storage.getConnection();
        String tableName = config.getProperty("tableName");
        String query = "select name from " + tableName + " where (iata = ? and icao = ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, iata);
            statement.setString(2, icao);
            ResultSet resultSet = statement.executeQuery();
            assertThat(resultSet.next(), is(false));
            resultSet.close();
        } catch (
                SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        storage.closeConnection();
        driver.close();
    }

    // Проверяем, что при условии, что Date Range Start > Date Range Finish и нажатии кнопки Edit откроется окно редактирования, и выставив даты верно запись создастся
    @Test
    public void checingEditAndCreate() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement newButton = getButton(config.getProperty("newButtonXpath"));
        newButton.click();
        Thread.sleep(500);
        WebElement iataElement = getField(config.getProperty("iataElementXpath"));
        iataElement.sendKeys(iata);
        Thread.sleep(500);

        WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
        icaoElement.sendKeys(icao);
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
        englishNameElement.sendKeys(englishName);
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
        localNameElement.sendKeys(localName);
        Thread.sleep(500);

        WebElement timeZoneWinterElement = getField(config.getProperty("timeZoneWinterXpath"));
        timeZoneWinterElement.sendKeys("11");
        Thread.sleep(500);

        WebElement timeZoneSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
        timeZoneSummerElement.sendKeys("11");
        Thread.sleep(500);

        WebElement dateRangeStart = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStart.clear();
        Thread.sleep(500);

        dateRangeStart.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinish = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinish.clear();
        Thread.sleep(500);

        dateRangeFinish.sendKeys("20.04.2022");
        Thread.sleep(500);

        WebElement saveButton = getButton(config.getProperty("saveButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);

        WebElement EditButtonInDateRangeMistakeWindow = getButton(config.getProperty("EditButtonInDateRangeMistakeWindow"));
        EditButtonInDateRangeMistakeWindow.click();
        Thread.sleep(2000);
        dateRangeStart.sendKeys("19.04.2022");
        Thread.sleep(500);

        saveButton.click();
        WebElement text = getField(config.getProperty("forSearchInMainTableXpath"));

        Thread.sleep(500);
        String pageText = text.getText();

        assertThat(pageText.contains(iata), is(true));
        assertThat(pageText.contains(icao), is(true));

        String dbCallResult = null;
        Connection connection = storage.getConnection();
        String tableName = config.getProperty("tableName");
        String query = "select name from " + tableName + " where (iata = ? and icao = ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, iata);
            statement.setString(2, icao);


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dbCallResult = resultSet.getString("name");
            }
            resultSet.close();
        } catch (
                SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        storage.closeConnection();
        String expected = "{" + englishName + "," + localName + "}";
        assertThat(dbCallResult, is(expected));
        driver.close();
    }

    private WebElement getButton(String xPath) {
        return driver.findElement(By.xpath(xPath));
    }

    private WebElement getField(String xPath) {
        return driver.findElement(By.xpath(xPath));
    }

    private String getRandomString(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char letter = (char) (random.nextInt(26) + 'a');
            result.append(letter);
        }
        return result.toString().toUpperCase();
    }

    public class NewRecordCreationValid {
    }
}
