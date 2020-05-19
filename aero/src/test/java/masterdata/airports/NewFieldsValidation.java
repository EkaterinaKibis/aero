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
 * 004 Проверка заполнения  полей (всплывание ошибок) при создании новой записи в окне Airport Append
 */

public class NewFieldsValidation {
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
// Проверка что поле ИАТА и ИКАО нельзя заполнить меньшим кол-вом буквенных значений

    @Test
    public void checkingMandatoryiatalangh() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement newButton = getButton(config.getProperty("newButtonXpath"));
        newButton.click();
        Thread.sleep(1000);

        WebElement iataEditElement = getField(config.getProperty("iataeditXpath"));
        iataEditElement.clear();
        Thread.sleep(1000);

        iataEditElement.sendKeys("xx");
        Thread.sleep(500);
        WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
        icaoElement.clear();
        icaoElement.sendKeys("1cf");
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
        WebElement miatakeicao = getField(config.getProperty("mistakeIcaoLanghXpath"));

        boolean result = iataemtyfieldMistake.getText().equals("IATA code minimum length is 3") && miatakeicao.getText().equals("ICAO code minimum length is 4") && appendButtonElement.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '" + icao + "'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        iataEditElement.clear();
        Thread.sleep(200);
        iataEditElement.sendKeys(iata);
        Thread.sleep(500);
        icaoElement.clear();
        Thread.sleep(200);
        icaoElement.sendKeys(icao);
        Thread.sleep(500);
        appendButtonElement.click();
        String querya = "Select * from master_data.masterdata.airports where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }
    // Проверка что поле ИАТА нельзя заполнить числовыми значениями

    @Test
    public void checkingnumbersValidationiatalangh() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement newButton = getButton(config.getProperty("newButtonXpath"));
        newButton.click();

        Thread.sleep(1000);
        WebElement iataEditElement = getField(config.getProperty("iataeditXpath"));
        iataEditElement.sendKeys("x11");
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

        WebElement mistakeiataOnlyEnglish = getField(config.getProperty("mistakeiataOnlyEnglishXpath"));

        boolean result = mistakeiataOnlyEnglish.getText().equals("IATA code is English only") && appendButtonElement.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '" + icao + "'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        iataEditElement.clear();
        Thread.sleep(200);
        iataEditElement.sendKeys(iata);
        Thread.sleep(500);
        appendButtonElement.click();
        String querya = "Select * from master_data.masterdata.airports where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }
//Проверяем что в поле English name, IATA ICAO  нельзя вставить значение не на английском языке

    @Test
    public void englishNameValidation() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement newButton = getButton(config.getProperty("newButtonXpath"));
        newButton.click();
        Thread.sleep(500);

        WebElement iataElement = getField(config.getProperty("iataElementXpath"));
        iataElement.sendKeys("вап");
        Thread.sleep(500);

        WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
        icaoElement.sendKeys("вапи");
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
        englishNameElement.clear();
        Thread.sleep(500);
        englishNameElement.sendKeys("Лондон12");
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
        localNameElement.sendKeys(localName);
        Thread.sleep(500);

        WebElement timeZoneWinterElement = getField(config.getProperty("timeZoneWinterXpath"));
        timeZoneWinterElement.clear();
        Thread.sleep(500);
        timeZoneWinterElement.sendKeys("-12");
        Thread.sleep(500);

        WebElement timeZoneSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
        timeZoneSummerElement.clear();
        Thread.sleep(500);
        timeZoneSummerElement.sendKeys("-12");
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
        WebElement mistakeEnglishNameEnglishOnly = getField(config.getProperty("mistakeEnglishNameEnglishOnlyXpath"));
        WebElement mistakeiataOnlyEnglish = getField(config.getProperty("mistakeiataOnlyEnglishXpath"));
        WebElement mistakeicaoEnglishOnly = getField(config.getProperty("mistakeicaoEnglishOnlyXpath"));
        boolean result = mistakeicaoEnglishOnly.getText().equals("ICAO code is English only") && mistakeiataOnlyEnglish.getText().equals("IATA code is English only") && mistakeEnglishNameEnglishOnly.getText().equals("Name is English only") && appendButtonElement.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '" + icao + "'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        iataElement.clear();
        Thread.sleep(200);
        iataElement.sendKeys(iata);
        Thread.sleep(500);
        icaoElement.clear();
        Thread.sleep(200);
        icaoElement.sendKeys(icao);
        Thread.sleep(500);
        englishNameElement.clear();
        Thread.sleep(500);
        englishNameElement.sendKeys(englishName);
        Thread.sleep(500);
        appendButtonElement.click();
        String querya = "Select * from master_data.masterdata.airports where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }

    // Проверяем что диапазон чисел в полях Timezone Winter/summer : Timezone limit -12 +14 hours (Граничное значение -13)
    @Test
    public void checkTimeZoneDownLimit() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement newButton = getButton(config.getProperty("newButtonXpath"));
        newButton.click();
        Thread.sleep(1000);

        WebElement iataElement = getField(config.getProperty("iataElementXpath"));
        iataElement.sendKeys(iata);
        Thread.sleep(500);

        WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
        icaoElement.sendKeys(icao);
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
        englishNameElement.clear();
        Thread.sleep(500);
        englishNameElement.sendKeys(englishName);
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
        localNameElement.sendKeys(localName);
        Thread.sleep(500);

        WebElement timeZoneWinterElement = getField(config.getProperty("timeZoneWinterXpath"));
        timeZoneWinterElement.clear();
        Thread.sleep(500);
        timeZoneWinterElement.sendKeys("-13");
        Thread.sleep(500);

        WebElement timeZoneSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
        timeZoneSummerElement.clear();
        Thread.sleep(500);
        timeZoneSummerElement.sendKeys("-13");
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
        WebElement mistakeTimeZoneWinterLimit = getField(config.getProperty("mistakeTimeZoneWinterLimitXpath"));
        WebElement mistakeTimeZoneSummerLimit = getField(config.getProperty("mistakeTimeZoneSummerLimitXpath"));
        boolean result = mistakeTimeZoneWinterLimit.getText().equals("Timezone limit -12 +14 hours") && mistakeTimeZoneSummerLimit.getText().equals("Timezone limit -12 +14 hours") && appendButtonElement.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '" + icao + "'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        timeZoneWinterElement.clear();
        Thread.sleep(200);
        timeZoneWinterElement.sendKeys("-12");
        Thread.sleep(500);
        timeZoneSummerElement.clear();
        Thread.sleep(200);
        timeZoneSummerElement.sendKeys("-12");
        Thread.sleep(500);
        appendButtonElement.click();
        String querya = "Select * from master_data.masterdata.airports where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }

    // Проверяем что диапазон чисел в полях Timezone Winter/summer : Timezone limit -12 +14 hours (Граничное значение 15)
    @Test
    public void checkTimeZoneUPLimit() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement newButton = getButton(config.getProperty("newButtonXpath"));
        newButton.click();
        Thread.sleep(1000);

        WebElement iataElement = getField(config.getProperty("iataElementXpath"));
        iataElement.sendKeys(iata);
        Thread.sleep(500);

        WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
        icaoElement.sendKeys(icao);
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
        englishNameElement.clear();
        Thread.sleep(500);
        englishNameElement.sendKeys(englishName);
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
        localNameElement.sendKeys(localName);
        Thread.sleep(500);

        WebElement timeZoneWinterElement = getField(config.getProperty("timeZoneWinterXpath"));
        timeZoneWinterElement.clear();
        Thread.sleep(500);
        timeZoneWinterElement.sendKeys("15");
        Thread.sleep(500);

        WebElement timeZoneSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
        timeZoneSummerElement.clear();
        Thread.sleep(500);
        timeZoneSummerElement.sendKeys("15");
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
        WebElement mistakeTimeZoneWinterLimit = getField(config.getProperty("mistakeTimeZoneWinterLimitXpath"));
        WebElement mistakeTimeZoneSummerLimit = getField(config.getProperty("mistakeTimeZoneSummerLimitXpath"));
        boolean result = (mistakeTimeZoneWinterLimit.getText().equals("Timezone limit -12 +14 hours") && mistakeTimeZoneSummerLimit.getText().equals("Timezone limit -12 +14 hours") && appendButtonElement.isDisplayed());
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '" + icao + "'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        timeZoneWinterElement.clear();
        Thread.sleep(200);
        timeZoneWinterElement.sendKeys("14");
        Thread.sleep(500);
        timeZoneSummerElement.clear();
        Thread.sleep(200);
        timeZoneSummerElement.sendKeys("14");
        Thread.sleep(500);
        appendButtonElement.click();
        String querya = "Select * from master_data.masterdata.airports where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }

      //
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
