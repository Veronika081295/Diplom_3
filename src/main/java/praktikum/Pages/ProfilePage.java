package praktikum.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    WebDriver driver;

    private final By btnProfileTab = By.xpath(".//a[text()='Профиль']");
    private final By btnExitTab = By.xpath(".//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("check if Profile tab is enabled")
    public boolean btnProfileTabIsEnabled() {
        return driver.findElement(btnProfileTab).isEnabled();
    }
    @Step("click on Exit button")
    public void clickExitButton() {
        driver.findElement(btnExitTab).click();
    }
}
