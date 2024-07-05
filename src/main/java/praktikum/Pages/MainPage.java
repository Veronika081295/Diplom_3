package praktikum.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.API.RestApiConstants;

public class MainPage {
    WebDriver driver;

    private final By btnLogo = By.className("AppHeader_header__logo__2D0X2");
    private final By btnConstructor = By.xpath(".//p[text()='Конструктор']");
    private final By headerCreateBurger = By.xpath(".//h1[text()='Соберите бургер']");
    private final By btnLogInToProfile = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By btnBuns = By.xpath(".//span[text()='Булки']");
    private final By btnBunsIsCurrent = By.xpath(".//div[(contains(span/text(),'Булки')) and (contains(@class, 'tab_tab_type_current__2BEPc'))]");
    private final By btnSauces = By.xpath(".//span[text()='Соусы']");
    private final By btnSaucesIsCurrent = By.xpath(".//div[(contains(span/text(),'Соусы')) and (contains(@class, 'tab_tab_type_current__2BEPc'))]");
    private final By btnFillings = By.xpath(".//span[text()='Начинки']");
    private final By btnFillingsIsCurrent = By.xpath(".//div[(contains(span/text(),'Начинки')) and (contains(@class, 'tab_tab_type_current__2BEPc'))]");
    private final By btnProfile = By.xpath(".//p[text()='Личный Кабинет']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("click on Logo")
    public void clickLogoButton() {
        driver.findElement(btnLogo).click();
    }
    @Step("click on Constructor tab")
    public void clickConstructorTab() {
        driver.findElement(btnConstructor).click();
    }
    @Step("click on LogIn button")
    public void clickLoginButton() {
        driver.findElement(btnLogInToProfile).click();
    }
    @Step("check if LogIn button is visible")
    public void checkLogInButtonVisible() {
        driver.findElement(btnLogInToProfile).isEnabled();
    }
    @Step("click on buns tab")
    public void clickBunsTab() {
        driver.findElement(btnBuns).click();
    }
    @Step("click on sauces tab")
    public void clickSaucesTab() {
        driver.findElement(btnSauces).click();
    }
    @Step("click on fillings tab")
    public void clickFillingsTab() {
        driver.findElement(btnFillings).click();
    }
    @Step("get CreateBurger text from header")
    public String getCreateBurgerTextFromHeader() {
        return driver.findElement(headerCreateBurger).getText();
    }
    @Step("check if Buns tab is enabled")
    public boolean checkBunsEnabled() {
        return driver.findElement(btnBunsIsCurrent).isEnabled();
    }
    @Step("check if Sauces tab is enabled")
    public boolean checkSaucesEnabled() {
        return driver.findElement(btnSaucesIsCurrent).isEnabled();
    }
    @Step("check if Fillings tab is enabled")
    public boolean checkFillingsEnabled() {
        return driver.findElement(btnFillingsIsCurrent).isEnabled();
    }
    @Step("click on Profile button")
    public void clickProfileButton() {
        driver.findElement(btnProfile).click();
    }

    @Step("return to main page with logo or constructor")
    public void backToMainPage(String button) {
        if (button.equals(RestApiConstants.LOGO_PATH)) {
            clickLogoButton();
        } else {
            clickConstructorTab();
        }
    }
}
