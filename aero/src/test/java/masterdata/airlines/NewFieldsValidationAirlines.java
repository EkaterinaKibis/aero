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
 * 004 Проверка заполнения  полей (всплывание ошибок) при создании новой записи в окне Airlines Append
 */

public class NewFieldsValidationAirlines {
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
// Проверка что поле ИАТА и ИКАО нельзя заполнить меньшим кол-вом буквенных значений

    @Test
    public void checkingMandatoryiatalangh() throws InterruptedException {
        driver.get(config.getProperty("url"));

        WebElement airlineFolder = getButton(config.getProperty("airlineFolderXpath"));
        airlineFolder.click();
        Thread.sleep(500);

        WebElement airlinesNewButton = getButton(config.getProperty("airlinesNewButtonXpath"));
        airlinesNewButton.click();
        Thread.sleep(1000);

        WebElement airlinesIata = getField(config.getProperty("airlinesIataXpath"));
        airlinesIata.sendKeys("x");
        Thread.sleep(500);

        WebElement airlinesIcao = getField(config.getProperty("airlinesIcaoXpath"));
        airlinesIcao.sendKeys("1c");
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
        WebElement iataLenghMistake = getField(config.getProperty("iataLenghMistakeXpath"));
        WebElement icaoLenghMistake = getField(config.getProperty("icaoLenghMistakeXpath"));

        boolean result = iataLenghMistake.getText().equals("IATA code minimum length is 2") && icaoLenghMistake.getText().equals("ICAO code minimum length is 3") && airlinesAppendButton.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '" + icao + "'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        airlinesIata.clear();
        Thread.sleep(200);
        airlinesIata.sendKeys(iata);
        Thread.sleep(500);
        airlinesIcao.clear();
        Thread.sleep(200);
        airlinesIcao.sendKeys(icao);
        Thread.sleep(500);
        airlinesAppendButton.click();
        String querya = "Select * from master_data.masterdata.airports where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(querya, true);
        driver.close();
    }
//Проверяем что в поле English name, IATA ICAO  нельзя вставить значение не на английском языке

    @Test
    public void checkingnumbersValidationiatalangh() throws InterruptedException {
        driver.get(config.getProperty("url"));

        WebElement airlineFolder = getButton(config.getProperty("airlineFolderXpath"));
        airlineFolder.click();
        Thread.sleep(500);

        WebElement airlinesNewButton = getButton(config.getProperty("airlinesNewButtonXpath"));
        airlinesNewButton.click();
        Thread.sleep(1000);

        WebElement airlinesIata = getField(config.getProperty("airlinesIataXpath"));
        airlinesIata.sendKeys("ыв");
        Thread.sleep(500);

        WebElement airlinesIcao = getField(config.getProperty("airlinesIcaoXpath"));
        airlinesIcao.sendKeys("ввв");
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("airlinesEnglishNameXpath"));
        englishNameElement.sendKeys("Тест");
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

        WebElement mistakeiataOnlyEnglishairlines = getField(config.getProperty("mistakeiataOnlyEnglishairlinesXpath"));
        WebElement mistakeEnglishNameEnglishOnly = getField(config.getProperty("mistakeEnglishNameEnglishOnlyXpath"));
        WebElement mistakeicaoEnglishOnly = getField(config.getProperty("mistakeicaoEnglishOnlyXpath"));


        boolean result = mistakeicaoEnglishOnly.getText().equals("ICAO code is English only")  && mistakeEnglishNameEnglishOnly.getText().equals("Name is English only") && mistakeiataOnlyEnglishairlines.getText().equals("IATA code is English only") && airlinesAppendButton.isDisplayed();
        assertThat(result, is(true));
        String query = "Select * from master_data.masterdata.airports where icao = '" + icao + "'";
        new DatabaseChecker(config, storage).existInDB(query, false);
        Thread.sleep(200);

        airlinesIata.clear();
        Thread.sleep(200);
        airlinesIata.sendKeys(iata);
        Thread.sleep(500);
        airlinesIcao.clear();
        Thread.sleep(200);
        airlinesIcao.sendKeys(icao);
        Thread.sleep(500);
        englishNameElement.clear();
        Thread.sleep(500);
        englishNameElement.sendKeys(englishName);
        Thread.sleep(500);
        airlinesAppendButton.click();
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

