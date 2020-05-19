package masterdata.delays;

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
 * 001 Проверка  создания новой записи в окне Delays
 */

public class NewRecordCreateDelays {
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
        InputStream in = DataStartBiggerThenDataFinishInEdit.NewRecordCreationValid.class.getClassLoader().getResourceAsStream("delaysTest.properties");
        config = new Properties();
        config.load(in);
        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver/chromedriver");
        driver = new ChromeDriver();
        iata = getRandomString(4);
        englishName = getRandomString(10);
        localName = getRandomString(10);
    }
// создание новой записи и очистка полей формы создания записи
    @Test
    public void newRecordCreation() throws InterruptedException {
        driver.get(config.getProperty("url"));

        WebElement delaysFolder = getButton(config.getProperty("delaysFolderXpath"));
        delaysFolder.click();
        Thread.sleep(500);

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

        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStartElement.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinishElement.sendKeys("28.04.2022");
        Thread.sleep(1000);

        WebElement appendButtonElement = getButton(config.getProperty("appendButtonXpath"));
        appendButtonElement.click();
        Thread.sleep(2000);

        WebElement text = getField(config.getProperty("forSearchInMainTableXpath"));

        assertThat(text.getText().contains(iata), is(true));

        String query = "Select * from master_data.masterdata.delays where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(query, true);
        newButton.click();
        Thread.sleep(500);

        assertThat(iataElement.getText().equals(""), is(true));
        assertThat(englishNameElement.getText().equals(""), is(true));
        assertThat(localNameElement.getText().equals(""), is(true));
        assertThat(dateRangeStartElement.getText().equals(""), is(true));
        assertThat(dateRangeFinishElement.getText().equals(""), is(true));

        driver.close();
    }
    // Выход без сохранения при создании новой записи и очистка полей формы создания записи
    @Test
    public void newRecordCloseWithoutsaving() throws InterruptedException {
        driver.get(config.getProperty("url"));

        WebElement delaysFolder = getButton(config.getProperty("delaysFolderXpath"));
        delaysFolder.click();
        Thread.sleep(500);

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

        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStartElement.sendKeys("23.04.2022");
        Thread.sleep(500);

        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinishElement.sendKeys("28.04.2022");
        Thread.sleep(1000);

        WebElement closeButton = getButton(config.getProperty("closeButtonXpath"));
        closeButton.click();
        Thread.sleep(2000);

        WebElement text = getField(config.getProperty("forSearchInMainTableXpath"));

        assertThat(text.getText().contains(iata), is(false));

        String query = "Select * from master_data.masterdata.delays where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(query, false);

        newButton.click();
        Thread.sleep(500);

        assertThat(iataElement.getText().equals(""), is(true));
        assertThat(englishNameElement.getText().equals(""), is(true));
        assertThat(localNameElement.getText().equals(""), is(true));
        assertThat(dateRangeStartElement.getText().equals(""), is(true));
        assertThat(dateRangeFinishElement.getText().equals(""), is(true));
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


