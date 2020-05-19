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
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * 004 создания дубля
 */

public class NewDubleCreate {
    WebDriver driver;
    HashMap<String, String> propertiesMap;
    Random random = new Random();
    private static final Logger LOGGER = LogManager.getLogger(DataStartBiggerThenDataFinishInEdit.NewRecordCreationValid.class.getName());
    private static SQLStorage storage = SQLStorage.getStorage();
    Properties config;
    String iata;
    String icao;
    String englishName;
    String localName;

    @Before
    public void initTest() throws IOException {
        InputStream in = DataStartBiggerThenDataFinishInEdit.NewRecordCreationValid.class.getClassLoader().getResourceAsStream("airportsTest.properties");
        config = new Properties();
        config.load(in);

        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver/chromedriver");
        driver = new ChromeDriver();

        iata = getRandomString(3);
        icao = getRandomString(4);
        englishName = getRandomString(10);
        localName = getRandomString(10);
    }
    //выход без сохранения при создании дубля
    @Test
    public void newDubleCreating() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement newButton = getButton(config.getProperty("newButtonXpath"));
        newButton.click();
        Thread.sleep(500);
        WebElement iataElement = getField(config.getProperty("iataElementXpath"));
        iataElement.sendKeys("MVB");
        Thread.sleep(500);

        WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
        icaoElement.sendKeys("MVVW");
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
        englishNameElement.sendKeys("Sochi");
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
        localNameElement.sendKeys("Сочи");
        Thread.sleep(500);

        WebElement timeZoneWinterElement = getField(config.getProperty("timeZoneWinterXpath"));
        timeZoneWinterElement.sendKeys("3");
        Thread.sleep(500);

        WebElement timeZoneSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
        timeZoneSummerElement.sendKeys("3");
        Thread.sleep(500);

        WebElement dateRangeStart = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStart.clear();
        Thread.sleep(500);

        dateRangeStart.sendKeys("05.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinish = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinish.clear();
        Thread.sleep(500);

        dateRangeFinish.sendKeys("05.04.2022");
        Thread.sleep(500);

        WebElement saveButton = getButton(config.getProperty("saveButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);

        WebElement clkoseWithoutSaving = getButton(config.getProperty("clkoseWithoutSavingXpath"));
        clkoseWithoutSaving.click();

        WebElement text = getField(config.getProperty("forSearchInMainTableXpath"));

        Thread.sleep(500);
        String pageText = text.getText();

        assertThat(pageText.contains("MVB"), is(true));
        assertThat(pageText.contains("MVVW"), is(true));

        DatabaseChecker checker = new DatabaseChecker(config, storage);
        String table = config.getProperty("tableName");
        String query = "select iata from " + table + "where dt_range = '[2022-04-05,2022-04-06]'";

        System.out.println(query);
        checker.existInDB(query, true);
        driver.close();
    }

    // Завершение создания записи, при всплывании окна дубль
    // Проверяем, создание дубля с другим дата рэнжем
    @Test
    public void  dubleCreateNewDuble() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement newButton = getButton(config.getProperty("newButtonXpath"));
        newButton.click();
        Thread.sleep(500);
        WebElement iataElement = getField(config.getProperty("iataElementXpath"));
        iataElement.sendKeys("MVB");
        Thread.sleep(500);

        WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
        icaoElement.sendKeys("MVVW");
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
        englishNameElement.sendKeys("Sochi");
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
        localNameElement.sendKeys("Сочи");
        Thread.sleep(500);

        WebElement timeZoneWinterElement = getField(config.getProperty("timeZoneWinterXpath"));
        timeZoneWinterElement.sendKeys("3");
        Thread.sleep(500);

        WebElement timeZoneSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
        timeZoneSummerElement.sendKeys("3");
        Thread.sleep(500);

        WebElement dateRangeStart = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStart.clear();
        Thread.sleep(500);

        dateRangeStart.sendKeys("04.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinish = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinish.clear();
        Thread.sleep(500);

        dateRangeFinish.sendKeys("06.04.2022");
        Thread.sleep(500);

        WebElement saveButton = getButton(config.getProperty("saveButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);
        WebElement dublicateError = getButton(config.getProperty("dublicateErrorXpath"));
        Thread.sleep(2000);

        if (dublicateError.isDisplayed() && dublicateError.getText().contains("Duplicate record found with field:")) {
            WebElement dublicateEditButton = getButton(config.getProperty("dublicateEditButtonXpath"));
            dublicateEditButton.click();
            dateRangeStart.clear();
            dateRangeStart.sendKeys("07.04.2022");
            dateRangeFinish.clear();
            dateRangeFinish.sendKeys("09.04.2022");
            saveButton.click();
        }


        WebElement text = getField(config.getProperty("forSearchInMainTableXpath"));

        Thread.sleep(500);
        String pageText = text.getText();

        assertThat(pageText.contains("MVB"), is(true));
        assertThat(pageText.contains("MVVW"), is(true));

        DatabaseChecker checker = new DatabaseChecker(config, storage);
        String table = config.getProperty("tableName");
        String query = "select iata from " + table + "where dt_range ='[2022-04-05 00:00:00,2022-04-06 23:59:50)'";

        System.out.println(query);
        checker.existInDB(query, true);
        driver.close();
    }
    // Проверяем, сздание дубля через кнопку дубль
    @Test
    public void  dubleCreateDubleButton() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement firstRow = getButton(config.getProperty("firstRowXpath"));
        firstRow.click();
        Thread.sleep(1000);
        WebElement dubleButton = getButton(config.getProperty("dubleButtonXpath"));
        dubleButton.click();

        WebElement dateRangeStart = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStart.clear();
        Thread.sleep(500);

        dateRangeStart.sendKeys("04.05.2024");
        Thread.sleep(500);

        WebElement dateRangeFinish = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinish.clear();
        Thread.sleep(500);

        dateRangeFinish.sendKeys("06.05.2024");
        Thread.sleep(500);

        WebElement saveButton = getButton(config.getProperty("saveButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);

        WebElement text = getField(config.getProperty("forSearchInMainTableXpath"));

        Thread.sleep(500);
        String pageText = text.getText();

        assertThat(pageText.contains("MVB"), is(true));
        assertThat(pageText.contains("MVVW"), is(true));

        DatabaseChecker checker = new DatabaseChecker(config, storage);
        String table = config.getProperty("tableName");
        String query = "select iata from " + table + "where dt_range ='[2022-04-05 00:00:00,2022-04-06 23:59:50)'";

        System.out.println(query);
        checker.existInDB(query, true);
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