package praktikum.API;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class UserUtils {
    private static final String MAIL_HOST = "@gmail.com";
    public static String generateUserName() {
        return RandomStringUtils.randomAlphabetic(8);
    }
    public static String generateUserEmail() {
        return RandomStringUtils.randomAlphabetic(8) + MAIL_HOST;
    }
    public static String generateUserPassword() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    @Step("generate user credentials")
    public static User generateCredentials() {
        String name = generateUserName();
        String email = generateUserEmail();
        String password = generateUserPassword();
        return new User(name, email, password);
    }
}
