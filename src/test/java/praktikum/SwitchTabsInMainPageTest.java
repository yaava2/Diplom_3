package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;
import static praktikum.EnvConfig.BASE_URL;

public class SwitchTabsInMainPageTest {
    private WebDriver driver;

    @Before
    public void startWork (){
        DriverFactory driverFactory = new DriverFactory();
        driverFactory.browserSelection();
        driver = driverFactory.driver;

        driver.get(BASE_URL);
    }

    @Test
    public void switchIngredientsTab() {
        MainPage mainPage= new MainPage(driver);
        mainPage.openIngredientsTab();

    }

    @Test
    public void switchBunsTab() {
        MainPage mainPage= new MainPage(driver);
        mainPage.openBunsTab();
    }

    @Test
    public void switchSaucesTab() {
        MainPage mainPage= new MainPage(driver);
        mainPage.openSaucesTab();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
