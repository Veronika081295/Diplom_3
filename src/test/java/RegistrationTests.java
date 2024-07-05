import io.qameta.allure.junit4.DisplayName;
import praktikum.API.*;
import praktikum.Assertions;
import praktikum.Pages.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.WebDriverConfig;

import java.util.concurrent.TimeUnit;

public class RegistrationTests {
    private WebDriver driver;
    private String accessToken;

    User user;

    @Before
    public void setup() {
        user = UserUtils.generateCredentials();
        driver = WebDriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(WebDriverConfig.WAIT_SEC_TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(RestApiConstants.REGISTER_URL);
    }

    @Test
    @DisplayName("Should return error when registration with five symbols password")
    public void fiveSymbolsPasswordTest() throws InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        String password = user.getPassword().substring(0, Math.min(user.getPassword().length(), 5));
        registrationPage.setName(user.getName());
        registrationPage.setEmail(user.getEmail());
        registrationPage.setPassword(password);
        registrationPage.clickRegisterButton();
        Thread.sleep(3000);
        Assertions.assertInvalidPasswordText(driver, "Некорректный пароль");
    }

    @After
    public void cleanUp() {
        if (accessToken != null && user != null) {
            UserAPI.deleteUser(accessToken);
            driver.quit();
        }
    }
}
