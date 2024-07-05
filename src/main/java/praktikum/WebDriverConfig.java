package praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;


public class WebDriverConfig {
    public static final long WAIT_SEC_TIMEOUT = 10;

    @Step("setDriver")
    public static WebDriver setDriver() {
        String browserType = System.getProperty("browser", "chrome").toLowerCase();

        switch (browserType) {
            case "chrome":
                return setupChromeDriver();
            case "firefox":
                return setupFirefoxDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    private static WebDriver setupChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);

        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
    }

    private static WebDriver setupFirefoxDriver() {

       /* FirefoxOptions firefoxOptions = new FirefoxOptions();//.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe");
        firefoxOptions.addArguments("--headless");
       System.setProperty("webdriver.firefox.bin", "C:/Program Files/Mozilla Firefox/firefox.exe");
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(firefoxOptions);
        */

        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions()
                //.configureFromEnv();
                .setBinary("C:/Program Files/Mozilla Firefox/firefox.exe");
        return new FirefoxDriver(firefoxOptions);
    }
}
