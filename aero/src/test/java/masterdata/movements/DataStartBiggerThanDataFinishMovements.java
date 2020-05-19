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
 * 004 Проверка того что запись не сохранится при условии Data range start > Data range Finish. Выдается сообщение об ошибке
 */

public class DataStartBiggerThanDataFinishMovements {
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

    // Проверка для окна редактирования
    @Test
    public void checkingInExistig() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement movementsFolder = getButton(config.getProperty("movementsFolderXpath"));
        movementsFolder.click();
        Thread.sleep(500);

        WebElement firstRow = getButton(config.getProperty("firstRowXpath"));
        firstRow.click();
        Thread.sleep(500);

        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStartElement.sendKeys("29.04.2024");
        Thread.sleep(500);

        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinishElement.sendKeys("28.04.2024");
        Thread.sleep(1000);
        WebElement appendButtonElement = getButton(config.getProperty("appendButtonXpath"));
        appendButtonElement.click();
        Thread.sleep(2000);

        Thread.sleep(2000);
        WebElement dateRangeMistake = getField(config.getProperty("DateRangeMistakeXpath"));
        WebElement editButtonInDateRangeMistakeWindow = getButton(config.getProperty("EditButtonInDateRangeMistakeWindow"));
        boolean result = editButtonInDateRangeMistakeWindow.isDisplayed() && dateRangeMistake.isDisplayed() && dateRangeMistake.getText().equals("Date range start cannot be greater than Date range finish");
        assertThat(result, is(true));
        driver.close();
    }


    // Проверяем, что при условии, что Date Range Start > Date Range Finish и нажатии кнопки Close,without Saving запись не запишется в базу и на фронт
    @Test
    public void checingCloseWithoutSaving() throws InterruptedException {
        driver.get(config.getProperty("url"));

        WebElement movementsFolder = getButton(config.getProperty("movementsFolderXpath"));
        movementsFolder.click();
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


        WebElement dateRangeStart = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStart.sendKeys("30.04.2022");
        Thread.sleep(1000);

        WebElement dateRangeFinish = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinish.sendKeys("29.04.2022");
        Thread.sleep(1000);


        WebElement saveButton = getButton(config.getProperty("appendButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);

        WebElement CloseWithoutSavingButtonInDateRangeMistakeWindow = getButton(config.getProperty("CloseWithoutSavingButtonInDateRangeMistakeWindowXpath"));
        CloseWithoutSavingButtonInDateRangeMistakeWindow.click();

        WebElement text = getField(config.getProperty("forSearchInMainTableXpath"));

        Thread.sleep(500);
        String pageText = text.getText();

        assertThat(pageText.contains(iata), is(false));
        String query = "Select * from master_data.delays where iata = '" + iata + "'";
        new DatabaseChecker(config, storage).existInDB(query, false);

        driver.close();
    }

    // Проверяем, что при условии, что Date Range Start > Date Range Finish и нажатии кнопки Edit откроется окно редактирования, и выставив даты верно запись создастся
    @Test
    public void checingEditAndCreate() throws InterruptedException {
        driver.get(config.getProperty("url"));
        WebElement movementsFolder = getButton(config.getProperty("movementsFolderXpath"));
        movementsFolder.click();
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

        WebElement dateRangeStart = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeStart.sendKeys("30.04.2022");
        Thread.sleep(1000);

        WebElement dateRangeFinish = getField(config.getProperty("dateRangeFinishXpath"));
        dateRangeFinish.sendKeys("29.04.2022");
        Thread.sleep(1000);


        WebElement saveButton = getButton(config.getProperty("appendButtonXpath"));
        saveButton.click();
        Thread.sleep(2000);

        WebElement EditButtonInDateRangeMistakeWindow = getButton(config.getProperty("EditButtonInDateRangeMistakeWindow"));
        EditButtonInDateRangeMistakeWindow.click();
        Thread.sleep(2000);
        dateRangeStart.sendKeys("19.04.2022");
        Thread.sleep(500);

        saveButton.click();

        Thread.sleep(1000);

        WebElement text = getField(config.getProperty("forSearchInMainTableXpath"));
        WebElement finderIata = getField(config.getProperty("finderIataXpath"));

        Thread.sleep(500);

        finderIata.sendKeys(iata);
        Thread.sleep(1000);

        assertThat(text.getText().contains(iata), is(true));

        String query = "Select * from master_data.movements where iata = '" + iata + "'";
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