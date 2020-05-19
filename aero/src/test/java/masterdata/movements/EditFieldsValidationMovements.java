package masterdata.movements;

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
 * 004 Проверка заполнения  полей (всплывание ошибок) при редактировании записи в окне Movements Append
 */

public class EditFieldsValidationMovements {
    WebDriver driver;
    HashMap<String, String> propertiesMap;
    Random random = new Random();
    private static final Logger LOGGER = LogManager.getLogger(DataStartBiggerThenDataFinishInEdit.NewRecordCreationValid.class.getName());
    private static SQLStorage storage = SQLStorage.getStorage();
    Properties config;
    String iata;
    String englishName;
    String localName;

    @Before
    public void initTest() throws IOException {
        InputStream in = DataStartBiggerThenDataFinishInEdit.NewRecordCreationValid.class.getClassLoader().getResourceAsStream("movementsTest.properties");
        config = new Properties();
        config.load(in);

        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver/chromedriver");
        driver = new ChromeDriver();

        iata = getRandomString(1);

        englishName = getRandomString(10);
        localName = getRandomString(10);
    }


//Проверяем что в поле English name, IATA  нельзя вставить значение не на английском языке

    @Test
    public void englishNameValidation() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement movementsFolder = getButton(config.getProperty("movementsFolderXpath"));
        movementsFolder.click();
        Thread.sleep(500);

        WebElement firstRow = getButton(config.getProperty("firstRowXpath"));
        firstRow.click();
        Thread.sleep(500);

        WebElement iataElement = getField(config.getProperty("iataElementXpath"));
        iataElement.clear();
        iataElement.sendKeys("л");
        Thread.sleep(500);

        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
        englishNameElement.clear();
        Thread.sleep(500);
        englishNameElement.sendKeys("рараррв");
        Thread.sleep(500);

        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
        localNameElement.sendKeys(localName);
        Thread.sleep(500);

        WebElement appendButtonElement = getButton(config.getProperty("appendButtonXpath"));
        appendButtonElement.click();
        Thread.sleep(2000);

        WebElement mistakeEnglishNameEnglishOnly = getField(config.getProperty("mistakeEnglishNameEnglishOnlyXpath"));
        WebElement mistakeiataOnlyEnglish = getField(config.getProperty("mistakeiataOnlyEnglishXpath"));

        boolean result = mistakeiataOnlyEnglish.getText().equals("IATA code is English only") && mistakeEnglishNameEnglishOnly.getText().equals("Name is English only") && appendButtonElement.isDisplayed();
        assertThat(result, is(true));

        iataElement.clear();
        Thread.sleep(200);
        iataElement.sendKeys(iata);
        Thread.sleep(500);
        englishNameElement.clear();
        Thread.sleep(500);
        englishNameElement.sendKeys(englishName);
        Thread.sleep(500);
        appendButtonElement.click();
        Thread.sleep(500);

        String querya = "Select * from master_data.movements where iata = '" + iata + "'";
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
