package anotherPageObject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class AuthPage {
    private static final By LOGIN_FIELD = By.xpath(".//input[@id='login-form-username']");
    private static final By PASSWORD_FIELD = By.xpath(".//input[@id='login-form-password']");
    private static final By LOGIN_BUTTON = By.xpath(".//input[@id='login']");

    @Step("Открываем страницу авторизации по ссылке")
    public AuthPage openUrl(String url) {
        open(url);
        return this;
    }

    @Step("Авторизуемся в системе")
    public MainPage doAuthorization(String login, String password) {
        $(LOGIN_FIELD).shouldBe(visible.because("Нет поля ввода логина")).sendKeys(login);
        $(PASSWORD_FIELD).shouldBe(visible.because("Нет поля ввода пароля")).sendKeys(password);
        $(LOGIN_BUTTON).shouldBe(enabled.because("Нет кнопки Войти")).click();
        return new MainPage();
    }
}
