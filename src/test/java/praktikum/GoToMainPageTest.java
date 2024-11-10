package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.*;

import static praktikum.pages.LoginPage.LOGIN_URL;

public class GoToMainPageTest extends UserApi {
    private WebDriver driver;
    String accessToken;
    praktikum.pages.User user = User.random();

    @Before
    public void startWork (){
        DriverFactory driverFactory = new DriverFactory();
        driverFactory.browserSelection();
        driver = driverFactory.driver;

        this.accessToken = createStellarUser(user);
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, user.getEmail(), user.getPassword());
        loginPage.typeEmailPasswordUser()
                .checkSuccessfulLogin();
    }

    @Test
    public void GoToConstructorPageForPersonalAccount() {
        MainPage mainPage=new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickConstructorButton()
                .checkGoToConstructorPage();

    }

    @Test
    public void GoToConstructorPageWithLogoForPersonalAccount() {
        MainPage mainPage=new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickLogoButton()
                .checkGoToConstructorPage();
    }

    @After
    public void tearDown() {
        if ( accessToken != null ) {
            deleteStellarUser(accessToken);
        }
        driver.quit();
    }

}
