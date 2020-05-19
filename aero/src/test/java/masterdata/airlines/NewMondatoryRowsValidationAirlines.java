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
 * 002 Проверка обязательности полей при создании новой записи в окне Airline Append
 */

public class NewMondatoryRowsValidationAirlines {
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
// Проверка что iata code обязательное поле

    @Test
    public void iataMondatoryFieldAirline() throws InterruptedException {
        driver.get(config.getProperty("url"));
        Thread.sleep(500);

        WebElement airlineFolder = getButton(config.getProperty("airlineFolderXpath"));
        airlineFolder.click();
        Thread.sleep(500);

        WebElement newButton = getButton(config.getProperty("airlinesNewButtonXpath"));
        newButton.click();
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

        WebElement airlinesDataRangeStart = getField(config.getProperty("airlinesDataRangeStartXpath"));
        airlinesDataRangeStart.sendKeys("28.04.2022");
        Thread.sleep(1000);

        WebElement airlinesDataRangeFinish = getField(config.getProperty("airlinesDataRangeFinishXpath"));
        airlinesDataRangeFinish.sendKeys("29.04.2022");
        Thread.sleep(1000);

        WebElement airlinesAppendButton = getButton(config.getProperty("airlinesAppendButtonXpath"));
        airlinesAppendButton.click();
        Thread.sleep(2000);

       WebElement iataMondatoryMistake = getField((config.getProperty("iataMondatoryMistakeXpath")));
        boolean result = iataMondatoryMistake.getText().equals("IATA code is required") && airlinesAppendButton.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airlines where iata = '"+iata+"'";
        new DatabaseChecker(config, storage).existInDB(query, false);

        WebElement iataElement = getField(config.getProperty("airlinesIataXpath"));
        iataElement.sendKeys(iata);
        Thread.sleep(500);
        airlinesAppendButton.click();
        String querya = "Select * from master_data.masterdata.airlines where iata = '"+iata+"'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }

    // Проверяем, что icao code обязательное поле
    @Test
    public void icaoMondatoryFieldAirline() throws InterruptedException {
        driver.get(config.getProperty("url"));
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

        WebElement englishNameElement = getField(config.getProperty("airlinesEnglishNameXpath"));
        englishNameElement.sendKeys(englishName);
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("airlinesLocalNameXpath"));
        localNameElement.sendKeys(localName);
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
        WebElement icaoMondatoryMistake = getField((config.getProperty("icaoMondatoryMistakeXpath")));
        boolean result = icaoMondatoryMistake.getText().equals("ICAO code is required") && airlinesAppendButton.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airlines where icao = '"+icao+"'";
        new DatabaseChecker(config, storage).existInDB(query, false);

        WebElement icaoElement = getField(config.getProperty("airlinesIcaoXpath"));
        icaoElement.sendKeys(icao);
        Thread.sleep(500);
        airlinesAppendButton.click();

        String querya = "Select * from master_data.masterdata.airlines where icao = '"+icao+"'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }

    // Проверяем, что English name обязательное поле
    @Test
    public void EnglishNameMondatoryFieldAirline() throws InterruptedException {
        driver.get(config.getProperty("url"));
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


        WebElement localNameElement = getField(config.getProperty("airlinesLocalNameXpath"));
        localNameElement.sendKeys(localName);
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

        WebElement englishNameMondatoryMistake = getField((config.getProperty("englishNameMondatoryMistakeXpath")));
        boolean result = englishNameMondatoryMistake.getText().equals("English name is required") && airlinesAppendButton.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airlines where icao = '"+icao+"'";
        new DatabaseChecker(config, storage).existInDB(query, false);

        WebElement englishNameElement = getField(config.getProperty("airlinesEnglishNameXpath"));
        englishNameElement.sendKeys(englishName);
        Thread.sleep(500);
        airlinesAppendButton.click();

        String querya = "Select * from master_data.masterdata.airlines where icao = '"+icao+"'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }
    // Проверяем, что поле Local Name обязательное поле
    @Test
    public void LocalNameMondatoryAirline() throws InterruptedException {
        driver.get(config.getProperty("url"));
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

        WebElement airlinesDataRangeStart = getField(config.getProperty("airlinesDataRangeStartXpath"));
        airlinesDataRangeStart.sendKeys("28.04.2022");
        Thread.sleep(1000);

        WebElement airlinesDataRangeFinish = getField(config.getProperty("airlinesDataRangeFinishXpath"));
        airlinesDataRangeFinish.sendKeys("29.04.2022");
        Thread.sleep(1000);

        WebElement airlinesAppendButton = getButton(config.getProperty("airlinesAppendButtonXpath"));
        airlinesAppendButton.click();
        Thread.sleep(2000);


        WebElement localnameMondatoryMistake = getField((config.getProperty("localnameMondatoryMistakeXpath")));
        boolean result = localnameMondatoryMistake.getText().equals("Local name is required") && airlinesAppendButton.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airlines where icao = '"+icao+"'";
        new DatabaseChecker(config, storage).existInDB(query, false);

        WebElement localNameElement = getField(config.getProperty("airlinesLocalNameXpath"));
        localNameElement.sendKeys(localName);
        Thread.sleep(500);
        airlinesAppendButton.click();

        String querya = "Select * from master_data.masterdata.airlines where icao = '"+icao+"'";
        new DatabaseChecker(config, storage).existInDB(querya, true);

        driver.close();
    }
    // Проверяем обязательность поля Data Range Start
    @Test
    public void DataRangeStartMondatoryAirline() throws InterruptedException {
        driver.get(config.getProperty("url"));
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


        WebElement airlinesDataRangeFinish = getField(config.getProperty("airlinesDataRangeFinishXpath"));
        airlinesDataRangeFinish.sendKeys("29.04.2022");
        Thread.sleep(1000);

        WebElement airlinesAppendButton = getButton(config.getProperty("airlinesAppendButtonXpath"));
        airlinesAppendButton.click();
        Thread.sleep(2000);



        WebElement dateRangeStartMondatoryMistake = getField((config.getProperty("dateRangeStartMondatoryMistakeXpath")));
        boolean result = dateRangeStartMondatoryMistake.getText().equals("Date range start is required") && airlinesAppendButton.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airlines where icao = '"+icao+"'";
        new DatabaseChecker(config, storage).existInDB(query, false);

        WebElement airlinesDataRangeStart = getField(config.getProperty("airlinesDataRangeStartXpath"));
        airlinesDataRangeStart.sendKeys("28.04.2022");
        Thread.sleep(1000);
        airlinesAppendButton.click();

        String querya = "Select * from master_data.masterdata.airlines where icao = '"+icao+"'";
        new DatabaseChecker(config, storage).existInDB(querya, true);


        driver.close();
    }
    // Проверяем, что DateRangeFinish обязательное поле
    @Test
    public void DataRangeFinishMondatoryAirline() throws InterruptedException {
        driver.get(config.getProperty("url"));
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

        WebElement airlinesDataRangeStart = getField(config.getProperty("airlinesDataRangeStartXpath"));
        airlinesDataRangeStart.sendKeys("28.04.2022");
        Thread.sleep(1000);

        WebElement airlinesAppendButton = getButton(config.getProperty("airlinesAppendButtonXpath"));
        airlinesAppendButton.click();
        Thread.sleep(2000);


        WebElement dateRangeFinishMondatoryMistake = getField((config.getProperty("dateRangeFinishMondatoryMistakeXpath")));
        boolean result = dateRangeFinishMondatoryMistake.getText().equals("Date range finish is required") && airlinesAppendButton.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airlines where icao = '"+icao+"'";
        new DatabaseChecker(config, storage).existInDB(query, false);

        WebElement airlinesDataRangeFinish = getField(config.getProperty("airlinesDataRangeFinishXpath"));
        airlinesDataRangeFinish.sendKeys("29.04.2022");
        Thread.sleep(1000);
        airlinesAppendButton.click();

        String querya = "Select * from master_data.masterdata.airlines where icao = '"+icao+"'";
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


