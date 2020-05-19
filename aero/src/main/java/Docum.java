import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Docum {

    public WebDriver driver = new ChromeDriver();

   public WebElement newButton = driver.findElement(By.xpath("/html/body/app-root/div/app-referance-module/div/div[2]/div[1]/div/div[1]/div[1]/button"));
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
