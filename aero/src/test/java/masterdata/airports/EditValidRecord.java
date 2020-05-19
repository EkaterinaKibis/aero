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
 * 002 Проверка редактирования записи с валидными полями
 */
public class EditValidRecord {
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

    @Test
    public void editValidfields() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement firstRow = getButton(config.getProperty("firstRowXpath"));
        firstRow.click();
        Thread.sleep(500);

        WebElement iataElement = getField(config.getProperty("iataElementXpath"));
        iataElement.clear();
        Thread.sleep(500);
        iataElement.sendKeys(iata);
        Thread.sleep(500);

        WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
        icaoElement.clear();
        Thread.sleep(500);
        icaoElement.sendKeys(icao);
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
        englishNameElement.clear();
        Thread.sleep(500);
        englishNameElement.sendKeys(englishName);
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
        localNameElement.clear();
        Thread.sleep(500);
        localNameElement.sendKeys(localName);
        Thread.sleep(500);

        WebElement timeZoneWinterElement = getField(config.getProperty("timeZoneWinterXpath"));
        timeZoneWinterElement.clear();
        Thread.sleep(500);
        timeZoneWinterElement.sendKeys("11");
        Thread.sleep(500);

        WebElement timeZoneSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
        timeZoneSummerElement.clear();
        Thread.sleep(500);
        timeZoneSummerElement.sendKeys("11");
        Thread.sleep(500);

        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStartElement.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinishElement.clear();
        Thread.sleep(500);
        dateRangeFinishElement.sendKeys("28.04.2022");
        Thread.sleep(500);

        WebElement saveButton = getButton(config.getProperty("saveButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);
        WebElement text = getField(config.getProperty("forSearchInMainTableXpath"));

        Thread.sleep(500);
        String pageText = text.getText();
        Thread.sleep(500);

        assertThat(pageText.contains(englishName + ", " + localName), is(true));
        assertThat(pageText.contains(iata), is(true));
        assertThat(pageText.contains(icao), is(true));
        Thread.sleep(500);

        String query = "Select * from master_data.masterdata.airports where iata = '" + iata + "'";
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


