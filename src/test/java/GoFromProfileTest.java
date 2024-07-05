import io.qameta.allure.Step;
import praktikum.API.RestApiConstants;
import praktikum.WebDriverConfig;
import praktikum.Assertions;
import praktikum.Pages.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class GoFromProfileTest {
    private WebDriver driver;
    private final String button;
    @Before
    @Step("setUp")
    public void setup() {
        driver = WebDriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(WebDriverConfig.WAIT_SEC_TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(RestApiConstants.BASE_URI);
    }

    public GoFromProfileTest(String button) {
        this.button = button;
    }

    @Parameterized.Parameters(name = "Go from profile using the {0} button")
    public static Object[] backToMainButtons() {
        return new Object[][]{
                {RestApiConstants.LOGO_PATH},
                {RestApiConstants.MAIN_PAGE_PATH},
        };
    }

    @Test
    public void goFromProfileToMain() {
        MainPage mainPage = new MainPage(driver);
        mainPage.checkLogInButtonVisible();
        mainPage.clickProfileButton();
        mainPage.backToMainPage(button);
        Assertions.assertCreateBurgerHeaderText(driver, "Соберите бургер");
    }

    @After
    @Step("cleanUp")
    public void cleanUp() {
        driver.quit();
    }
}
