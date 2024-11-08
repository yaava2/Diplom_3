package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.PersonalAccountPage;
import praktikum.pages.UserApi;

import static praktikum.pages.LoginPage.LOGIN_URL;

public class LogoutTest extends UserApi {
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
    public void Logout(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton()
                .checkPersonalAccountPage();

        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickLogout()
                .checkLogout();
    }


    @After
    public void tearDown() {
        if ( accessToken != null ) {
            deleteStellarUser(accessToken);
        }
        driver.quit();
    }
}
