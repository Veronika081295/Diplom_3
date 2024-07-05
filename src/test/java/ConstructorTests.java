import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import praktikum.API.RestApiConstants;
import praktikum.WebDriverConfig;
import praktikum.Pages.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public class ConstructorTests {
    private WebDriver driver;

    @Before
    @Step("setUp")
    public void setup() {
        driver = WebDriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(WebDriverConfig.WAIT_SEC_TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(RestApiConstants.BASE_URI);
    }

    @Test
    @DisplayName("Switch from Buns tab and go back to Buns tab")
    public void switchBunTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        Assert.assertTrue(mainPage.checkBunsEnabled());
    }

    @Test
    @DisplayName("Click on sauces tab")
    public void saucesTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();
        Assert.assertTrue(mainPage.checkSaucesEnabled());
    }

    @Test
    @DisplayName("Click on fillings tab")
    public void fillingTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab();
        Assert.assertTrue(mainPage.checkFillingsEnabled());
    }

    @After
    @Step("cleanUp")
    public void cleanUp() {
        driver.quit();
    }
}
