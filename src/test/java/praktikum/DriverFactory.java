package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    public WebDriver driver;

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

    public void browserSelection(){
        DriverFactory driverFactory = new DriverFactory();
        if ("firefox".equals(System.getProperty("browser"))){
            driver = driverFactory.startFirefox();
        } else {
            driver = driverFactory.startChrome();
        }
    }
}
