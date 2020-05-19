package masterdata.airlines;

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
 * 001 Проверка  создания новой записи в окне Airlines
 */

public class NewRecordCreateAirline {
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
        InputStream in = DataStartBiggerThenDataFinishInEdit.NewRecordCreationValid.class.getClassLoader().getResourceAsStream("airlinesTest.properties");
        config = new Properties();
        config.load(in);

        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver/chromedriver");
        driver = new ChromeDriver();

        iata = getRandomString(2);
        icao = getRandomString(3);
        englishName = getRandomString(10);
        localName = getRandomString(10);

    }

    @Test
    public void newRecordCreationAirline() throws InterruptedException {
        driver.get(config.getProperty("url"));
        Thread.sleep(500);

        WebElement Spravochnik = getButton(config.getProperty("SpravochnikXpath"));
        Spravochnik.click();
        Thread.sleep(500);

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
        airlinesDataRangeStart.sendKeys("28.04.2022");
        Thread.sleep(1000);

        WebElement airlinesDataRangeFinish = getField(config.getProperty("airlinesDataRangeFinishXpath"));
        airlinesDataRangeFinish.sendKeys("29.04.2022");
        Thread.sleep(1000);

        WebElement airlinesAppendButton = getButton(config.getProperty("airlinesAppendButtonXpath"));
        airlinesAppendButton.click();
        Thread.sleep(2000);

        WebElement airlineText = getField(config.getProperty("airlineTextXpath"));
        assertThat(airlineText.getText().contains(iata), is(true));
        assertThat(airlineText.getText().contains(icao), is(true));
        String querya = "Select * from master_data.masterdata.airlines where iata = '"+iata+"'";
       new DatabaseChecker(config, storage).existInDB(querya, true);

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

