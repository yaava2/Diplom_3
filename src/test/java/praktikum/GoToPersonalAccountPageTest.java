package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.*;
import static praktikum.pages.LoginPage.LOGIN_URL;

public class GoToPersonalAccountPageTest extends UserApi {
    private WebDriver driver;
    String accessToken;
    private praktikum.pages.User User;
    praktikum.pages.User user = User.random();

    @Before
    public void startWork (){
        DriverFactory driverFactory = new DriverFactory();
        if ("firefox".equals(System.getProperty("browser"))){
            driver = driverFactory.startFirefox();
        } else {
            driver = driverFactory.startChrome();
        }
        this.accessToken = createStellarUser(user);
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, user.getEmail(), user.getPassword());
        loginPage.typeEmailPasswordUser()
                .checkSuccessfulLogin();
    }

    @Test
    public void GoToPersonalAccountPageForMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton()
                .checkPersonalAccountPage();

    }

    @After
    public void tearDown() {
        if ( accessToken != null ) {
            deleteStellarUser(accessToken);
        }
        driver.quit();
    }

}
