package praktikum.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    WebDriver driver;

    private final By inputName = By.xpath(".//label[text()='Имя']/../input");
    private final By inputEmail = By.xpath(".//label[text()='Email']/../input");
    private final By inputPassword = By.xpath(".//label[text()='Пароль']/../input");
    private final By btnRegister = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By textInvalidPassword = By.xpath(".//p[text()='Некорректный пароль']");
    private final By btnLoginUnderReg = By.className("Auth_link__1fOlj");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("insert user name")
    public void setName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }
    @Step("insert user email")
    public void setEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }
    @Step("insert user password")
    public void setPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }
    @Step("click on registration button")
    public void clickRegisterButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(btnRegister));
        driver.findElement(btnRegister).click();
    }
    @Step("get InvalidPassword error message")
    public String getInvalidPasswordText() {
        return driver.findElement(textInvalidPassword).getText();
    }

    @Step("click on LogIn button under registration form")
    public void clickLogInButtonUnderRegistration() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(btnLoginUnderReg));
        driver.findElement(btnLoginUnderReg).click();
    }
}
