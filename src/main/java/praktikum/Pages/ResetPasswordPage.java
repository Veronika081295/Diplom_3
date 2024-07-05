package praktikum.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResetPasswordPage {
    WebDriver driver;
    private final By btnLoginUnderResetting = By.xpath("//a[@href='/login']");

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Click on LogIn button on Reset password page")
    public void clickLoginButtonUnderResetting() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(btnLoginUnderResetting));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(btnLoginUnderResetting));
        loginButton.click();
    }
}
