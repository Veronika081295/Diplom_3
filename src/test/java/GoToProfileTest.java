import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import praktikum.API.*;
import praktikum.Assertions;
import praktikum.Pages.LoginPage;
import praktikum.Pages.MainPage;
import praktikum.Pages.ProfilePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.WebDriverConfig;

import java.util.concurrent.TimeUnit;

public class GoToProfileTest {
    private WebDriver driver;
    User user;

    @Before
    @Step("setUp")
    public void setup() {
        RestAssured.baseURI = RestApiConstants.BASE_URI;
        user = UserUtils.generateCredentials();
        UserAPI.createApiUser(user);
        driver = WebDriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(WebDriverConfig.WAIT_SEC_TIMEOUT, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Go to the profile as an authorized user")
    public void authUserGetProfileTest() {
        driver.navigate().to(RestApiConstants.BASE_URI);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.clickLoginButton();
        loginPage.loginUser(user);
        loginPage.clickProfileButton();
        profilePage.btnProfileTabIsEnabled();
    }

    @Test
    @DisplayName("Go to the profile as an unauthorized user")
    public void unauthorizedUserGetProfile() {
        driver.navigate().to(RestApiConstants.BASE_URI);
        MainPage mainPage = new MainPage(driver);
        mainPage.checkLogInButtonVisible();
        mainPage.clickProfileButton();
        Assertions.assertHeaderText(driver, "Вход");    }

    @After
    @Step("cleanUp")
    public void cleanUp() {
        UserAPI.deleteUser(UserAPI.getAccessToken(user));
        driver.quit();
    }
}
