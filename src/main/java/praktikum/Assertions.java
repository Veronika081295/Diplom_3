package praktikum;

import io.qameta.allure.Step;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import praktikum.Pages.LoginPage;
import praktikum.Pages.MainPage;
import praktikum.Pages.RegistrationPage;

import static org.hamcrest.CoreMatchers.equalTo;

public class Assertions {

    @Step("assert that header text is '{expectedHeaderText}'")
    public static void assertHeaderText(WebDriver driver, String expectedHeaderText) {
        LoginPage loginPage = new LoginPage(driver);
        MatcherAssert.assertThat(loginPage.getHeaderText(), equalTo(expectedHeaderText));
    }
    @Step("assert that header text is '{expectedBurgerText}'")
    public static void assertCreateBurgerHeaderText(WebDriver driver, String expectedBurgerText) {
        MainPage mainPage = new MainPage(driver);
        MatcherAssert.assertThat(mainPage.getCreateBurgerTextFromHeader(), equalTo(expectedBurgerText));
    }
    @Step("assert that error message is '{expectedPasswordErrorText}'")
    public static void assertInvalidPasswordText(WebDriver driver, String expectedPasswordErrorText) {
        RegistrationPage RegistrationPage = new RegistrationPage(driver);
        MatcherAssert.assertThat(RegistrationPage.getInvalidPasswordText(), equalTo(expectedPasswordErrorText));
    }
}
