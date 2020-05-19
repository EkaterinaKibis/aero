package oop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test {
    public String autorisation (String password){
        System.setProperty("webdriver.chrome.driver", "/home/ilya/Загрузки/chromedriver_linux64/chromedriver");;
        WebDriver driver = new ChromeDriver();
        String baseUrl = "https://accounts.google.com/signin/v2/sl/pwd?continue=https%3A%2F%2Faccounts.google.com%2F&followup=https%3A%2F%2Faccounts.google.com%2F&flowName=GlifWebSignIn&flowEntry=AddSession&cid=0&navigationDirection=forward";
        driver.get(baseUrl);
        WebElement passw = driver.findElement(By.name("password"));
        passw.sendKeys(password);
        WebElement submit = driver.findElement(By.id("passwordNext"));
        submit.click();
        String url = driver.getCurrentUrl();
        return url;
    }
}
