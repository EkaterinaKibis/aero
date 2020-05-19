package masterdata.airports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DocumTest {

    public WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    public void openmainpage() {
      System.setProperty("webdriver.chrome.driver", "/opt/chromedriver/chromedriver");
                driver.get("http://dev.msural.ru");
}
    public void openMasterData (){
        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver/chromedriver");
        driver.get("http://dev.msural.ru/referance");

    }
    public void openAirlines(){
        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver/chromedriver");
        driver.get("http://dev.msural.ru/referance");
        WebElement airlinesFolder = driver.findElement(By.xpath("//*[@id=\"tab-airlines\"]"));
        airlinesFolder.click();
    }
    public void openAircrafts(){
        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver/chromedriver");
        driver.get("http://dev.msural.ru/referance");
        WebElement aircraftsFolder = driver.findElement(By.xpath("//*[@id=\"tab-aircrafts\"]"));
        aircraftsFolder.click();
    }
}
