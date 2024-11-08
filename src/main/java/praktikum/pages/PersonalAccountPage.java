package praktikum.pages;

import io.qameta.allure.Step;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;

public class PersonalAccountPage {
    WebDriver driver;
    private static final By CONSTRUCTOR_BUTTON = By.xpath(".//*[@id=\"root\"]/div/header/nav/ul/li[1]/a/p");
    private static final By LOGO= By.className("AppHeader_header__logo__2D0X2");
    private static final By PLACE_AN_ORDER= By.className("button_button_type_primary__1O7Bx");
    private static final By LOGOUT_BUTTON= By.className("Account_button__14Yp3");
    private static final By LOGIN_PAGE= By.xpath("//*[@id=\"root\"]/div/main/div/h2");

    public PersonalAccountPage(WebDriver driver){
        this.driver=driver;
    }

    @Step("нажать на кнопку Конструктор")
    public PersonalAccountPage clickConstructorButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(CONSTRUCTOR_BUTTON));

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(CONSTRUCTOR_BUTTON)).perform();
        actions.click().perform();

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }

        return this;
    }

    @Step("нажать на Логотип")
    public PersonalAccountPage clickLogoButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(LOGO));

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(LOGO)).perform();
        actions.click().perform();

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }

        return this;
    }

    @Step("проверка открытия страницы Конструктора")
    public PersonalAccountPage checkGoToConstructorPage(){
        String checkSuccessfulLogin = driver.findElement(PLACE_AN_ORDER).getText();
        MatcherAssert.assertThat(checkSuccessfulLogin,containsString("Оформить заказ"));
        return this;
    }

    @Step("нажать Выйти")
    public PersonalAccountPage clickLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_BUTTON));

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(LOGOUT_BUTTON)).perform();
        actions.click().perform();

        return this;
    }

    @Step("проверка выхода из аккаунта")
    public PersonalAccountPage checkLogout() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(LOGIN_PAGE, "Вход"));
        String checkSuccessfulLogout = driver.findElement(LOGIN_PAGE).getText();
        MatcherAssert.assertThat(checkSuccessfulLogout,containsString("Вход"));
        return this;
    }


}
