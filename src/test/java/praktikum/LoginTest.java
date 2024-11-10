package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.*;

import static praktikum.EnvConfig.BASE_URL;
import static praktikum.pages.AuthorizationPage.AUTHORIZATION_URL;
import static praktikum.pages.PasswordRecoveryPage.PASSWORD_RECOVERY_URL;

public class LoginTest extends UserApi {
    private WebDriver driver;
    String accessToken;
    User user = User.random();

    @Before
    public void startWork (){
        DriverFactory driverFactory = new DriverFactory();
        driverFactory.browserSelection();
        driver = driverFactory.driver;

        this.accessToken = createStellarUser(user);
    }

    //авто через страницу регистрации
    @Test
    public void loginForAuthorizationPage() {
        driver.get(AUTHORIZATION_URL);
        AuthorizationPage authorizationPage = new AuthorizationPage(driver, user.getName(), user.getEmail(), user.getPassword());
        authorizationPage.clickLoginButton()
                        .checkOpenLoginPage();
        LoginPage loginPage = new LoginPage(driver, user.getEmail(), user.getPassword());
        loginPage.typeEmailPasswordUser()
                .checkSuccessfulLogin();
    }

    //авто через кнопку «Личный кабинет»
    @Test
    public void loginForButtonPersonalAccount() {
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginInAccountButton()
                .checkOpenLoginPage();
        LoginPage loginPage = new LoginPage(driver, user.getEmail(), user.getPassword());
        loginPage.typeEmailPasswordUser()
                .checkSuccessfulLogin();
    }

    //авто по кнопке «Войти в аккаунт» на главной
    @Test
    public void loginForButtonLoginInAccount() {
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginInAccountButton()
                .checkOpenLoginPage();
        LoginPage loginPage = new LoginPage(driver, user.getEmail(), user.getPassword());
        loginPage.typeEmailPasswordUser()
                .checkSuccessfulLogin();
    }

    //авто через кнопку в форме восстановления пароля
    @Test
    public void loginForPasswordRecoveryPage() {
        driver.get(PASSWORD_RECOVERY_URL);
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        passwordRecoveryPage.clickLoginButton()
                .checkOpenLoginPage();
        LoginPage loginPage = new LoginPage(driver, user.getEmail(), user.getPassword());
        loginPage.typeEmailPasswordUser()
                .checkSuccessfulLogin();
    }


    @After
    public void tearDown() {
        if ( accessToken != null ) {
            deleteStellarUser(accessToken);
        }
        driver.quit();
    }
}
