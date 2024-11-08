package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static praktikum.EnvConfig.BASE_URL;

public class DriverFactory {

    public ChromeDriver startChrome(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
//        driver.get(BASE_URL);
        return driver;
    }

    public FirefoxDriver startFirefox(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxDriver driver = new FirefoxDriver();
        //driver.get(BASE_URL);
        return driver;
    }
}
