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

public class MainPage {
    WebDriver driver;
    private static final By LOGIN_IN_ACCOUNT_BUTTON = By.className("button_button_size_large__G21Vg");
    private static final  By LOGIN_PAGE= By.xpath(".//*[@id=\"root\"]/div/main/div/h2");
    private static final By PERSONAL_ACCOUNT_BUTTON = By.xpath(".//*[@id=\"root\"]/div/header/nav/a/p");
    private static final By PROFILE_BUTTON= By.className("Account_link_active__2opc9");

    private static final By BUNS_TAB = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");
    private static final By SAUCES_TAB = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
    private static final By INGREDIENTS_TAB = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");

    public MainPage(WebDriver driver){
        this.driver=driver;
    }

    @Step("нажать Войти в аккаунт")
    public MainPage clickLoginInAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(LOGIN_IN_ACCOUNT_BUTTON));
        driver.findElement(LOGIN_IN_ACCOUNT_BUTTON).click();
        return this;
    }

    @Step("нажать Личный кабинет")
    public MainPage clickPersonalAccountButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(PERSONAL_ACCOUNT_BUTTON));

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(PERSONAL_ACCOUNT_BUTTON)).perform();
        actions.click().perform();

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
        return this;
    }

    @Step("проверка открытия страницы Личного кабинета")
    public MainPage checkPersonalAccountPage() {
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(PROFILE_BUTTON, "Профиль"));
        String checkSuccessfulRegistration = driver.findElement(PROFILE_BUTTON).getText();
        MatcherAssert.assertThat(checkSuccessfulRegistration,containsString("Профиль"));
        return this;
    }

    @Step("проверка открытия страницы Входа")
    public MainPage checkOpenLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(LOGIN_PAGE, "Вход"));
        String checkSuccessfulRegistration = driver.findElement(LOGIN_PAGE).getText();
        MatcherAssert.assertThat(checkSuccessfulRegistration,containsString("Вход"));
        return this;
    }

    @Step("переход к разделу Булки")
    public MainPage openBunsTab() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(BUNS_TAB));

        driver.findElement(SAUCES_TAB).click();
        driver.findElement(BUNS_TAB).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(BUNS_TAB, "class", "current"));
        return this;
    }

    @Step("переход к разделу Соусы")
    public MainPage openSaucesTab() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(BUNS_TAB));

        driver.findElement(SAUCES_TAB).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(SAUCES_TAB, "class", "current"));
        return this;
    }

    @Step("переход к разделу Начинки")
    public MainPage openIngredientsTab() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(BUNS_TAB));

        driver.findElement(INGREDIENTS_TAB).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(INGREDIENTS_TAB, "class", "current"));
        return this;
    }
}
