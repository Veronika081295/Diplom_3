import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.API.*;
import praktikum.Pages.*;
import praktikum.WebDriverConfig;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;

public class LoginTests {
    private WebDriver driver;
    User user;

    @Before
    @Step("setUp")
    public void setup() {
        RestAssured.baseURI = RestApiConstants.BASE_URI;
        user = UserUtils.generateCredentials();
        System.out.println(user);
        UserAPI.createApiUser(user);
        driver = WebDriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(WebDriverConfig.WAIT_SEC_TIMEOUT, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Log in with Log in button on main page")
    public void logInWithMainButtonTest() {
        driver.navigate().to(RestApiConstants.BASE_URI);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.clickLoginButton();
        loginPage.loginUser(user);
        loginPage.clickProfileButton();
        Assert.assertTrue(profilePage.btnProfileTabIsEnabled());
    }

    @Test
    @DisplayName("Log in with account button")
    public void logInWithAccountButtonTest() {
        driver.navigate().to(RestApiConstants.BASE_URI);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.clickProfileButton();
        loginPage.loginUser(user);
        loginPage.clickProfileButton();
        Assert.assertTrue(profilePage.btnProfileTabIsEnabled());
    }

    @Test
    @DisplayName("Login from the registration page")
    public void logInFromRegistrationPageTest() {
        System.out.println("Starting login from the register page test...");
        driver.navigate().to(RestApiConstants.REGISTER_URL);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        registrationPage.clickLogInButtonUnderRegistration();
        loginPage.loginUser(user);
        loginPage.clickProfileButton();
        System.out.println("Login successful from the register page.");
        Assert.assertTrue(profilePage.btnProfileTabIsEnabled());
    }

    @Test
    @DisplayName("Log in from reset password page")
    public void logInFromResetPasswordPageTest() {
        driver.navigate().to(RestApiConstants.RESET_PASSWORD_URL);
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        resetPasswordPage.clickLoginButtonUnderResetting();
        loginPage.loginUser(user);
        loginPage.clickProfileButton();
        Assert.assertTrue(profilePage.btnProfileTabIsEnabled());
    }

    @Test
    @DisplayName("Log out from account")
    public void logoutTest() {
        driver.navigate().to(RestApiConstants.BASE_URI);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.clickProfileButton();
        loginPage.loginUser(user);
        loginPage.clickProfileButton();
        profilePage.clickExitButton();
        MatcherAssert.assertThat(loginPage.getHeaderText(), equalTo("Вход"));
    }

    @After
    @Step("cleanUp")
    public void cleanUp() {
        String accessToken = UserAPI.getAccessToken(user);
        if (accessToken != null) {
            UserAPI.deleteUser(accessToken);
        }
        driver.quit();
    }
}
