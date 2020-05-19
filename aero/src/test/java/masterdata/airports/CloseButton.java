package masterdata.airports;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
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
 * 001 Проверка закрытия окон без сохранения
 */
public class CloseButton {
    WebDriver driver;
    HashMap<String, String> propertiesMap;
    Random random = new Random();
    private static final Logger LOGGER = LogManager.getLogger(DataStartBiggerThenDataFinishInEdit.NewRecordCreationValid.class.getName());
//    private static masterdata.SQLStorage storage = masterdata.SQLStorage.getStorage();

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
        driver   = new ChromeDriver();

        iata = getRandomString(3);
        icao = getRandomString(4);
        englishName = getRandomString(10);
        localName = getRandomString(10);
    }
 // 001 Проверка закрытия окона Append без сохранения, проверяется только на фронте

//    @Test
//    public void newTest() throws IOException, InterruptedException {
//        driver.get(config.getProperty("url"));
//        TableElements tableElements = new TableElements(driver);
//        String text = tableElements
//                .clickNewButton(500)
//                .fillIATA(iata, 500)
//                .fillICAO(icao, 500)
//                .fillEnglishName(englishName,500)
//                .fillLocalName(localName,500)
//                .fillTimeZoneWinter("10",500)
//                .fillTimeZoneSummer("1",500)
//                .fillDateRangeStartElement("10.04.2020",500)
//                .fillDateRangeFinishElement("13.04.2020",1000)
//                .saveButton(1000)
//                .text(500);
//
//        assertThat(text.contains(englishName + ", " + localName), is(true));
//        assertThat(text.contains(iata), is(true));
//        assertThat(text.contains(icao), is(true));
//        String query = "Select * from master_data.masterdata.airports where iata = '"+iata+"'";
//        new masterdata.airports.DatabaseChecker(config, storage).existInDB(query, true);
//        driver.close();
//    }

//    @Test
//    public void openAirportsAppendWindowAndCheckPage() throws InterruptedException, IOException {
//        driver.get(config.getProperty("url"));
//
//       WebElement newButton = getButton(config.getProperty("newButtonXpath"));
//        newButton.click();
//        Thread.sleep(500);
//        WebElement icaoElement = getField(config.getProperty("icaoElementXpath"));
//        icaoElement.sendKeys(icao);
//        Thread.sleep(500);
//
//        WebElement englishNameElement = getField(config.getProperty("englishNameXpath"));
//        englishNameElement.sendKeys(englishName);
//        Thread.sleep(500);
//
//        WebElement localNameElement = getField(config.getProperty("localNameXpath"));
//        localNameElement.sendKeys(localName);
//        Thread.sleep(500);
//
//        WebElement timeZoneWinterElement = getField(config.getProperty("timeZoneWinterXpath"));
//        timeZoneWinterElement.sendKeys("11");
//        Thread.sleep(500);
//
//        WebElement timeZoneSummerElement = getField(config.getProperty("timeZoneSummerXpath"));
//        timeZoneSummerElement.sendKeys("11");
//        Thread.sleep(500);
//
//        WebElement dateRangeStartElement = getField(config.getProperty("dateRangeStartXpath"));
//        dateRangeStartElement.sendKeys("23.04.2022");
//        Thread.sleep(500);
//
//        WebElement dateRangeFinishElement = getField(config.getProperty("dateRangeFinishXpath"));
//        dateRangeFinishElement.sendKeys("28.04.2022");
//        Thread.sleep(500);

//        assertThat(text.contains(englishName + ", " + localName), is(false));
//        assertThat(text.contains(iata), is(false));
//        assertThat(text.contains(icao), is(false));
//
//        // Проверяем, что форма Append new очистилась
//
//        tableElements.clickNewButton(500);
//
//        HashMap<String, WebElement> map = tableElements.webElementMap;
//
//        assertThat(map.get("iata").getText().equals(""), is(true));
//        assertThat(map.get("icao").getText().equals(""), is(true));
//        assertThat(map.get("englishName").getText().equals(""), is(true));
//        assertThat(map.get("localName").getText().equals(""), is(true));
//        assertThat(map.get("timeZoneWinter").getText().equals(""), is(true));
//        assertThat(map.get("timeZoneSummer").getText().equals(""), is(true));
//        assertThat(map.get("dateRangeStart").getText().equals(""), is(true));
//        assertThat(map.get("dateRangeFinish").getText().equals(""), is(true));
//
//
//    }
//        String dbCallResult = null;
//        Connection connection = storage.getConnection();
//        String tableName = config.getProperty("tableName");
//        String query = "select name from " + tableName + " where (iata = ? and icao = ?)";
//        try (
//                PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, iata);
//            statement.setString(2, icao);
//
//
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                dbCallResult = resultSet.getString("name");
//            }
//            resultSet.close();
//        } catch (
//                SQLException e) {
//            LOGGER.error(e.getMessage(), e);
//            e.printStackTrace();
//        }
//        storage.closeConnection();
//        String expected = "{" + englishName + "," + localName + "}";
//        assertThat(dbCallResult, is(expected));
//    }

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
