package praktikum.pages;

import io.qameta.allure.Step;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static praktikum.EnvConfig.BASE_URL;

public class LoginPage {
    public static final String LOGIN_URL = BASE_URL +"/login";
    WebDriver driver;
    private static final By PERSONAL_ACCOUNT= By.xpath(".//*[@id='root']/div/header/nav/a/p");
    private static final By LOGIN_TO_YOUR_ACCOUNT= By.xpath("//*[@id='root']/div/main/section[2]/div/button");
    private static final By PLACE_AN_ORDER= By.className("button_button_type_primary__1O7Bx");
    private String textEmail;
    private String textPassword;

    public LoginPage( WebDriver driver, String textEmail, String textPassword) {
        this.driver = driver;
        this.textEmail = textEmail;
        this.textPassword = textPassword;
    }

    @Step("заполнить поля:почта, пароль")
    public LoginPage typeEmailPasswordUser(){

        driver.findElement(By.cssSelector("input[name='name']")).sendKeys(textEmail);
        driver.findElement(By.cssSelector("input[name='Пароль']")).sendKeys(textPassword);
        new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("form button")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(PLACE_AN_ORDER, "Оформить заказ"));
        return this;
    }

    @Step("проверка успешного входа - открытие страницы конструктора")
    public LoginPage checkSuccessfulLogin(){
        String checkSuccessfulLogin = driver.findElement(PLACE_AN_ORDER).getText();
        MatcherAssert.assertThat(checkSuccessfulLogin,containsString("Оформить заказ"));
        return this;
    }

}
