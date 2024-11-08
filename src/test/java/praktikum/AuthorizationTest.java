package praktikum;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v128.network.Network;
import praktikum.pages.AuthorizationPage;
import praktikum.pages.UserApi;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.assertEquals;
import static praktikum.pages.AuthorizationPage.AUTHORIZATION_URL;
import static praktikum.pages.LoginPage.LOGIN_URL;

public class AuthorizationTest extends UserApi {
    private WebDriver driver;
    String accessToken;

    @Before
    public void startWork (){
        DriverFactory driverFactory = new DriverFactory();
        if ("firefox".equals(System.getProperty("browser"))){
            driver = driverFactory.startFirefox();
        } else {
            driver = driverFactory.startChrome();
        }
        driver.get(AUTHORIZATION_URL);
    }

    @Test
    public void checkAuthorization() throws InterruptedException  {
        String textName = RandomStringUtils.randomAlphanumeric(6,10);
        String textEmail = RandomStringUtils.randomAlphanumeric(6,10)+"@1mail.ru";
        String textPassword = RandomStringUtils.randomAlphanumeric(6,10);

        AuthorizationPage authorizationPage = new AuthorizationPage(driver, textName, textEmail, textPassword);
        authorizationPage.typeNameEmailPassword()
                .clickRegisterButton()
                .checkOpenLoginPage();

        driver.findElement(By.cssSelector("input[name='name']")).sendKeys(textEmail);
        driver.findElement(By.cssSelector("input[name='Пароль']")).sendKeys(textPassword);
        driver.findElement(By.cssSelector("form button")).click();

        accessToken = authorizationPage.getAccessToken();
    }

    @Test
    public void cannotAuthorizationPasswordLess6Characters() {
        String textName = RandomStringUtils.randomAlphanumeric(6,10);
        String textEmail = RandomStringUtils.randomAlphanumeric(6,10)+"@1mail.ru";
        String textPassword = RandomStringUtils.randomAlphanumeric(1,5);

        AuthorizationPage authorizationPage = new AuthorizationPage(driver, textName, textEmail, textPassword);
        authorizationPage.typeNameEmailPassword()
                .clickRegisterButton();

        authorizationPage.checkPasswordErrorLess6Characters();
    }

    @After
    public void tearDown() {
        if ( accessToken != null ) {
            deleteStellarUser(accessToken);
        }
        driver.quit();
    }
}
