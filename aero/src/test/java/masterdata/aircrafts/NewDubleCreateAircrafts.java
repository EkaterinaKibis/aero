package masterdata.aircrafts;

import masterdata.airports.DataStartBiggerThenDataFinishInEdit;
import masterdata.airports.DatabaseChecker;
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

public class NewDubleCreateAircrafts {
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
        InputStream in = DataStartBiggerThenDataFinishInEdit.NewRecordCreationValid.class.getClassLoader().getResourceAsStream("aircraftsTest.properties");
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
        WebElement aircraftsFolder = getButton(config.getProperty("aircraftsFolderXpath"));
        aircraftsFolder.click();
        Thread.sleep(500);

        WebElement newButton = getButton(config.getProperty("newButtonXpath"));
        newButton.click();
        Thread.sleep(500);

        WebElement iataElement = getField(config.getProperty("iataElementXpath"));
        iataElement.sendKeys("OAX");
        Thread.sleep(500);

        WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
        icaoElement.sendKeys(("DHSB"));
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
        englishNameElement.sendKeys("AOPIKFZYNU");
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
        localNameElement.sendKeys("OGMPIRMKQI");
        Thread.sleep(500);

        WebElement mtow = getField(config.getProperty("mtowXpath"));
        mtow.sendKeys("11");
        Thread.sleep(500);

        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStartElement.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinishElement.sendKeys("28.04.2022");
        Thread.sleep(1000);

        WebElement appendButtonElement = getButton(config.getProperty("appendButtonXpath"));
        appendButtonElement.click();
        Thread.sleep(2000);

        WebElement clkoseWithoutSaving = getButton(config.getProperty("clkoseWithoutSavingXpath"));
        clkoseWithoutSaving.click();

        WebElement text = getField(config.getProperty("forSearchInMainTableXpath"));

        Thread.sleep(500);
        String pageText = text.getText();

        assertThat(pageText.contains("OAX"), is(true));
        assertThat(pageText.contains("DHSB"), is(true));

        String query = "Select * from master_data.masterdata.aircrafts where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(query, true);
        driver.close();
    }

    // Завершение создания записи, при всплывании окна дубль
    // Проверяем, создание дубля с другим дата рэнжем
    @Test
    public void  dubleCreateNewDuble() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement aircraftsFolder = getButton(config.getProperty("aircraftsFolderXpath"));
        aircraftsFolder.click();
        Thread.sleep(500);

        WebElement newButton = getButton(config.getProperty("newButtonXpath"));
        newButton.click();
        Thread.sleep(500);

        WebElement iataElement = getField(config.getProperty("iataElementXpath"));
        iataElement.sendKeys("OAX");
        Thread.sleep(500);

        WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
        icaoElement.sendKeys(("DHSB"));
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
        englishNameElement.sendKeys("AOPIKFZYNU");
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
        localNameElement.sendKeys("OGMPIRMKQI");
        Thread.sleep(500);

        WebElement mtow = getField(config.getProperty("mtowXpath"));
        mtow.sendKeys("11");
        Thread.sleep(500);

        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStartElement.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinishElement.sendKeys("28.04.2022");
        Thread.sleep(1000);

        WebElement appendButtonElement = getButton(config.getProperty("appendButtonXpath"));
        appendButtonElement.click();
        Thread.sleep(2000);

        WebElement dublicateError = getButton(config.getProperty("dublicateErrorXpath"));
        Thread.sleep(2000);

        if (dublicateError.isDisplayed() && dublicateError.getText().contains("Duplicate record found with field:")) {
            WebElement dublicateEditButton = getButton(config.getProperty("dublicateEditButtonXpath"));
            dublicateEditButton.click();
            dateRangeStartElement.clear();
            Thread.sleep(500);
            dateRangeStartElement.sendKeys("29.05.2022");
            Thread.sleep(500);
            dateRangeFinishElement.clear();
            Thread.sleep(500);
            dateRangeFinishElement.sendKeys("30.05.2022");
            Thread.sleep(500);

            appendButtonElement.click();
        }
        Thread.sleep(500);

        WebElement text = getField(config.getProperty("forSearchInMainTableXpath"));
        Thread.sleep(500);
        String pageText = text.getText();

        assertThat(pageText.contains("OAX"), is(true));
        assertThat(pageText.contains("DHSB"), is(true));

        String query = "Select * from master_data.masterdata.aircrafts where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(query, true);
        driver.close();
    }
    // Проверяем, сздание дубля через кнопку дубль
    @Test
    public void  dubleCreateDubleButton() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement aircraftsFolder = getButton(config.getProperty("aircraftsFolderXpath"));
        aircraftsFolder.click();
        Thread.sleep(500);

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

        WebElement saveButton = getButton(config.getProperty("appendButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);

        WebElement text = getField(config.getProperty("forSearchInMainTableXpath"));

        Thread.sleep(500);
        String pageText = text.getText();

        assertThat(pageText.contains("OAX"), is(true));
        assertThat(pageText.contains("DHSB"), is(true));

        String query = "Select * from master_data.masterdata.aircrafts where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(query, true);
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