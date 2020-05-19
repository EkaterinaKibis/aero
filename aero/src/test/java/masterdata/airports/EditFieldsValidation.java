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
 * 003 Проверка заполнения  полей при редактировании записи в окне Airport Append
 */

public class EditFieldsValidation {
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
        WebElement firstRow = getButton(config.getProperty("firstRowXpath"));
        firstRow.click();
        Thread.sleep(1000);
        WebElement iataEditElement = getField(config.getProperty("iataeditXpath"));
        iataEditElement.clear();
        Thread.sleep(1000);
        iataEditElement.sendKeys("vv");
        Thread.sleep(500);
        WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
        icaoElement.clear();
        icaoElement.sendKeys("dcf");
        Thread.sleep(500);
        WebElement saveButton = getButton(config.getProperty("saveButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);
        WebElement iataemtyfieldMistake = getField(config.getProperty("iataemtyfieldMistakeXpath"));
        WebElement miatakeicao = getField(config.getProperty("mistakeIcaoLanghXpath"));
        boolean result = iataemtyfieldMistake.getText().equals("IATA code minimum length is 3") && miatakeicao.getText().equals("ICAO code minimum length is 4") && saveButton.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = 'dcf'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        iataEditElement.clear();
        Thread.sleep(200);
        iataEditElement.sendKeys(iata);
        Thread.sleep(500);
        icaoElement.clear();
        Thread.sleep(200);
        icaoElement.sendKeys(icao);
        Thread.sleep(500);
        saveButton.click();
        Thread.sleep(500);
        String querya = "Select * from master_data.masterdata.airports where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }
//Проверяем что в поле English name нельзя вставить значение не на английском языке

    @Test
    public void englishNameValidation() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement firstRow = getButton(config.getProperty("firstRowXpath"));
        firstRow.click();
        Thread.sleep(1000);
        WebElement iataEditElement = getField(config.getProperty("iataeditXpath"));
        iataEditElement.clear();
        Thread.sleep(500);
        iataEditElement.sendKeys(iata);
        Thread.sleep(500);
        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
        englishNameElement.clear();
        Thread.sleep(500);
        englishNameElement.sendKeys("Лондон");
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
        WebElement saveButton = getButton(config.getProperty("saveButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);
        WebElement mistakeEnglishOnly = getField(config.getProperty("mistakeEnglishNameEnglishOnlyXpath"));
        boolean result = mistakeEnglishOnly.getText().equals("Name is English only") && saveButton.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where iata = '"+iata+"'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        englishNameElement.clear();
        Thread.sleep(200);
        englishNameElement.sendKeys("London");
        Thread.sleep(500);
        saveButton.click();
        Thread.sleep(500);
        String querya = "Select * from master_data.masterdata.airports where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }

    // Проверяем что диапазон чисел в полях Timezone Winter/summer : Timezone limit -12 +14 hours (Граничное значение -13 и -12)
    @Test
    public void checkTimeZoneDownLimit() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement firstRow = getButton(config.getProperty("firstRowXpath"));
        firstRow.click();
        Thread.sleep(1000);

        WebElement iataEditElement = getField(config.getProperty("iataeditXpath"));
        iataEditElement.clear();
        Thread.sleep(500);
        iataEditElement.sendKeys(iata);
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

        WebElement saveButton = getButton(config.getProperty("saveButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);
        WebElement mistakeTimeZoneWinterLimit = getField(config.getProperty("mistakeTimeZoneWinterLimitXpath"));
        WebElement mistakeTimeZoneSummerLimit = getField(config.getProperty("mistakeTimeZoneSummerLimitXpath"));
        boolean result = mistakeTimeZoneWinterLimit.getText().equals("Timezone limit -12 +14 hours") && mistakeTimeZoneSummerLimit.getText().equals("Timezone limit -12 +14 hours") && saveButton.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '"+ icao +"'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        timeZoneWinterElement.clear();
        Thread.sleep(200);
        timeZoneWinterElement.sendKeys("-12");
        Thread.sleep(500);
        timeZoneSummerElement.clear();
        Thread.sleep(200);
        timeZoneSummerElement.sendKeys("-12");
        Thread.sleep(500);
        saveButton.click();
        String querya = "Select * from master_data.masterdata.airports where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }

    // Проверяем что диапазон чисел в полях Timezone Winter/summer : Timezone limit -12 +14 hours (Граничное значение 15)
    @Test
    public void checkTimeZoneUPLimit() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement firstRow = getButton(config.getProperty("firstRowXpath"));
        firstRow.click();
        Thread.sleep(1000);

        WebElement iataEditElement = getField(config.getProperty("iataeditXpath"));
        iataEditElement.clear();
        Thread.sleep(500);
        iataEditElement.sendKeys(iata);
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

        WebElement saveButton = getButton(config.getProperty("saveButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);
        WebElement mistakeTimeZoneWinterLimit = getField(config.getProperty("mistakeTimeZoneWinterLimitXpath"));
        WebElement mistakeTimeZoneSummerLimit = getField(config.getProperty("mistakeTimeZoneSummerLimitXpath"));
        boolean result = mistakeTimeZoneWinterLimit.getText().equals("Timezone limit -12 +14 hours") && mistakeTimeZoneSummerLimit.getText().equals("Timezone limit -12 +14 hours") && saveButton.isDisplayed();
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
        saveButton.click();
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