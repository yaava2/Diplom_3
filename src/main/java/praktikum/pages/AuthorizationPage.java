package praktikum.pages;

import io.qameta.allure.Step;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.hamcrest.CoreMatchers.containsString;
import static praktikum.EnvConfig.BASE_URL;

public class AuthorizationPage {
    WebDriver driver;
    public static final String AUTHORIZATION_URL = BASE_URL +"/register";

    private String textName;
    private String textEmail;
    private String textPassword;
    private static final By NAME_FIELD = By.xpath(".//*[@id=\'root\']/div/main/div/form/fieldset[1]/div/div/input");
    private static final By EMAIL_FIELD = By.xpath(".//*[@id=\'root\']/div/main/div/form/fieldset[2]/div/div/input");
    private static final By PASSWORD_FIELD = By.xpath(".//*[@id=\'root\']/div/main/div/form/fieldset[3]/div/div/input");
    private static final By REGISTER_BUTTON = By.xpath( ".//*[@id=\"root\"]/div/main/div/form/button");
    private static final  By LOGIN_PAGE= By.xpath(".//*[@id=\"root\"]/div/main/div/h2");
    private static final  By LOGIN_BUTTON= By.className("Auth_link__1fOlj");

    private static final By TEXT_PASSWORD_ERROR = By.className("input__error");

    public AuthorizationPage(WebDriver driver, String textName, String textEmail, String textPassword) {
        this.textName = textName;
        this.textEmail = textEmail;
        this.textPassword = textPassword;
        this.driver = driver;
    }

    @Step("заполнить поля: имя, почта, пароль")
    public AuthorizationPage typeNameEmailPassword() {
        driver.findElement(NAME_FIELD).sendKeys(textName);
        driver.findElement(EMAIL_FIELD).sendKeys(textEmail);
        driver.findElement(PASSWORD_FIELD).sendKeys(textPassword);
        return this;
    }

    @Step("нажать Зарегистрироваться")
    public AuthorizationPage clickRegisterButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(REGISTER_BUTTON).click();
        return this;
    }

    @Step("проверка открытия страницы входа")
    public AuthorizationPage checkOpenLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(LOGIN_PAGE, "Вход"));
        String checkSuccessfulRegistration = driver.findElement(LOGIN_PAGE).getText();
        MatcherAssert.assertThat(checkSuccessfulRegistration,containsString("Вход"));
        return this;
    }

    @Step("проверка появления ошибки некорректности пароля- пароль меньше 6 символов")
    public AuthorizationPage checkPasswordErrorLess6Characters(){
        String PasswordErrorLess6Characters = driver.findElement(TEXT_PASSWORD_ERROR).getText();
        MatcherAssert.assertThat(PasswordErrorLess6Characters,containsString("Некорректный пароль"));
        return this;
    }

    @Step("нажать Войти")
    public AuthorizationPage clickLoginButton() {
        //new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(LOGIN_BUTTON).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(LOGIN_BUTTON));
        return this;
    }

    public String getAccessToken(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector("a[class^='BurgerIngredient']"), 2));

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        return localStorage.getItem("accessToken");
    }

}
