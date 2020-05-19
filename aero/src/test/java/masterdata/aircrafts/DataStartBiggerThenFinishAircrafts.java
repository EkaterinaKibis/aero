package masterdata.aircrafts;

import masterdata.SQLStorage;
import masterdata.airports.DatabaseChecker;
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

public class DataStartBiggerThenFinishAircrafts {
    WebDriver driver;
    HashMap<String, String> propertiesMap;
    Random random = new Random();
    private static final Logger LOGGER = LogManager.getLogger(DataStartBiggerThenFinishAircrafts.class.getName());
    private static SQLStorage storage = SQLStorage.getStorage();
    Properties config;
    String iata;
    String icao;
    String englishName;
    String localName;

    @Before
    public void initTest() throws IOException {
        InputStream in = DataStartBiggerThenFinishAircrafts.class.getClassLoader().getResourceAsStream("aircraftsTest.properties");
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
        WebElement aircraftsFolder = getButton(config.getProperty("aircraftsFolderXpath"));
        aircraftsFolder.click();
        Thread.sleep(500);

        WebElement firstRow = getButton(config.getProperty("firstRowXpath"));
        firstRow.click();
        Thread.sleep(500);

        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStartElement.sendKeys("29.04.2024");
        Thread.sleep(500);

        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinishElement.sendKeys("28.04.2024");
        Thread.sleep(1000);
        WebElement appendButtonElement = getButton(config.getProperty("appendButtonXpath"));
        appendButtonElement.click();
        Thread.sleep(2000);

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

        WebElement airlineFolder = getButton(config.getProperty("airlineFolderXpath"));
        airlineFolder.click();
        Thread.sleep(500);

        WebElement newButton = getButton(config.getProperty("airlinesNewButtonXpath"));
        newButton.click();
        Thread.sleep(500);
        WebElement iataElement = getField(config.getProperty("airlinesIataXpath"));
        iataElement.sendKeys(iata);
        Thread.sleep(500);

        WebElement icaoElement = getField(config.getProperty("airlinesIcaoXpath"));
        icaoElement.sendKeys(icao);
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("airlinesEnglishNameXpath"));
        englishNameElement.sendKeys(englishName);
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("airlinesLocalNameXpath"));
        localNameElement.sendKeys(localName);
        Thread.sleep(500);

        WebElement adultWinter = getField(config.getProperty("adultWinterXpath"));
        adultWinter.sendKeys("11");
        Thread.sleep(500);

        WebElement adultSummer = getField(config.getProperty("adultSummerXpath"));
        adultSummer.sendKeys("11");
        Thread.sleep(500);

        WebElement childWinter = getField(config.getProperty("childWinterXpath"));
        childWinter.sendKeys("1");
        Thread.sleep(500);

        WebElement childSummer = getField(config.getProperty("childSummerXpath"));
        childSummer.sendKeys("2");
        Thread.sleep(500);

        WebElement infantWinter = getField(config.getProperty("infantWinterXpath"));
        infantWinter.sendKeys("3");
        Thread.sleep(500);

        WebElement infantSummer = getField(config.getProperty("infantSummerXpath"));
        infantSummer.sendKeys("5");
        Thread.sleep(500);

        WebElement airlinesDataRangeStart = getField(config.getProperty("airlinesDataRangeStartXpath"));
        airlinesDataRangeStart.sendKeys("30.04.2022");
        Thread.sleep(1000);

        WebElement airlinesDataRangeFinish = getField(config.getProperty("airlinesDataRangeFinishXpath"));
        airlinesDataRangeFinish.sendKeys("29.04.2022");
        Thread.sleep(1000);

        WebElement saveButton = getButton(config.getProperty("airlinesAppendButtonXpath"));
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

        WebElement airlineFolder = getButton(config.getProperty("airlineFolderXpath"));
        airlineFolder.click();
        Thread.sleep(500);

        WebElement newButton = getButton(config.getProperty("airlinesNewButtonXpath"));
        newButton.click();
        Thread.sleep(500);
        WebElement iataElement = getField(config.getProperty("airlinesIataXpath"));
        iataElement.sendKeys(iata);
        Thread.sleep(500);

        WebElement icaoElement = getField(config.getProperty("airlinesIcaoXpath"));
        icaoElement.sendKeys(icao);
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("airlinesEnglishNameXpath"));
        englishNameElement.sendKeys(englishName);
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("airlinesLocalNameXpath"));
        localNameElement.sendKeys(localName);
        Thread.sleep(500);

        WebElement adultWinter = getField(config.getProperty("adultWinterXpath"));
        adultWinter.sendKeys("11");
        Thread.sleep(500);

        WebElement adultSummer = getField(config.getProperty("adultSummerXpath"));
        adultSummer.sendKeys("11");
        Thread.sleep(500);

        WebElement childWinter = getField(config.getProperty("childWinterXpath"));
        childWinter.sendKeys("1");
        Thread.sleep(500);

        WebElement childSummer = getField(config.getProperty("childSummerXpath"));
        childSummer.sendKeys("2");
        Thread.sleep(500);

        WebElement infantWinter = getField(config.getProperty("infantWinterXpath"));
        infantWinter.sendKeys("3");
        Thread.sleep(500);

        WebElement infantSummer = getField(config.getProperty("infantSummerXpath"));
        infantSummer.sendKeys("5");
        Thread.sleep(500);

        WebElement airlinesDataRangeStart = getField(config.getProperty("airlinesDataRangeStartXpath"));
        airlinesDataRangeStart.sendKeys("30.04.2022");
        Thread.sleep(1000);

        WebElement airlinesDataRangeFinish = getField(config.getProperty("airlinesDataRangeFinishXpath"));
        airlinesDataRangeFinish.sendKeys("29.04.2022");
        Thread.sleep(1000);


        WebElement saveButton = getButton(config.getProperty("airlinesAppendButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);

        WebElement CloseWithoutSavingButtonInDateRangeMistakeWindow = getButton(config.getProperty("CloseWithoutSavingButtonInDateRangeMistakeWindowXpath"));
        CloseWithoutSavingButtonInDateRangeMistakeWindow.click();

        WebElement text = getField(config.getProperty("airlineTextXpath"));

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
        WebElement airlineFolder = getButton(config.getProperty("airlineFolderXpath"));
        airlineFolder.click();
        Thread.sleep(500);

        WebElement newButton = getButton(config.getProperty("airlinesNewButtonXpath"));
        newButton.click();
        Thread.sleep(500);
        WebElement iataElement = getField(config.getProperty("airlinesIataXpath"));
        iataElement.sendKeys(iata);
        Thread.sleep(500);

        WebElement icaoElement = getField(config.getProperty("airlinesIcaoXpath"));
        icaoElement.sendKeys(icao);
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("airlinesEnglishNameXpath"));
        englishNameElement.sendKeys(englishName);
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("airlinesLocalNameXpath"));
        localNameElement.sendKeys(localName);
        Thread.sleep(500);

        WebElement adultWinter = getField(config.getProperty("adultWinterXpath"));
        adultWinter.sendKeys("11");
        Thread.sleep(500);

        WebElement adultSummer = getField(config.getProperty("adultSummerXpath"));
        adultSummer.sendKeys("11");
        Thread.sleep(500);

        WebElement childWinter = getField(config.getProperty("childWinterXpath"));
        childWinter.sendKeys("1");
        Thread.sleep(500);

        WebElement childSummer = getField(config.getProperty("childSummerXpath"));
        childSummer.sendKeys("2");
        Thread.sleep(500);

        WebElement infantWinter = getField(config.getProperty("infantWinterXpath"));
        infantWinter.sendKeys("3");
        Thread.sleep(500);

        WebElement infantSummer = getField(config.getProperty("infantSummerXpath"));
        infantSummer.sendKeys("5");
        Thread.sleep(500);

        WebElement airlinesDataRangeStart = getField(config.getProperty("airlinesDataRangeStartXpath"));
        airlinesDataRangeStart.sendKeys("30.04.2022");
        Thread.sleep(1000);

        WebElement airlinesDataRangeFinish = getField(config.getProperty("airlinesDataRangeFinishXpath"));
        airlinesDataRangeFinish.sendKeys("29.04.2022");
        Thread.sleep(1000);

        WebElement saveButton = getButton(config.getProperty("airlinesAppendButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);

        WebElement EditButtonInDateRangeMistakeWindow = getButton(config.getProperty("EditButtonInDateRangeMistakeWindow"));
        EditButtonInDateRangeMistakeWindow.click();
        Thread.sleep(2000);
        airlinesDataRangeStart.sendKeys("19.04.2022");
        Thread.sleep(500);

        saveButton.click();
//        WebElement text = getField(config.getProperty("airlineTextXpath"));
//
//        Thread.sleep(500);
//        String pageText = text.getText();
//
//        assertThat(pageText.contains(iata), is(true));
//        assertThat(pageText.contains(icao), is(true));

        String query = "Select * from master_data.masterdata.airlines where icao = '" + icao + "'";
        new DatabaseChecker(config, storage).existInDB(query, false);
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


}