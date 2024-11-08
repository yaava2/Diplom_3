package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.pages.LoginPage;

import java.time.Duration;

import static praktikum.EnvConfig.BASE_URL;
import static praktikum.pages.LoginPage.LOGIN_URL;

public class SwitchTabsInMainPageTest {
    private WebDriver driver;

    @Before
    public void startWork (){
        DriverFactory driverFactory = new DriverFactory();
        if ("firefox".equals(System.getProperty("browser"))){
            driver = driverFactory.startFirefox();
        } else {
            driver = driverFactory.startChrome();
        }
        driver.get(BASE_URL);
    }

    @Test
    public void switchTabs() {
        var bunsTab = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");
        var saucesTab = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
        var ingredientTab = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsTab));

        driver.findElement(saucesTab).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(saucesTab, "class", "current"));

        driver.findElement(ingredientTab).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(ingredientTab, "class", "current"));

        driver.findElement(bunsTab).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(bunsTab, "class", "current"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
