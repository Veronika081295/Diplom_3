package praktikum.Pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import praktikum.API.User;

public class LoginPage {
    WebDriver driver;
    private final By headerLogin = By.xpath(".//h2[text()='Вход']");
    private final By inputEmail = By.xpath(".//label[text()='Email']/../input");
    private final By inputPassword = By.xpath(".//label[text()='Пароль']/../input");
    private final By btnLogin = By.xpath(".//button[text()='Войти']");
    private final By btnCreateOrder = By.xpath(".//button[text()='Оформить заказ']");
    private final By btnProfile = By.xpath(".//p[text()='Личный Кабинет']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("check if CreateOrder button is enabled")
    public boolean checkCreateOrderButtonEnabled() {
        return driver.findElement(btnCreateOrder).isEnabled();
    }
    @Step("get text from header")
    public String getHeaderText() {
        return driver.findElement(headerLogin).getText();
    }

    @Step("clear input and insert Email")
    public void clearInputAndInsertEmail(String email) {
        WebElement emailInput = driver.findElement(inputEmail);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    @Step("clear input and insert password")
    public void clearInputAndInsertPassword(String password) {
        WebElement passwordInput = driver.findElement(inputPassword);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    @Step("click on LogIn Button")
    public void clickLoginButton() {
        driver.findElement(btnLogin).click();
    }

    @Step("click on profile button")
    public void clickProfileButton() {
        driver.findElement(btnProfile).click();
    }

    @Step("loginUserSuccessfully")
    public void loginUser(User user) {
        clearInputAndInsertEmail(user.getEmail());
        clearInputAndInsertPassword(user.getPassword());
        clickLoginButton();
        Assert.assertTrue(checkCreateOrderButtonEnabled());
    }

}
