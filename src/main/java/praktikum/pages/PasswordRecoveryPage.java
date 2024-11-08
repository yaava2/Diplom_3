package praktikum.pages;

import io.qameta.allure.Step;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static praktikum.EnvConfig.BASE_URL;

public class PasswordRecoveryPage {
    WebDriver driver;
    public static final String PASSWORD_RECOVERY_URL = BASE_URL +"/forgot-password";
    private static final By LOGIN_BUTTON= By.className("Auth_link__1fOlj");
    private static final  By LOGIN_PAGE= By.xpath(".//*[@id=\"root\"]/div/main/div/h2");

    public PasswordRecoveryPage( WebDriver driver) {
        this.driver = driver;
    }

    @Step("нажать Войти")
    public PasswordRecoveryPage clickLoginButton() {
//        new WebDriverWait(driver, Duration.ofSeconds(5));
//        driver.findElement(LOGIN_BUTTON).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(LOGIN_BUTTON)).perform();
        actions.click().perform();

        return this;
    }

    @Step("проверка открытия страницы Входа")
    public PasswordRecoveryPage checkOpenLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(LOGIN_PAGE, "Вход"));
        String checkSuccessfulRegistration = driver.findElement(LOGIN_PAGE).getText();
        MatcherAssert.assertThat(checkSuccessfulRegistration,containsString("Вход"));
        return this;
    }
}
