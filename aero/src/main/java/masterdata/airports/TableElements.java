package masterdata.airports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class TableElements {
    WebDriver driver;
    InputStream in = TableElements.class.getClassLoader().getResourceAsStream("masterdataairports.properties");
    Properties config = new Properties();
    public WebElement newButton;
    public WebElement iataElement;
    public WebElement icaoElement;
    public WebElement englishNameElement;
    public WebElement localNameElement;
    public WebElement timeZoneWinterElement;
    public WebElement timeZoneSummerElement;
    public WebElement dateRangeStartElement;
    public WebElement dateRangeFinishElement;
    public WebElement saveButton;
    public WebElement closeButton;
    public WebElement text;
    public WebElement iataMistakeMondatory;

    public TableElements(WebDriver driver) throws IOException {
        config.load(in);
        this.driver = driver;
        newButton = getButton(config.getProperty("newButtonXpath"));
        iataElement = getField(config.getProperty("iataElementXpath"));
        icaoElement = getField(config.getProperty("icaoElementXpath"));
        englishNameElement = getField(config.getProperty("englishNameXpath"));
        localNameElement = getField(config.getProperty("localNameXpath"));
        timeZoneWinterElement = getField(config.getProperty("timeZoneWinterXpath"));
        timeZoneSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
        dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
        dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
        saveButton = getButton(config.getProperty("saveButtonXpath"));
        closeButton = getButton(config.getProperty("closebuttonXpath"));
        text = getField(config.getProperty("forSearchInMainTableXpath"));
        iataMistakeMondatory = getField(config.getProperty("iataemtyfieldMistakeXpath"));

    }

    public TableElements openPage() {
        driver.get(config.getProperty("url"));
        return this;
    }
//    }
//
//    public TableElements clickNewButton(long delay) throws InterruptedException {
//        WebElement newButton = getButton(config.getProperty("newButtonXpath"));
//        newButton.click();
//        Thread.sleep(delay);
//        return this;
//    }
//
//    public TableElements fillIATA(String value, long delay) throws InterruptedException {
//        WebElement iataElement = getField(config.getProperty("iataElementXpath"));
//        webElementMap.put("iata", iataElement);
//        iataElement.sendKeys(value);
//        Thread.sleep(delay);
//        return this;
//    }
//
//    public TableElements fillICAO(String value, long delay) throws InterruptedException {
//        WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
//        webElementMap.put("icao", icaoElement);
//        icaoElement.sendKeys(value);
//        Thread.sleep(delay);
//        return this;
//    }
//
//    public TableElements fillEnglishName(String value, long delay) throws InterruptedException {
//        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
//        webElementMap.put("englishName", englishNameElement);
//        englishNameElement.sendKeys(value);
//        Thread.sleep(delay);
//        return this;
//    }
//
//    public TableElements fillLocalName(String value, long delay) throws InterruptedException {
//        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
//        webElementMap.put("localName", localNameElement);
//        localNameElement.sendKeys(value);
//        Thread.sleep(delay);
//        return this;
//    }
//
//    public TableElements fillTimeZoneWinter(String value, long delay) throws InterruptedException {
//        WebElement timeZoneWinterElement = getField(config.getProperty("timeZoneWinterXpath"));
//        webElementMap.put("timeZoneWinter", timeZoneWinterElement);
//        timeZoneWinterElement.sendKeys(value);
//        Thread.sleep(delay);
//        return this;
//    }
//
//
//    public TableElements fillTimeZoneSummer(String value, long delay) throws InterruptedException {
//        WebElement timeZoneSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
//        webElementMap.put("timeZoneSummer", timeZoneSummerElement);
//        timeZoneSummerElement.sendKeys(value);
//        Thread.sleep(delay);
//        return this;
//    }
//
//    public TableElements fillDateRangeStartElement(String value, long delay) throws InterruptedException {
//        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
//        webElementMap.put("dateRangeStart", dateRangeStartElement);
//        dateRangeStartElement.sendKeys(value);
//        Thread.sleep(delay);
//        return this;
//    }
//
//
//    public TableElements fillDateRangeFinishElement(String value, long delay) throws InterruptedException {
//        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
//        webElementMap.put("dateRangeFinish", dateRangeFinishElement);
//        dateRangeFinishElement.sendKeys(value);
//        Thread.sleep(delay);
//        return this;
//    }
////
//    public TableElements closeButton( long delay) throws InterruptedException {
//        WebElement closeButton = getButton(config.getProperty("closebuttonXpath"));
//        closeButton.click();
//        Thread.sleep(delay);
//        return this;
//    }
////
//    public TableElements saveButton(long delay) throws InterruptedException {
//        WebElement saveButton = getButton(config.getProperty("saveButtonXpath"));
//        saveButton.click();
//        Thread.sleep(delay);
//        return this;
//    }
//    public TableElements saveButtonIsDisplayed(long delay) throws InterruptedException {
//        WebElement saveButton = getButton(config.getProperty("saveButtonXpath"));
//        saveButton.isDisplayed();
//        Thread.sleep(delay);
//        return this;
//    }
//
//    public TableElements appendButton() throws InterruptedException {
//        WebElement appendButton = getButton(config.getProperty("appendButtonXpath"));
//        appendButton.click();
//        return this;
//    }
//    public String text( long delay) throws InterruptedException {
//        WebElement text = getField(config.getProperty("forSearchInMainTableXpath"));
//        Thread.sleep(delay);
//       return text.getText();
//    }
//    // Mistakes
//    public TableElements iataMistakeMondatory() throws InterruptedException {
//        WebElement iataMistakeMondatory = getField(config.getProperty("iataemtyfieldMistakeXpath"));
//        webElementMap.put("iataMistakeMondatory", iataMistakeMondatory);
//        return this;
//    }


    public WebElement getButton(String xPath) {
        return driver.findElement(By.xpath(xPath));
    }

    public WebElement getField(String xPath) {
        return driver.findElement(By.xpath(xPath));
    }

}
