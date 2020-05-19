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
 * 005 Проверка обязательности полей при создании новой записи в окне Airport Append
 */

public class NewMondatoryRowsValidation {
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
// Проверка что iata обязательное поле
    @Test
    public void checkingMandatoryiataFielsViaCreation() throws InterruptedException {
        driver.get(config.getProperty("url"));

       WebElement newButton = getButton(config.getProperty("newButtonXpath"));
        newButton.click();
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

        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStartElement.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinishElement.sendKeys("28.04.2022");
        Thread.sleep(500);

        WebElement appendButtonElement = getButton(config.getProperty("appendButtonXpath"));
        appendButtonElement.click();
        Thread.sleep(2000);
        WebElement iataemtyfieldMistake = getField(config.getProperty("iataemtyfieldMistakeXpath"));
        boolean result = iataemtyfieldMistake.getText().equals("IATA code is required") && appendButtonElement.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '"+icao+"'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        WebElement iataElement = getField(config.getProperty("iataElementXpath"));
        iataElement.sendKeys(iata);
        Thread.sleep(500);
        appendButtonElement.click();
        String querya = "Select * from master_data.masterdata.airports where iata = '"+iata+"'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }
    // Проверка что icao обязательное поле
    @Test
    public void checkingMandatoryicaoFielsViaCreation() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement newButton = getButton(config.getProperty("newButtonXpath"));
        newButton.click();
        Thread.sleep(500);
        WebElement iataElement = getField(config.getProperty("iataElementXpath"));
        iataElement.sendKeys(iata);
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

        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStartElement.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinishElement.sendKeys("28.04.2022");
        Thread.sleep(500);

        WebElement appendButtonElement = getButton(config.getProperty("appendButtonXpath"));
        appendButtonElement.click();
        Thread.sleep(2000);
        WebElement icaoemptyfieldMistake = getField(config.getProperty("icaoemptyfieldMistakeXpath"));
        boolean result = icaoemptyfieldMistake.getText().equals("ICAO code is required") && appendButtonElement.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '"+icao+"'";
        new DatabaseChecker(config, storage).existInDB(query, false);
         WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
        icaoElement.sendKeys(icao);
        Thread.sleep(500);
        appendButtonElement.click();
        String querya = "Select * from master_data.masterdata.airports where iata = '"+iata+"'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }
    // Проверка что englishName обязательное поле
    @Test
    public void checkingMandatoryEnglishNameFielsViaCreation() throws InterruptedException {
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

        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
        localNameElement.sendKeys(localName);
        Thread.sleep(500);

        WebElement timeZoneWinterElement = getField(config.getProperty("timeZoneWinterXpath"));
        timeZoneWinterElement.sendKeys("11");
        Thread.sleep(500);

        WebElement timeZoneSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
        timeZoneSummerElement.sendKeys("11");
        Thread.sleep(500);

        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStartElement.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinishElement.sendKeys("28.04.2022");
        Thread.sleep(500);

        WebElement appendButtonElement = getButton(config.getProperty("appendButtonXpath"));
        appendButtonElement.click();
        Thread.sleep(2000);
        WebElement englishNameEmptyfieldMistake = getField(config.getProperty("englishNameEmptyfieldMistakeXpath"));
        boolean result = englishNameEmptyfieldMistake.getText().equals("English name is required") && appendButtonElement.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '"+icao+"'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
        englishNameElement.sendKeys(englishName);
        Thread.sleep(500);
        appendButtonElement.click();
        String querya = "Select * from master_data.masterdata.airports where iata = '"+iata+"'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }
    // Проверка что localName обязательное поле

    @Test
    public void checkingMandatoryLocalNameFielsViaCreation() throws InterruptedException {
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

        WebElement timeZoneWinterElement = getField(config.getProperty("timeZoneWinterXpath"));
        timeZoneWinterElement.sendKeys("11");
        Thread.sleep(500);

        WebElement timeZoneSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
        timeZoneSummerElement.sendKeys("11");
        Thread.sleep(500);

        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStartElement.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinishElement.sendKeys("28.04.2022");
        Thread.sleep(500);

        WebElement appendButtonElement = getButton(config.getProperty("appendButtonXpath"));
        appendButtonElement.click();
        Thread.sleep(2000);
        WebElement localNameEmptyfieldMistake = getField(config.getProperty("localNameEmptyfieldMistakeXpath"));
        boolean result = localNameEmptyfieldMistake.getText().equals("Local name is required") && appendButtonElement.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '" + icao + "'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
        localNameElement.sendKeys(localName);
        Thread.sleep(500);
        appendButtonElement.click();
        String querya = "Select * from master_data.masterdata.airports where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }
    // Проверка что timeZoneWinterElement обязательное поле

    @Test
    public void checkingMandatoryTimeZoneWinterFielsViaCreation() throws InterruptedException {
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

        WebElement timeZoneSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
        timeZoneSummerElement.sendKeys("11");
        Thread.sleep(500);

        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStartElement.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinishElement.sendKeys("28.04.2022");
        Thread.sleep(500);

        WebElement appendButtonElement = getButton(config.getProperty("appendButtonXpath"));
        appendButtonElement.click();
        Thread.sleep(2000);
        WebElement timeZoneWinterEmptyfieldMistake = getField(config.getProperty("timeZoneWinterEmptyfieldMistakeXpath"));
        boolean result = timeZoneWinterEmptyfieldMistake.getText().equals("Timezone winter is required") && appendButtonElement.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '" + icao + "'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        WebElement timeZoneWinterElement = getField(config.getProperty("timeZoneWinterXpath"));
        timeZoneWinterElement.sendKeys("1");
        Thread.sleep(500);
        appendButtonElement.click();
        String querya = "Select * from master_data.masterdata.airports where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }
    // Проверка что timeZoneSummerElement обязательное поле
    @Test
    public void checkingMandatoryTimeZoneSummerFielsViaCreation() throws InterruptedException {
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

        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStartElement.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinishElement.sendKeys("28.04.2022");
        Thread.sleep(500);

        WebElement appendButtonElement = getButton(config.getProperty("appendButtonXpath"));
        appendButtonElement.click();
        Thread.sleep(2000);
        WebElement timeZoneSummerEmptyfieldMistake = getField(config.getProperty("timeZoneSummerEmptyfieldMistakeXpath"));
        boolean result = timeZoneSummerEmptyfieldMistake.getText().equals("Timezone is required") && appendButtonElement.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '" + icao + "'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        WebElement timeSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
        timeSummerElement.sendKeys("1");
        Thread.sleep(500);
        appendButtonElement.click();
        String querya = "Select * from master_data.masterdata.airports where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }
    // Проверка что dateRangeStartElement  обязательное поле
    @Test
    public void checkingMandatoryDateRangeStartFielsViaCreation() throws InterruptedException {
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

        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinishElement.sendKeys("28.04.2022");
        Thread.sleep(500);

        WebElement appendButtonElement = getButton(config.getProperty("appendButtonXpath"));
        appendButtonElement.click();
        Thread.sleep(2000);
        WebElement dateRangeStartEmptyfieldMistake = getField(config.getProperty("dateRangeStartEmptyfieldMistakeXpath"));
        boolean result = dateRangeStartEmptyfieldMistake.getText().equals("Date range start is required") && appendButtonElement.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '" + icao + "'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStartElement.sendKeys("23.04.2022");
        Thread.sleep(500);
        appendButtonElement.click();
        String querya = "Select * from master_data.masterdata.airports where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
        }
    // Проверка что dateRangeFinishElement  обязательное поле

    @Test
    public void checkingMandatoryDateRangeFinishtFielsViaCreation() throws InterruptedException {
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
        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStartElement.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement appendButtonElement = getButton(config.getProperty("appendButtonXpath"));
        appendButtonElement.click();
        Thread.sleep(2000);
        WebElement dateRangeFinishEmptyfieldMistake = getField(config.getProperty("dateRangeFinishEmptyfieldMistakeXpath"));
        boolean result = dateRangeFinishEmptyfieldMistake.getText().equals("Date range finish is required") && appendButtonElement.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '" + icao + "'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinishElement.sendKeys("25.04.2022");
        Thread.sleep(500);
        appendButtonElement.click();
        String querya = "Select * from master_data.masterdata.airports where iata = '" + iata + "'";
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